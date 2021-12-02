package FTB;

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
        int pos = 0;
        while (pos != sentence.length()) {
            if (getWord() != null) {
                break;
            }
            if (sentence.charAt(pos) == '(' || sentence.charAt(pos) == ')') {
                ++pos;
            }
            else if (sentence.charAt(pos) == ' '){
                String wordToUse = new String();
                ++pos;
                while (sentence.charAt(pos) != ')') {
                    wordToUse = wordToUse + sentence.charAt(pos);
                    ++pos;
                }

                this.setWord(wordToUse);

            }
            else {
                String typeToUse = new String();
                while (sentence.charAt(pos) != ' ') {
                    typeToUse = typeToUse + sentence.charAt(pos);
                    ++pos;
                }

                this.setMyType(typeToUse);

            }

        }
    }

}
