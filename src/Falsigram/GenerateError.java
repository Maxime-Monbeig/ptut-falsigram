package Falsigram;

import java.util.*;

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

        List<Integer> list = Arrays.asList(4,14,17,28,38,42,46,57,59,67);
        Random random = new Random();
        int pos = random.nextInt(list.size());
        int i = list.get(pos);

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




