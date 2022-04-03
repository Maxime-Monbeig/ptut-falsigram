package FalsigramV2;

import java.util.*;

public class Normal {
    private ArrayList<StringBuilder> stock;
    private String str; // Phrase d'entrée
    private String str_out; // Phrase de sortie
    private static final Random rand = new Random();

    public Normal(String str) {
        this.str = str;
        this.initStock(str);
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

    public void addToStock(StringBuilder str, int pos){
        this.getStock().add(pos, str);
    }

    @Override
    public String toString() {
        return this.getStr_out();
    }

    public void initStock (String str){
        stock = new ArrayList<>();

        int i = 0;
        while (i < str.length()){
            StringBuilder stringBuilder = new StringBuilder();
            while ( i < str.length() && str.charAt(i) != ' ' && str.charAt(i) != ','
                                     && str.charAt(i) != '.' && str.charAt(i) != '!'
                                     && str.charAt(i) != '?' && str.charAt(i) != '(' && str.charAt(i) != ')'){
                stringBuilder.append(str.charAt(i));
                ++i;
            }
            if (stringBuilder.length() != 0){
                this.stock.add(stringBuilder);
            }
            if (i < str.length() && (str.charAt(i) == '.' || str.charAt(i) == '!'
                                 || str.charAt(i) == '?' || str.charAt(i) == ','
                                 || str.charAt(i) == '(' || str.charAt(i) == ')')){
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append(str.charAt(i));

                while ( str.charAt(i) == '.' && i+1 != str.length() &&str.charAt(i+1) == '.'){
                    i++;
                    stringBuilder1.append(str.charAt(i));
                }

                this.stock.add(stringBuilder1);
            }
            i++;

        }
        this.calculStr_out();
    }

    public void calculStr_out(){
        String str_sortie = "";
        for (StringBuilder sb : stock){
            str_sortie = str_sortie + sb + ' ';
        }
        str_out = str_sortie;
    }

    public AbstractMap.SimpleEntry<Integer, StringBuilder> RandomWordFromStock(){
        int pos = rand.nextInt(this.getStock().size());
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

        this.addToStock(word, word_pos+1);
        this.calculStr_out();
    }//        - Doubler un mot

    public void DoublageLettre(AbstractMap.SimpleEntry<Integer, StringBuilder> word_info) {
        StringBuilder word = word_info.getValue();
        int lettre_pos = rand.nextInt(word.length());

        if (lettre_pos == 0 && word_info.getKey() == 0){
            word.insert(lettre_pos+1, Character.toLowerCase(word.charAt(lettre_pos)));
        }
        else {
            word.insert(lettre_pos+1, word.charAt(lettre_pos));
        }

        this.getStock().set(word_info.getKey(), word);
        this.calculStr_out();
    }

    public void SupprimerMot() {
        // Choix d'un mot dans la phrase

        int word_pos = rand.nextInt(this.getStock().size());

        while (this.getStock().get(word_pos).length() == 1){
            word_pos = rand.nextInt(this.getStock().size());
        }

        if (word_pos == 0){
            this.getStock().get(1).setCharAt(0, Character.toUpperCase(this.getStock().get(1).charAt(0)));
        }

        // Suppression du mot
        this.getStock().remove(word_pos);

        // Calcul de la nouvelle phrase
        this.calculStr_out();
    }//        - Supprimer un mot

    public void SupprimerLettre(AbstractMap.SimpleEntry<Integer, StringBuilder> word_info) {
        StringBuilder word = word_info.getValue();

        while (word.length() == 1){
            word_info = this.RandomWordFromStock();
            word = word_info.getValue();
        }

        int lettre_pos = rand.nextInt(word.length());

        if (lettre_pos == 0 && word_info.getKey() == 0){
            word.deleteCharAt(lettre_pos);
            word.setCharAt(lettre_pos, Character.toUpperCase(word.charAt(lettre_pos)));
        }
        else {
            word.deleteCharAt(lettre_pos);
        }

        this.getStock().set(word_info.getKey(), word);
        this.calculStr_out();
    }

    public void AjouterMot() {
        // Choix d'un mot dans la phrase

        AbstractMap.SimpleEntry<Integer, StringBuilder> word_info = this.RandomWordFromStock();
        StringBuilder word = new StringBuilder(word_info.getValue());
        int word_pos = word_info.getKey();

        while (word.toString().equals(".") || word.toString().equals(",") || word.toString().equals("!")
                                           || word.toString().equals("?") || word.toString().equals(")")
                                           || word.toString().equals("(") || word.toString().equals("\"")){
            word_info = this.RandomWordFromStock();
            word = word_info.getValue();
        }

        // Choix d'une position pour ajouter le mot
        int ajout_pos = rand.nextInt(this.getStock().size());
        while (ajout_pos == word_pos){
            ajout_pos = rand.nextInt(this.getStock().size());
        }

        // Ajustement des majuscules en début de mot
        if (word_pos == 0){
            word.setCharAt(0, Character.toLowerCase(word.charAt(0)));
        }
        else if (ajout_pos == 0){
            word.setCharAt(0, Character.toUpperCase(word.charAt(0)));
            getStock().get(0).setCharAt(0, Character.toLowerCase(getStock().get(0).charAt(0)));
        }

        // Ajout du mot
        this.addToStock(word, ajout_pos);
        this.calculStr_out();
    }//        - Ajouter un mot

    public void AjouterLettre(AbstractMap.SimpleEntry<Integer, StringBuilder> word_info) {
        StringBuilder word = word_info.getValue();
        int lettre_pos = rand.nextInt(word.length());
        int new_pos = rand.nextInt(word.length());

        while (new_pos == lettre_pos){
            new_pos = rand.nextInt(word_info.getValue().length());
        }


        if (lettre_pos == 0 && word_info.getKey() == 0){
            word.insert(new_pos, Character.toLowerCase(word.charAt(lettre_pos)));
        }
        else if (new_pos == 0 && word_info.getKey() == 0) {
            word.insert(new_pos, Character.toUpperCase(word.charAt(lettre_pos)));
            word.setCharAt(new_pos + 1, Character.toLowerCase(word.charAt(new_pos + 1)));
        }
        else {
            word.insert(new_pos, word.charAt(lettre_pos));
        }

        this.getStock().set(word_info.getKey(), word);
        this.calculStr_out();
    }

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

        this.addToStock(echange, word_pos);
        this.getStock().remove(word_pos + 1);
        this.addToStock(word, echange_pos);
        this.getStock().remove(echange_pos + 1);

        this.calculStr_out();
    }

