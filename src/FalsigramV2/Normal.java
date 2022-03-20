package FalsigramV2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Normal {
    private ArrayList<StringBuilder> stock = new ArrayList<>();
    private int num; // Pour choisir quelle type d'erreurs on va appliquer
    private String str; // Phrase d'entrée
    private String str_out; // Phrase de sortie

    public Normal(String str) {
        this.initStock(str);
        this.str = str;
        this.calculStr_out();
    }

    public void initStock (String str){

        int i = 0;
        while (i < str.length()){
            StringBuilder stringBuilder = new StringBuilder();
            while ( i < str.length() && str.charAt(i) != ' ' && str.charAt(i) != '.' && str.charAt(i) != '!' && str.charAt(i) != '?'){
                stringBuilder.append(str.charAt(i));
                ++i;
            }
            if (stringBuilder.length() != 0){
                this.stock.add(stringBuilder);
            }
            if (i < str.length() && (str.charAt(i) == '.' || str.charAt(i) == '!' || str.charAt(i) == '?')){
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append(str.charAt(i));
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

    @Override
    public String toString() {
        return str_out;
    }
}
