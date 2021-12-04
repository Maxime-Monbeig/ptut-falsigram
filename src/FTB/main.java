package FTB;

public class main {


    public static void main(String[] args) {
        String text_FTB = "(SENT (NP-SUJ (D Une) (N quinzaine) (PP (P de) (NP (N militaires) (AP (A libériens))))) (VN (V ont) (V été) (V transférés)) (PP-P_OBJ (P à) (NP (N Abidjan))) (PONCT .))" ;
        //String text_FTB_2 = "fichier.txt";
        Component falsigram = new Component(text_FTB);
        //Component falsigram_2 = new Component(text_FTB_2);
        System.out.println(falsigram.toString());
        String localDir = System.getProperty("user.dir");
        System.out.println(localDir);
        //System.out.println(falsigram_2.toString());
    }
}

// src/iut/montexte.txt