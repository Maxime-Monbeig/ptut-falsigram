package Falsigram;

import java.util.*;

public class GenerateError {

    private ArrayList<StringBuilder> stock = new ArrayList<>();
    private int num; // Pour choisir quelle type d'erreurs on va appliquer
    private String str; // Phrase d'entrée
    private String str_out; // Phrase de sortie

    public ArrayList<StringBuilder> getStock() {
        return stock;
    }

    public void setStock(ArrayList<StringBuilder> stock) {
        this.stock = stock;
    }

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
        // Choix d'un mot dans la phrase
        Random rand_str = new Random();
        int rand_pos;
        rand_pos = rand_str.nextInt(this.stock.size() - 1);
        StringBuilder word = new StringBuilder(getStock().get(rand_pos));
        if (rand_pos == 0){
            word.setCharAt(0, Character.toLowerCase(word.charAt(0)));
        }

        this.stock.add(rand_pos + 1, word);
        String sentence = "";
        for (StringBuilder str : getStock()){
            sentence = sentence + str + ' ';
        }

        this.str_out = sentence;

    }//        - Doubler les mots


    public void Insert() {
        // Choix d'un mot dans la phrase
        Random rand_str = new Random();
        int rand_pos;
        rand_pos = rand_str.nextInt(getStock().size() - 1);
        StringBuilder word = new StringBuilder(getStock().get(rand_pos));
        if (rand_pos == 0){
            word.setCharAt(0, Character.toLowerCase(word.charAt(0)));
        }

        // Position aléatoire
        Random rand = new Random();
        int for_rand;
        for_rand = rand.nextInt(getStock().size());

        if (for_rand == 0){
            word.setCharAt(0, Character.toUpperCase(word.charAt(0)));
            this.stock.get(0).setCharAt(0, Character.toLowerCase(this.stock.get(0).charAt(0)));
        }

        // Ajout du mot
        this.stock.add(for_rand, word);
        String sentence = "";
        for (StringBuilder str : this.stock){
            sentence = sentence + str + ' ';
        }

        this.str_out = sentence;

    }//        - Rajouter des mots ---------- ajout du mot "base" a la position i



    public void Move() {
        // Choix d'un mot dans la phrase
        Random rand_str = new Random();
        int rand_pos;
        rand_pos = rand_str.nextInt(stock.size() - 1);
        StringBuilder word = new StringBuilder(getStock().get(rand_pos));
        if (rand_pos == 0){
            word.setCharAt(0, Character.toLowerCase(word.charAt(0)));
        }

        // Retrait de ce mot
        this.stock.remove(rand_pos);

        // Position aléatoire
        Random rand = new Random();
        int for_rand;
        for_rand = rand.nextInt(getStock().size());

        if (for_rand == 0){
            word.setCharAt(0, Character.toUpperCase(word.charAt(0)));
            this.stock.get(0).setCharAt(0, Character.toLowerCase(this.stock.get(0).charAt(0)));
        }

        // Ajout du mot
        this.stock.add(for_rand, word);

        // Construction de la nouvelle phrase
        String sentence = "";
        for (StringBuilder str : stock){
            sentence = sentence + str + ' ';
        }

        this.str_out = sentence;
    }//        - Déplacer des mots



    public void Delete() {
        // Choix d'un mot dans la phrase
        Random rand_str = new Random();
        int rand_pos;
        rand_pos = rand_str.nextInt(stock.size() - 1);
        StringBuilder word = new StringBuilder(stock.get(rand_pos));
        if (rand_pos == 0){
            word.setCharAt(0, Character.toLowerCase(word.charAt(0)));
        }

        // Retrait de ce mot
        stock.remove(rand_pos);

        // Construction de la nouvelle phrase
        String sentence = "";
        for (StringBuilder str : stock){
            sentence = sentence + str + ' ';
        }

        this.str_out = sentence;
    }//        - Supprimer des mots

    public GenerateError(String str, int num) {

        // Stock des mots
        int i = 0;

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
            this.stock.add(mot);
        }
        this.str = str;
        this.num = num;
    }
}




