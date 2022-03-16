package Falsigram;

import java.util.*;

public class Word extends Group {
    private String word;

    public Word(){
        super();
    }

    public Word(String str) {
        this.setMySentence(str);
        this.readString(getMySentence());
    }

    @Override
    public String toString() {
        if (this.getMyType().compareTo("PONCT") == 0){
            return word;
        }
        return word + ' ';
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void readString (String sentence) {
        if (sentence.charAt(0) == '('){
            int pos = 0;
            while (pos != sentence.length()) {
                if (getWord() != null) {
                    break;
                }
                if (sentence.charAt(pos) == '(' || sentence.charAt(pos) == ')') {
                    ++pos;
                }
                else if (sentence.charAt(pos) == ' '){
                    String wordToUse = "";
                    ++pos;
                    while (sentence.charAt(pos) != ')') {
                        wordToUse = wordToUse + sentence.charAt(pos);
                        ++pos;
                    }

                    this.setWord(wordToUse);

                }
                else {
                    String typeToUse = "";
                    while (sentence.charAt(pos) != ' ') {
                        typeToUse = typeToUse + sentence.charAt(pos);
                        ++pos;
                    }

                    this.setMyType(typeToUse);

                }

            }
        }
        else if (sentence.charAt(0) == '<'){
            int pos = 2;
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
            setWord(wordToUse);
        }
        if (getXml_Attributes() != null){
            for (Map.Entry<String, String> mapentry : getXml_Attributes().entrySet()) {
                if (mapentry.getKey().equals("cat")){
                    this.setMyType(mapentry.getValue());
                    break;
                }
            }
        }

    }

    @Override
    public ArrayList<Group> getMyComponents() {
        return null;
    }

    @Override
    public ArrayList<Group> getAllWords() {
        return null;
    }

    public void doubleLettre(int pos){


        StringBuilder nouveauMot = new StringBuilder(this.getWord());
        nouveauMot.insert(nouveauMot.charAt(pos+1), nouveauMot.charAt(pos));

        this.setWord(nouveauMot.toString());
    }

    public void substituteLettre(){

    }

    public void deleteLettre(){

    }

    public void moveLettre(){

    }

    public void insertLettre(){

    }
}
