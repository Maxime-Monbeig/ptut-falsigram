package Falsigram;

public class GenerateError {


    private int num; // Pour choisir quelle type d'erreurs on va appliquer
    private String str; // Phrase d'entrée
    private String word;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void Doublage(String str) {

        this.str = str;


    }//        - Doubler les mots


    public StringBuffer Insert(String str) {

        this.str = str;
        String word = "base ";
        int i = 28;
        StringBuffer string = new StringBuffer(str);
        string.insert(i, word);
        return string;


    }//        - Rajouter des mots ---------- ajout du mot "base" a la position i



    public void Move(String str) {

        this.str = str;


    }//        - Déplacer des mots



    public void Delete(String str) {

        this.str = str;


    }//        - Supprimer des mots



    public void Substitute(String str) {

        this.str = str;


    }//        - Remplacement des mots par Lemna


    public GenerateError(String str) {

        this.str = str;
    }
}