    public void EchangerLettre(AbstractMap.SimpleEntry<Integer, StringBuilder> word_info) {
        StringBuilder word = word_info.getValue();
        int lettre_pos = rand.nextInt(word.length());
        int new_pos = rand.nextInt(word.length());

        while (new_pos == lettre_pos){
            new_pos = rand.nextInt(word_info.getValue().length());
        }

        StringBuilder word_current = new StringBuilder(word_info.getValue());

        if (lettre_pos == 0 && word_info.getKey() == 0){
            word_current.insert(new_pos, Character.toLowerCase(word.charAt(lettre_pos)));
            word_current.deleteCharAt(lettre_pos + 1);
            word_current.insert(lettre_pos, Character.toUpperCase(word.charAt(new_pos)));
            word_current.deleteCharAt(new_pos + 1);
        }
        else if (new_pos == 0 && word_info.getKey() == 0) {
            word_current.insert(new_pos, Character.toUpperCase(word.charAt(lettre_pos)));
            word_current.deleteCharAt(new_pos + 1);
            word_current.insert(lettre_pos, Character.toLowerCase(word.charAt(new_pos)));
            word_current.deleteCharAt(lettre_pos + 1);
        }
        else {
            word_current.insert(new_pos, word.charAt(lettre_pos));
            word_current.deleteCharAt(new_pos + 1);
            word_current.insert(lettre_pos, word.charAt(new_pos));
            word_current.deleteCharAt(lettre_pos + 1);
        }

        this.getStock().set(word_info.getKey(), word_current);
        this.calculStr_out();
    }

    public void DeplacerMot() {
        // Choix d'un mot dans la phrase

        AbstractMap.SimpleEntry<Integer, StringBuilder> word_info = this.RandomWordFromStock();
        StringBuilder word = new StringBuilder(word_info.getValue());
        int word_pos = word_info.getKey();

        // Choix d'une position pour déplacer le mot
        int new_pos = rand.nextInt(this.getStock().size());
        while (new_pos == word_pos){
            new_pos = rand.nextInt(this.getStock().size());
        }

        if (word_pos == 0){
            word.setCharAt(0, Character.toLowerCase(word.charAt(0)));
            getStock().get(1).setCharAt(0, Character.toUpperCase(getStock().get(1).charAt(0)));
        }
        else if (new_pos == 0){
            word.setCharAt(0, Character.toUpperCase(word.charAt(0)));
            getStock().get(0).setCharAt(0, Character.toLowerCase(getStock().get(0).charAt(0)));
        }


        this.getStock().remove(word_pos);
        this.addToStock(word, new_pos);

        this.calculStr_out();
    }

    public void DeplacerLettre (AbstractMap.SimpleEntry<Integer, StringBuilder> word_info) {
        StringBuilder word = word_info.getValue();
        int lettre_pos = rand.nextInt(word.length());
        int new_pos = rand.nextInt(word.length());

        StringBuilder word_current = new StringBuilder(word_info.getValue());

        if (lettre_pos == 0 && word_info.getKey() == 0){
            word_current.deleteCharAt(lettre_pos);
            word_current.insert(new_pos, Character.toLowerCase(word.charAt(lettre_pos)));
        }
        else if (new_pos == 0 && word_info.getKey() == 0) {
            word_current.deleteCharAt(lettre_pos);
            word_current.insert(new_pos, Character.toUpperCase(word.charAt(lettre_pos)));
        }
        else {
            word_current.deleteCharAt(lettre_pos);
            word_current.insert(new_pos, word.charAt(lettre_pos));
        }

        this.getStock().set(word_info.getKey(), word_current);
        this.calculStr_out();;
    }



    public static void main(String[] args) {
        Normal normal = new Normal("Salut je suis un test");
        normal.EchangerLettre(normal.RandomWordFromStock());
        normal.AjouterLettre(normal.RandomWordFromStock());
        normal.DeplacerLettre(normal.RandomWordFromStock());
        normal.SupprimerLettre(normal.RandomWordFromStock());
        normal.DoublageLettre(normal.RandomWordFromStock());
        normal.DoublageLettre(new AbstractMap.SimpleEntry<>(1, new StringBuilder("Coucou")));
        normal.DeplacerMot();
    }

}
