package FTB;

public class main {


    public static void main(String[] args) {
        String text_FTB = "(SENT (NP-SUJ (D Une) (N quinzaine) (PP (P de) (NP (N militaires) (AP (A libériens))))) (VN (V ont) (V été) (V transférés)) (PP-P_OBJ (P à) (NP (N Abidjan))) (PONCT .))" ;
        String text_FTB_2 = "<SENT argument=\"ETR\" author=\"MINANGOY ROBERT\" date=\"1990-01-19\" nb=\"1015\" textID=\"456\">\n" +
                "    <NP fct=\"SUJ\">\n" +
                "        <w cat=\"D\" ee=\"D-ind-fs\" ei=\"Dfs\" lemma=\"un\" mph=\"fs\" subcat=\"ind\">Une</w>\n" +
                "        <w cat=\"N\" ee=\"N-C-fs\" ei=\"NCfs\" lemma=\"quinzaine\" mph=\"fs\" subcat=\"C\">quinzaine</w>\n" +
                "        <PP>\n" +
                "            <w cat=\"P\" ee=\"P\" ei=\"P\" lemma=\"de\">de</w>\n" +
                "            <NP>\n" +
                "                <w cat=\"N\" ee=\"N-C-mp\" ei=\"NCmp\" lemma=\"militaire\" mph=\"mp\" subcat=\"C\">militaires</w>\n" +
                "                <AP>\n" +
                "                    <w cat=\"A\" ee=\"A-qual-mp\" ei=\"Amp\" lemma=\"libérien\" mph=\"mp\" subcat=\"qual\">libériens</w>\n" +
                "                </AP>\n" +
                "            </NP>\n" +
                "        </PP>\n" +
                "    </NP>\n" +
                "    <VN>\n" +
                "        <w cat=\"V\" ee=\"V--P3p\" ei=\"VP3p\" lemma=\"avoir\" mph=\"P3p\" subcat=\"\">ont</w>\n" +
                "        <w cat=\"V\" ee=\"V--Kms\" ei=\"VKms\" lemma=\"être\" mph=\"Kms\" subcat=\"\">été</w>\n" +
                "        <w cat=\"V\" ee=\"V--Kmp\" ei=\"VKmp\" lemma=\"transférer\" mph=\"Kmp\" subcat=\"\">transférés</w>\n" +
                "    </VN>\n" +
                "    <PP fct=\"P-OBJ\">\n" +
                "        <w cat=\"P\" ee=\"P\" ei=\"P\" lemma=\"à\">à</w>\n" +
                "        <NP>\n" +
                "            <w cat=\"N\" ee=\"N-P-ms\" ei=\"NPms\" lemma=\"Abidjan\" mph=\"ms\" subcat=\"P\">Abidjan</w>\n" +
                "        </NP>\n" +
                "    </PP>\n" +
                "    <w cat=\"PONCT\" ee=\"PONCT-S\" ei=\"PONCTS\" lemma=\".\" subcat=\"S\">.</w>\n" +
                "</SENT>";
        //Component falsigram = new Component(text_FTB);
        Component falsigram_2 = new Component(text_FTB_2);
        //System.out.println(falsigram.toString());
        String localDir = System.getProperty("user.dir");
        System.out.println(localDir);
        //System.out.println(falsigram_2.toString());
    }
}

// src/iut/montexte.txt