package FalsigramV2;

import java.util.*;

public class Normal {
    private ArrayList<StringBuilder> stock;
    private int num; // Pour choisir quelle type d'erreurs on va appliquer
    private String str; // Phrase d'entrée
    private String str_out; // Phrase de sortie
    private final Random rand = new Random();

    public Normal(String str) {
        this.initStock(str);
        this.str = str;
        this.calculStr_out();
    }

    public ArrayList<StringBuilder> getStock() {
        return stock;
    }

    public void setStock(ArrayList<StringBuilder> stock) {
        this.stock = stock;
    }

    public String getStr() {
        return str;
    }

    public String getStr_out() {
        return str_out;
    }

    @Override
    public String toString() {
        return str_out;
    }

    public void initStock (String str){
        stock = new ArrayList<>();

        int i = 0;
        while (i < str.length()){
            StringBuilder stringBuilder = new StringBuilder();
            while ( i < str.length() && str.charAt(i) != ' ' && str.charAt(i) != ',' && str.charAt(i) != '.' && str.charAt(i) != '!' && str.charAt(i) != '?' && str.charAt(i) != '(' && str.charAt(i) != ')'){
                stringBuilder.append(str.charAt(i));
                ++i;
            }
            if (stringBuilder.length() != 0){
                this.stock.add(stringBuilder);
            }
            if (i < str.length() && (str.charAt(i) == '.' || str.charAt(i) == '!' || str.charAt(i) == '?' || str.charAt(i) == ',' || str.charAt(i) == '(' || str.charAt(i) == ')')){
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append(str.charAt(i));

                while ( i+1 != str.length() &&str.charAt(i+1) == '.'){
                    i++;
                    stringBuilder1.append(str.charAt(i));
                }

                this.stock.add(stringBuilder1);
            }
            i++;

        }
    }

    public void calculStr_out(){
        String str_sortie = "";
        for (StringBuilder sb : stock){
            str_sortie = str_sortie + sb + ' ';
        }
        str_out = str_sortie;
    }

    public AbstractMap.SimpleEntry<Integer, StringBuilder> RandomWordFromStock(){
        Integer pos = rand.nextInt(this.getStock().size());
        return new AbstractMap.SimpleEntry<>(pos, new StringBuilder(getStock().get(pos)));
    }

    public void DoublageMot() {
        // Choix d'un mot dans la phrase

        AbstractMap.SimpleEntry<Integer, StringBuilder> word_info = this.RandomWordFromStock();
        StringBuilder word = new StringBuilder(word_info.getValue());
        int word_pos = word_info.getKey();
        if (word_pos == 0){
            word.setCharAt(0, Character.toLowerCase(word.charAt(0)));
        }

        ArrayList<StringBuilder> stock_current = new ArrayList<>(stock);

        stock_current.add(word_pos + 1, word);

        setStock(stock_current);
        this.calculStr_out();
    }//        - Doubler un mot

    public void DoublageLettre(StringBuilder word) {
        int lettre_pos = rand.nextInt(word.length());
    }

    public void SupprimerMot() {
        // Choix d'un mot dans la phrase

        int word_pos = rand.nextInt(this.stock.size());

        // Suppression du mot
        this.getStock().remove(word_pos);

        // Calcul de la nouvelle phrase
        this.calculStr_out();
    }//        - Supprimer un mot

    public void AjouterMot() {
        // Choix d'un mot dans la phrase

        AbstractMap.SimpleEntry<Integer, StringBuilder> word_info = this.RandomWordFromStock();
        StringBuilder word = new StringBuilder(word_info.getValue());
        int word_pos = word_info.getKey();

        while (word.toString().equals(".") || word.toString().equals("!") || word.toString().equals("?") || word.toString().equals(")") || word.toString().equals("(") || word.toString().equals("\"")){
            word_info = this.RandomWordFromStock();
            word = word_info.getValue();
        }

        // Choix d'une position pour ajouter le mot
        int ajout_pos = rand.nextInt(this.stock.size());
        while (ajout_pos == word_pos){
            ajout_pos = rand.nextInt(this.stock.size());
        }

        // Ajustement des majuscules en début de mot
        if (word_pos == 0){
            word.setCharAt(0, Character.toLowerCase(word.charAt(0)));
        }
        else if (ajout_pos == 0){
            word.setCharAt(0, Character.toUpperCase(word.charAt(0)));
            getStock().get(0).setCharAt(0, Character.toLowerCase(getStock().get(0).charAt(0)));
        }

        ArrayList<StringBuilder> stock_current = new ArrayList<>(stock);

        // Ajout du mot
        stock_current.add(ajout_pos, word);

        setStock(stock_current);
        this.calculStr_out();
    }//        - Ajouter un mot

    public void EchangerMot() {
        // Choix d'un mot dans la phrase

        AbstractMap.SimpleEntry<Integer, StringBuilder> word_info = this.RandomWordFromStock();
        StringBuilder word = new StringBuilder(word_info.getValue());
        int word_pos = word_info.getKey();

        // Choix d'un deuxième mot dans la phrase
        AbstractMap.SimpleEntry<Integer, StringBuilder> echange_info = this.RandomWordFromStock();
        int echange_pos = echange_info.getKey();
        while (echange_pos == word_pos){
            echange_info = this.RandomWordFromStock();
            echange_pos = echange_info.getKey();
        }
        StringBuilder echange = new StringBuilder(echange_info.getValue());

        // Ajustement des majuscules en début de mot
        if (word_pos == 0){
            word.setCharAt(0, Character.toLowerCase(word.charAt(0)));
            echange.setCharAt(0, Character.toUpperCase(echange.charAt(0)));
        }
        else if (echange_pos == 0){
            word.setCharAt(0, Character.toUpperCase(word.charAt(0)));
            echange.setCharAt(0, Character.toLowerCase(echange.charAt(0)));
        }

        ArrayList<StringBuilder> stock_current = new ArrayList<>(stock);

        stock_current.add(word_pos, echange);
        stock_current.remove(word_pos + 1);
        stock_current.add(echange_pos, word);
        stock_current.remove(echange_pos + 1);

        setStock(stock_current);
        this.calculStr_out();
    }

    public void DeplacerMot() {
        // Choix d'un mot dans la phrase

        AbstractMap.SimpleEntry<Integer, StringBuilder> word_info = this.RandomWordFromStock();
        StringBuilder word = new StringBuilder(word_info.getValue());
        int word_pos = word_info.getKey();

        // Choix d'une position pour déplacer le mot
        int new_pos = rand.nextInt(this.stock.size());
        while (new_pos == word_pos){
            new_pos = rand.nextInt(this.stock.size());
        }

        if (word_pos == 0){
            word.setCharAt(0, Character.toLowerCase(word.charAt(0)));
            getStock().get(1).setCharAt(0, Character.toUpperCase(getStock().get(1).charAt(0)));
        }
        else if (new_pos == 0){
            word.setCharAt(0, Character.toUpperCase(word.charAt(0)));
            getStock().get(0).setCharAt(0, Character.toLowerCase(getStock().get(0).charAt(0)));
        }

        ArrayList<StringBuilder> stock_current = new ArrayList<>(stock);

        stock_current.add(new_pos, word);
        if (new_pos > word_pos){
            stock_current.remove(word_pos);
        }
        else {
            stock_current.remove(word_pos+1);
        }

        this.setStock(stock_current);
        this.calculStr_out();
    }

}
