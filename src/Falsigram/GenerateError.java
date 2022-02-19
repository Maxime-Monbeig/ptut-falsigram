package Falsigram;

import java.lang.reflect.Array;
import java.util.*;

public class GenerateError {

    private List<List<Character>> stock = new ArrayList<>();
    private int num; // Pour choisir quelle type d'erreurs on va appliquer
    private String str; // Phrase d'entrée
    private String str_out; // Phrase de sortie

    @Override
    public String toString() {
        return "GenerateError{" +
                "stock=" + stock +
                ", num=" + num +
                ", str='" + str + '\'' +
                ", str_out='" + str_out + '\'' +
                '}';
    }

    public List<List<Character>> getStock() {
        return stock;
    }

    public void setStock(List<List<Character>> stock) {
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

    public GenerateError(String str, int num) {

        // Stock des mots
        int i = 0;

        while (i < str.length()){
            List<Character> current_list = new ArrayList<Character>();
            while ( i < str.length() && str.charAt(i) != ' ' && str.charAt(i) != '.' || str.charAt(i) == '!' || str.charAt(i) == '?'){
                current_list.add(str.charAt(i));
                ++i;
            }
            this.stock.add(current_list);
            if (str.charAt(i) == '.' || str.charAt(i) == '!' || str.charAt(i) == '?'){
                List<Character> current_list_2 = new ArrayList<Character>();
                current_list_2.add(str.charAt(i));
                this.stock.add(current_list_2);
            }
            i++;

        }
        this.str = str;
        this.num = num;
    }

    public void Doublage() {
        // Choix d'un mot dans la phrase
        Random rand_str = new Random();
        int rand_pos;
        rand_pos = rand_str.nextInt(this.stock.size() - 1);
        List<Character> word = new ArrayList<Character>(getStock().get(rand_pos));
        if (rand_pos == 0){
            word.set(0, Character.toLowerCase(word.get(0)));
        }

        List<List<Character>> stock_current = new ArrayList<>(stock);

        stock_current.add(rand_pos + 1, word);
        String sentence = "";
        for (List<Character> str : stock_current){
            for (Character car : str){
                sentence = sentence + car;

            }
            sentence = sentence + ' ';
        }

        this.str_out = sentence;
        stock_current = new ArrayList<>(stock);
    }//        - Doubler les mots

    public void DoublageLettre () {
        // Choix d'un mot dans la phrase
        Random rand_str = new Random();
        int rand_pos;
        rand_pos = rand_str.nextInt(this.stock.size() - 1);
        List<Character> word = new ArrayList<Character>(getStock().get(rand_pos));


        // Position aléatoire
        Random rand = new Random();
        int for_rand;
        for_rand = rand.nextInt(word.size());

        // Doubler la lettre
        List<List<Character>> stock_current = new ArrayList<List<Character>>(stock);

        if (for_rand+1 == stock_current.get(rand_pos).size()){
            stock_current.get(rand_pos).add(for_rand, word.get(for_rand));
        }
        else{
            stock_current.get(rand_pos).add(for_rand+1, word.get(for_rand));
        }
        //stock_current.get(rand_pos).add(for_rand_2, word.get(for_rand));

        String sentence = "";
        for (List<Character> str : stock_current){
            for (Character car : str){
                sentence = sentence + car;

            }
            sentence = sentence + ' ';
        }

        this.str_out = sentence;
        stock_current = new ArrayList<>(stock);
    }

    public void Insert() {
        // Choix d'un mot dans la phrase
        Random rand_str = new Random();
        int rand_pos;
        rand_pos = rand_str.nextInt(getStock().size() - 1);
        List<Character> word = new ArrayList<Character>(getStock().get(rand_pos));
        if (rand_pos == 0){
            word.set(0, Character.toLowerCase(word.get(0)));
        }

        // Position aléatoire
        Random rand = new Random();
        int for_rand;
        for_rand = rand.nextInt(getStock().size());
        List<List<Character>> stock_current = new ArrayList<>(stock);

        if (for_rand == 0){
            word.set(0, Character.toUpperCase(word.get(0)));
            stock_current.get(0).set(0, Character.toLowerCase(stock_current.get(0).get(0)));
        }

        // Ajout du mot

        stock_current.add(for_rand, word);
        String sentence = "";
        for (List<Character> str : stock_current){
            for (Character car : str){
                sentence = sentence + car;
            }
            sentence = sentence + ' ';
        }

        this.str_out = sentence;
        stock_current = new ArrayList<>(stock);
    }//        - Rajouter des mots ---------- ajout du mot "base" a la position i

    public void InsertLettre () {
        // Choix d'un mot dans la phrase
        Random rand_str = new Random();
        int rand_pos;
        int size = stock.size();
        List<List<Character>> stock_current = new ArrayList<List<Character>>(stock);
        rand_pos = rand_str.nextInt(stock_current.size() - 1);
        List<Character> word = new ArrayList<Character>(stock_current.get(rand_pos));


        // Position aléatoire
        Random rand = new Random();
        int for_rand;
        for_rand = rand.nextInt(word.size());

        Random rand_2 = new Random();
        int for_rand_2;
        for_rand_2 = rand.nextInt(word.size());

        // Inserer la lettre

        stock_current.get(rand_pos).add(for_rand_2, word.get(for_rand));

        String sentence = "";
        for (List<Character> str : stock_current){
            for (Character car : str){
                sentence = sentence + car;

            }
            sentence = sentence + ' ';
        }

        this.str_out = sentence;
    }

    public void Move() {
        // Choix d'un mot dans la phrase
        Random rand_str = new Random();
        int rand_pos;
        List<List<Character>> stock_current = new ArrayList<>(stock);
        rand_pos = rand_str.nextInt(stock_current.size() - 1);
        List<Character> word = new ArrayList<Character>(getStock().get(rand_pos));


        // Retrait de ce mot
        stock_current.remove(rand_pos);
        if (rand_pos == 0){
            word.set(0, Character.toLowerCase(word.get(0)));
            stock_current.get(0).set(0, Character.toUpperCase(stock_current.get(0).get(0)));
        }

        // Position aléatoire
        Random rand = new Random();
        int for_rand;
        for_rand = rand.nextInt(stock_current.size());
        while (for_rand == rand_pos){
            for_rand = rand.nextInt(stock_current.size());
        }

        if (for_rand == 0){
            word.set(0, Character.toUpperCase(word.get(0)));
            stock_current.get(0).set(0, Character.toLowerCase(stock_current.get(0).get(0)));
        }

        // Ajout du mot
        stock_current.add(for_rand, word);

        // Construction de la nouvelle phrase
        String sentence = "";
        for (List<Character> str : stock_current){
            for (Character car : str){
                sentence = sentence + car;

            }
            sentence = sentence + ' ';
        }

        this.str_out = sentence;
        stock_current = new ArrayList<>(stock);
    }//        - Déplacer des mots

    public void MoveLettre () {
        // Choix d'un mot dans la phrase
        Random rand_str = new Random();
        int rand_pos;
        rand_pos = rand_str.nextInt(this.stock.size() - 1);
        List<Character> word = new ArrayList<Character>(getStock().get(rand_pos));
        List<List<Character>> stock_current = new ArrayList<List<Character>>(stock);


        // Position aléatoire
        Random rand = new Random();
        int for_rand;
        for_rand = rand.nextInt(word.size());
        while (for_rand == rand_pos){
            for_rand = rand.nextInt(stock_current.get(rand_pos).size());
        }

        // Déplacer la lettre

        stock_current.get(rand_pos).remove(for_rand);
        if (rand_pos == 0){
        }

        Random rand_2 = new Random();
        int for_rand_2;
        for_rand_2 = rand_2.nextInt(stock_current.size());
        while (for_rand_2 == for_rand){
            for_rand_2 = rand_2.nextInt(stock_current.get(rand_pos).size());
        }

        stock_current.get(rand_pos).add(for_rand_2, word.get(for_rand));
        //stock_current.get(rand_pos).add(for_rand_2, word.get(for_rand));

        String sentence = "";
        for (List<Character> str : stock_current){
            for (Character car : str){
                sentence = sentence + car;

            }
            sentence = sentence + ' ';
        }

        this.str_out = sentence;
        stock_current = new ArrayList<>(stock);
    }

    public void Delete() {
        // Choix d'un mot dans la phrase
        Random rand_str = new Random();
        int rand_pos;
        rand_pos = rand_str.nextInt(stock.size() - 1);
        List<Character> word = new ArrayList<Character>(getStock().get(rand_pos));


        // Retrait de ce mot
        List<List<Character>> stock_current = new ArrayList<>(stock);
        stock_current.remove(rand_pos);
        if (rand_pos == 0){
            stock_current.get(0).set(0, Character.toUpperCase(stock_current.get(0).get(0)));
        }

        // Construction de la nouvelle phrase
        String sentence = "";
        for (List<Character> str : stock_current){
            for (Character car : str){
                sentence = sentence + car;

            }
            sentence = sentence + ' ';
        }

        this.str_out = sentence;
        stock_current = new ArrayList<>(stock);
    }//        - Supprimer des mots

    public void DeleteLettre() {
        // Choix d'un mot dans la phrase
        Random rand_str = new Random();
        int rand_pos;
        rand_pos = rand_str.nextInt(stock.size() - 1);
        List<Character> word = new ArrayList<Character>(getStock().get(rand_pos));


        // Retrait de la lettre
        Random rand = new Random();
        int for_rand;
        for_rand = rand.nextInt(word.size());

        List<List<Character>> stock_current = new ArrayList<>(stock);
        stock_current.get(rand_pos).remove(for_rand);
        if (rand_pos == 0 && for_rand == 0){
            stock_current.get(0).set(0, Character.toUpperCase(stock_current.get(0).get(0)));
        }

        // Construction de la nouvelle phrase
        String sentence = "";
        for (List<Character> str : stock_current){
            for (Character car : str){
                sentence = sentence + car;

            }
            sentence = sentence + ' ';
        }

        this.str_out = sentence;
        stock_current = new ArrayList<>(stock);
    }//
}