package FTB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Component extends Group{
    private final ArrayList<String> createWord = new ArrayList<String>();
    private ArrayList<Group> myComponents;



    private static final String[] wordsType = {"N", "P", "D", "A", "PONCT", "V", "CL", "PRO", "ADV", "C"};



    public Component(String mySentence) {
        this.createWord.addAll(Arrays.asList(wordsType));
        this.myComponents = new ArrayList<Group>();
        this.setMySentence(mySentence);
        this.readString(getMySentence());
    }


    // toString


    @Override
    public String toString() {
        String display_sentence = new String();
        Iterator<Group> it = myComponents.iterator();
        while (it.hasNext()) {
            display_sentence = display_sentence + it.next().toString();
        }
        return display_sentence;
    }

    public ArrayList<String> getCreateWord() {
        return createWord;
    }

    public ArrayList<Group> getMyComponents() {
        return myComponents;
    }



    public static String[] getWordsType() {
        return wordsType;
    }



    public void addComponent (Group group) {
        this.myComponents.add(group);
    }

    public String cutString (String str, int pos){
        String newStr = new String();
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

    public void readString(String sentence) {
        String textType = new String();

        // FORMAT PTB
        if (sentence.charAt(0) == '('){
            int pos = 0;
            while (pos != sentence.length()) {
                if (sentence.charAt(pos) == '(') {
                    if (this.getMyType() != null){
                        String str = cutString(getMySentence(), pos);
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
                        String typeToUse = new String();
                        while (sentence.charAt(x) != ' ') {
                            typeToUse = typeToUse + sentence.charAt(x);
                            ++x;
                        }
                        this.setMyType(typeToUse);
                        pos = pos + this.getMyType().length();
                    }

                }
                ++pos;
            }
        }
        // FORMAT XML
        else if (sentence.charAt(0) == '<'){
            //Analyse XML
        }
        else {
            //Analyse Fichier
            // Try "ouvrir fichier" catch "exception ça ouvre pas"
        }

    }
}
