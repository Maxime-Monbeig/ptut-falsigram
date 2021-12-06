package Falsigram;

import java.util.Random;
import java.util.regex.Pattern;

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


    public String Insert(String str) {

        this.str = str;


        String[] mots = Pattern.compile("\\w+" ).split(str);
        Random r = new
                Random();
        String motAleatoire = mots[r.nextInt(mots.length)];

        return motAleatoire;
    }//        - Rajouter des mots



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




