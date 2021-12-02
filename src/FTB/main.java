package FTB;

public class main {


    public static void main(String[] args) {
        String text_FTB = "(SENT (NP-SUJ (D Une) (N quinzaine) (PP (P de) (NP (N militaires) (AP (A libériens))))) (VN (V ont) (V été) (V transférés)) (PP-P_OBJ (P à) (NP (N Abidjan))) (PONCT .))" ;
        String text_FTB_2 = "(SENT (Ssub-MOD (C Comme) (VN-OBJ (CL le) (V déplore)) (NP-SUJ (D le) (N (N quotidien) (A financier))) (PONCT ,)) (VN-SUJ (CL on) (V est)) (AdP-MOD (ADV bien) (ADV loin) (PP (P des) (NP (D *T*) (D (D quatre) (PONCT -) (D vingt) (PONCT -) (D deux)) (N admissions) (VPpart (V enregistrées) (PP (P au) (NP (D *T*) (N cours) (PP (P de) (NP (D l') (N année) (N 1987))))))))) (PONCT .))";
        Component falsigram = new Component(text_FTB);
        Component falsigram_2 = new Component(text_FTB_2);
        System.out.println(falsigram.toString());
        System.out.println(falsigram_2.toString());
    }
}

// src/iut/montexte.txt