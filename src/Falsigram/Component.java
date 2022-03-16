package Falsigram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Component extends Group{
    //private final ArrayList<String> createWord = new ArrayList<String>();

    private ArrayList<Group> myComponents;

    //private static final String[] wordsType = {"N", "P", "D", "A", "PONCT", "V", "CL", "PRO", "ADV", "C"};

    public Component(String mySentence) {
        //this.createWord.addAll(Arrays.asList(wordsType));
        this.myComponents = new ArrayList<Group>();
        this.setMySentence(mySentence);
        this.readString(getMySentence());
    }

    @Override
    public String toString() {
        String display_sentence = "";
        for (Group myComponent : myComponents) {
            display_sentence = display_sentence + myComponent.toString();
        }
        return display_sentence;
    }

    public void addToSentence (char c){
        this.setMySentence(getMySentence()+c);
    }

    public ArrayList<Group> getMyComponents() {
        return myComponents;
    }

    public void addComponent (Group group) {
        this.myComponents.add(group);
    }

    // Fonction pour analyser seulement la partie du string nécessaire (FTB)
    public String cutStringFTB (String str, int pos){
        String newStr = "";
        int x = 0;
        do{
            if (str.charAt(pos) == '(') {
                ++x;
            }
            else if (str.charAt(pos) == ')') {
                --x;
            }
            newStr = newStr + str.charAt(pos);
            ++pos;
        }while (x > 0);

        return newStr;
    }

    public List<String> getXmlTypes(){
        ArrayList<String> xmlTypes = new ArrayList<>();
        if (this.myComponents.size() != 0){
            for (Group myComponent : myComponents) {
                if (myComponent.getClass().equals(Word.class) && !xmlTypes.contains(myComponent.getMyType())){
                    xmlTypes.add(myComponent.getMyType());
                }
                else if (myComponent.getClass().equals(Component.class)){
                    xmlTypes.addAll(((Component) myComponent).getXmlTypes());
                }
            }
        }

        List<String> xmlTypes_toreturn = xmlTypes.stream().distinct().toList();
        return xmlTypes_toreturn;
    }

    // Fonction pour analyser seulement la partie du string nécessaire (XML)
    public String cutStringXML (String str, int pos){
        String newStr = "";
        int x = 0;
        do{
            if (str.charAt(pos) == '<') {
                ++x;
            }
            else if (str.charAt(pos) == '/') {
                x = x - 2;
            }
            newStr = newStr + str.charAt(pos);
            ++pos;
        }while (x > 0);

        while (str.charAt(pos) != '>'){
            newStr = newStr + str.charAt(pos);
            ++pos;
        }
        newStr = newStr + str.charAt(pos);
        return newStr;
    }

    // Analyser la phrase en entrée pour créer l'arbre
    public void readString(String sentence) {
        String textType = "";

        // FORMAT PTB
        if (sentence.charAt(0) == '('){
            int pos = 0;
            while (pos < sentence.length()) {
                if (sentence.charAt(pos) == '(') {
                    if (this.getMyType() != null){
                        String str = cutStringFTB(getMySentence(), pos);
                        int x = 0;
                        while (str.charAt(x) != ' '){
                            ++x;
                        }
                        if (str.charAt(x+1) == '('){
                            Component current_component = new Component(str);
                            myComponents.add(current_component);
                            pos = pos + current_component.getMySentence().length();
                            //System.out.println(current_component.getMySentence());
                        }
                        else {
                            Word current_word = new Word(str);
                            myComponents.add(current_word);
                            pos = pos + current_word.getMySentence().length();
                            //System.out.println(current_word.getMySentence());
                        }

                    }
                    else {
                        int x = pos+1;
                        String typeToUse = "";
                        while (sentence.charAt(x) != ' ') {
                            typeToUse = typeToUse + sentence.charAt(x);
                            ++x;
                        }
                        this.setMyType(typeToUse);
                        pos = pos + x;
                    }

                }
                ++pos;
            }
        }
        // FORMAT XML
        else if (sentence.charAt(0) == '<'){
            //Analyse XML
            int pos = 1;
            while (pos < sentence.length()) {
                if (Character.isUpperCase(sentence.charAt(pos))){
                    int x = pos;
                    String typeToUse = "";
                    while (sentence.charAt(x) != ' ' && sentence.charAt(x) != '>') {
                        typeToUse = typeToUse + sentence.charAt(x);
                        ++x;
                    }
                    this.setMyType(typeToUse);
                    pos = pos + x;
                }
                else if (Character.isLowerCase(sentence.charAt(pos))){
                    HashMap<String, String> caract= new HashMap<>();

                    while (sentence.charAt(pos) != '>'){
                        String param = "";
                        String value = "";
                        int x = pos;
                        while (sentence.charAt(x) != '=' ){
                            if (sentence.charAt(x) != ' '){
                                param = param + sentence.charAt(x);
                            }
                            ++x;

                        }
                        ++x;
                        int count = 0;
                        do {
                            if (sentence.charAt(x) == '\"'){
                                ++count;
                                ++x;
                            }
                            else {
                                value = value + sentence.charAt(x);
                                ++x;
                            }
                        } while (count < 2);

                        caract.put(param, value);
                        pos = x;
                    }
                    setXml_Attributes(caract);
                }
                else if (sentence.charAt(pos) == '<' && sentence.charAt(pos+1) != '/'){
                    String str = cutStringXML(sentence, pos);
                    if (str.charAt(1) == 'w'){
                        Word current_word = new Word(str);
                        myComponents.add(current_word);
                        pos = pos + current_word.getMySentence().length();
                    }
                    else {
                        Component current_component = new Component(str);
                        myComponents.add(current_component);
                        pos = pos + current_component.getMySentence().length();
                    }
                }
                else {
                    ++pos;
                }

            }
        }
        else if (sentence.charAt(0) == '/') {
            //Analyse Fichier
            try
            {
                File file = new File(sentence);
                FileReader fileR = new FileReader(file);
                BufferedReader fileBR = new BufferedReader(fileR);

                int r = 0;
                StringBuilder contenu = new StringBuilder();
                while((r = fileBR.read())!=-1)
                {
                    contenu.append((char)r);
                }
                fileR.close();
                fileBR.close();
                this.setMySentence(contenu.toString());
                this.readString(getMySentence());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            // Try "ouvrir fichier" catch "exception ça ouvre pas"
        }

    }

    public void modifierMot(Component mot, String action){
        Random rand_str = new Random();
        int rand_pos = rand_str.nextInt(mot.getWord().length()-1);
        switch (action){
            case "substitute":
                break;
            case "move":
                break;
            case "delete":
                break;
            case "double":
                mot.doubleLettre(rand_pos);
                break;
            case "insert":
                break;
        }
    }

    public ArrayList<Group> getAllWords(){
        ArrayList<Group> wordsList = new ArrayList<>();
        for (Group g : this.getMyComponents()){
            if (g.getClass().equals(Word.class)){
                wordsList.add(g);
            }
            else {
                wordsList.addAll(g.getAllWords());
            }
        }
        return wordsList;
    }

    @Override
    public void doubleLettre(int pos) {

    }

    @Override
    public String getWord() {
        return null;
    }

    public Group getRandomWordFromType (String type){
        Random rand = new Random();
        int rand_pos = rand.nextInt(this.getAllWords().size());
        while (!this.getAllWords().get(rand_pos).getMyType().equals(type)){
            rand_pos = rand.nextInt(this.getAllWords().size());
        }
        return this.getAllWords().get(rand_pos);
    }


}
