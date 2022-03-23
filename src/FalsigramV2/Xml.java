package FalsigramV2;

import Falsigram.Component;
import Falsigram.Group;
import Falsigram.Word;

import java.util.*;

public class Xml {
    private ArrayList<Xml> Composants = new ArrayList<>();
    private String mySentence;
    private String myWord;
    private Boolean isAWord;
    private String myType;
    private HashMap<String, String> xml_Attributes = new HashMap<>();

    public Xml(String mySentence, boolean isAWord) {
        this.mySentence = mySentence;
        this.isAWord = isAWord;
        this.readString(getMySentence());
    }

    public String getMyWord() {
        return myWord;
    }

    public String toString() {
        String display_sentence = "";
        if (this.getComposants() != null){
            for (Xml Composant : Composants) {
                if (Composant.isAWord){
                    display_sentence = display_sentence + Composant.getMyWord();
                    if (!(Composant.getMyType().equals("PONCT"))){
                        display_sentence = display_sentence + ' ';
                    }
                }
                else {
                    display_sentence = display_sentence + Composant.toString();
                }
            }
        }
        return display_sentence;
    }

    public void setMyWord(String myWord) {
        this.myWord = myWord;
    }

    public void setIsAWord(Boolean IsAWord) {
        isAWord = IsAWord;
    }

    public ArrayList<Xml> getComposants() {
        return Composants;
    }

    public String getMySentence() {
        return mySentence;
    }

    public String getMyType() {
        return myType;
    }

    public HashMap<String, String> getXml_Attributes() {
        return xml_Attributes;
    }

    public void setMySentence(String mySentence) {
        this.mySentence = mySentence;
    }

    public void setMyType(String myType) {
        this.myType = myType;
    }

    public void setXml_Attributes(HashMap<String, String> xml_Attributes) {
        this.xml_Attributes = xml_Attributes;
    }

    public boolean getIsAWord() {
        return this.isAWord;
    }

    public ArrayList<Xml> getAllWords(){
        ArrayList<Xml> wordsList = new ArrayList<>();
        for (Xml g : this.getComposants()){
            if (g.getIsAWord()){
                wordsList.add(g);
            }
            else {
                wordsList.addAll(g.getAllWords());
            }
        }
        return wordsList;
    }

    public Xml getRandomWordFromType (String type){
        Random rand = new Random();
        int rand_pos = rand.nextInt(this.getAllWords().size());
        while (!this.getAllWords().get(rand_pos).getMyType().equals(type)){
            rand_pos = rand.nextInt(this.getAllWords().size());
        }
        return this.getAllWords().get(rand_pos);
    }

    public List<String> getXmlTypes(){
        ArrayList<String> xmlTypes = new ArrayList<>();
        if (this.getComposants().size() != 0){
            for (Xml myComponent : getComposants()) {
                if (myComponent.getIsAWord() && !xmlTypes.contains(myComponent.getMyType())){
                    xmlTypes.add(myComponent.getMyType());
                }
                else if (!myComponent.getIsAWord()){
                    xmlTypes.addAll((myComponent.getXmlTypes()));
                }
            }
        }

        return xmlTypes.stream().distinct().toList();
    }

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

    public void readString(String sentence) {
        String textType = "";

        // FORMAT XML
        //Analyse XML
        int pos = 1;

        if (isAWord){
            pos = 2;
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
            ++pos;
            String wordToUse = "";
            while (sentence.charAt(pos) != '<'){
                wordToUse = wordToUse + sentence.charAt(pos);
                ++pos;
            }
            setMyWord(wordToUse);
            if (getXml_Attributes() != null){
                for (Map.Entry<String, String> mapentry : getXml_Attributes().entrySet()) {
                    if (mapentry.getKey().equals("cat")){
                        this.setMyType(mapentry.getValue());
                        break;
                    }
                }
            }
        }
        else {
            while (pos < sentence.length()) {
                if (sentence.charAt(pos-1) == '<' && sentence.charAt(pos) != '/'){
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
                            }
                            else {
                                value = value + sentence.charAt(x);
                            }
                            ++x;
                        } while (count < 2);

                        caract.put(param, value);
                        pos = x;
                    }
                    setXml_Attributes(caract);
                }
                else if (sentence.charAt(pos) == '<' && sentence.charAt(pos+1) != '/'){
                    String str = cutStringXML(sentence, pos);
                    if (str.charAt(1) == 'w'){
                        Xml current_xml = new Xml(str, true);
                        Composants.add(current_xml);
                        pos = pos + current_xml.getMySentence().length();
                    }
                    else {
                        Xml current_xml = new Xml(str, false);
                        Composants.add(current_xml);
                        pos = pos + current_xml.getMySentence().length();
                    }
                }
                else {
                    ++pos;
                }

            }
        }



    }
}
