package FalsigramV2;


import java.util.ArrayList;

public class Ftb {
    private ArrayList<Ftb> Composants = new ArrayList<>();
    private String mySentence;
    private String myWord;
    private Boolean isAWord;
    private String myType;

    public Ftb(String mySentence, Boolean isAWord) {
        this.mySentence = mySentence;
        this.isAWord = isAWord;
        this.readFtb(getMySentence());
    }

    @Override
    public String toString() {
        String display_sentence = "";
        if (this.getComposants() != null){
            for (Ftb Composant : Composants) {
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

    public ArrayList<Ftb> getComposants() {
        return Composants;
    }

    public void setComposants(ArrayList<Ftb> composants) {
        Composants = composants;
    }

    public String getMySentence() {
        return mySentence;
    }

    public void setMySentence(String mySentence) {
        this.mySentence = mySentence;
    }

    public String getMyWord() {
        return myWord;
    }

    public void setMyWord(String myWord) {
        this.myWord = myWord;
    }

    public Boolean getAWord() {
        return isAWord;
    }

    public void setAWord(Boolean AWord) {
        isAWord = AWord;
    }

    public String getMyType() {
        return myType;
    }

    public void setMyType(String myType) {
        this.myType = myType;
    }

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

    public void readFtb(String sentence) {
        int pos = 0;

        if (isAWord){
            while (pos != sentence.length()) {
                if (getMyWord() != null) {
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

                    this.setMyWord(wordToUse);

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
        else {
            while (pos < sentence.length()) {
                if (sentence.charAt(pos) == '(') {
                    if (this.getMyType() != null){
                        String str = cutStringFTB(getMySentence(), pos);
                        int x = 0;
                        while (str.charAt(x) != ' '){
                            ++x;
                        }
                        if (str.charAt(x+1) == '('){
                            Ftb current_ftb = new Ftb(str, false);
                            Composants.add(current_ftb);
                            pos = pos + current_ftb.getMySentence().length();
                        }
                        else {
                            Ftb current_ftb = new Ftb(str, true);
                            Composants.add(current_ftb);
                            pos = pos + current_ftb.getMySentence().length();
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

    }
}
