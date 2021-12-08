package Falsigram;

import java.util.*;

public class GenerateError {


    private int num; // Pour choisir quelle type d'erreurs on va appliquer
    private String str; // Phrase d'entrée
    private String str_out; // Phrase de sortie

    public String getStr_out() {
        return str_out;
    }

    public void setStr_out(String str_out) {
        this.str_out = str_out;
    }

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

    public void Doublage() {

        // Stock des mots
        int i = 0;

        ArrayList<StringBuilder> stock = new ArrayList<>();
        while (i < str.length()){
            StringBuilder mot = new StringBuilder();
            while ( i < str.length() && str.charAt(i) != ' ' && str.charAt(i) != '.'){
                mot = mot.append(str.charAt(i));
                ++i;
            }
            if (str.charAt(i) == '.' || str.charAt(i) == '!' || str.charAt(i) == '?'){
                mot = mot.append(str.charAt(i));
            }
            ++i;
            stock.add(mot);
        }

        // Choix d'un mot dans la phrase
        Random rand_str = new Random();
        int rand_pos;
        rand_pos = rand_str.nextInt(stock.size() - 1);
        StringBuilder word = new StringBuilder(stock.get(rand_pos));
        if (rand_pos == 0){
            word.setCharAt(0, Character.toLowerCase(word.charAt(0)));
        }

        stock.add(rand_pos + 1, word);
        String sentence = "";
        for (StringBuilder str : stock){
            sentence = sentence + str + ' ';
        }

        this.str_out = sentence;

    }//        - Doubler les mots


    public void Insert() {


        // Stock des mots
        int i = 0;

        ArrayList<StringBuilder> stock = new ArrayList<>();
        while (i < str.length()){
            StringBuilder mot = new StringBuilder();
            while ( i < str.length() && str.charAt(i) != ' ' && str.charAt(i) != '.'){
                mot = mot.append(str.charAt(i));
                ++i;
            }
            if (str.charAt(i) == '.' || str.charAt(i) == '!' || str.charAt(i) == '?'){
                mot = mot.append(str.charAt(i));
            }
            ++i;
            stock.add(mot);
        }

        // Choix d'un mot dans la phrase
        Random rand_str = new Random();
        int rand_pos;
        rand_pos = rand_str.nextInt(stock.size() - 1);
        StringBuilder word = new StringBuilder(stock.get(rand_pos));
        if (rand_pos == 0){
            word.setCharAt(0, Character.toLowerCase(word.charAt(0)));
        }

        // Position aléatoire
        Random rand = new Random();
        int for_rand;
        for_rand = rand.nextInt(stock.size());

        if (for_rand == 0){
            word.setCharAt(0, Character.toUpperCase(word.charAt(0)));
            stock.get(0).setCharAt(0, Character.toLowerCase(stock.get(0).charAt(0)));
        }

        // Ajout du mot
        stock.add(for_rand, word);
        String sentence = "";
        for (StringBuilder str : stock){
            sentence = sentence + str + ' ';
        }

        this.str_out = sentence;

    }//        - Rajouter des mots ---------- ajout du mot "base" a la position i



    public void Move(String str) {

        this.str = str;


    }//        - Déplacer des mots



    public void Delete(String str) {

        this.str = str;


    }//        - Supprimer des mots

    public GenerateError(String str, int num) {

        this.str = str;
        this.num = num;
    }
}




