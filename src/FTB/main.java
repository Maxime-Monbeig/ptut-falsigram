package FTB;

public class main {


    public static void main(String[] args) {
        String text_FTB = "(SENT (NP-SUJ (D Une) (N quinzaine) (PP (P de) (NP (N militaires) (AP (A libériens))))) (VN (V ont) (V été) (V transférés)) (PP-P_OBJ (P à) (NP (N Abidjan))) (PONCT .))" ;

        Falsigram falsigram = new Falsigram(text_FTB);
    }
}
