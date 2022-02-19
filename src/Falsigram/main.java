package Falsigram;

public class main {


    public static void main(String[] args) {
        String text_FILE = "/Users/maxim/Documents/IUT INFO/PTUT/ptut-falsigram/fichier_test.txt";
//        String text_FTB = "(SENT (NP-SUJ (D Une) (N quinzaine) (PP (P de) (NP (N militaires) (AP (A libériens))))) (VN (V ont) (V été) (V transférés)) (PP-P_OBJ (P à) (NP (N Abidjan))) (PONCT .))" ;
//        String text_XML = "<SENT argument=\"ETR\" author=\"MINANGOY ROBERT\" date=\"1990-01-19\" nb=\"1015\" textID=\"456\">\n" +
//                "    <NP fct=\"SUJ\">\n" +
//                "        <w cat=\"D\" ee=\"D-ind-fs\" ei=\"Dfs\" lemma=\"un\" mph=\"fs\" subcat=\"ind\">Une</w>\n" +
//                "        <w cat=\"N\" ee=\"N-C-fs\" ei=\"NCfs\" lemma=\"quinzaine\" mph=\"fs\" subcat=\"C\">quinzaine</w>\n" +
//                "        <PP>\n" +
//                "            <w cat=\"P\" ee=\"P\" ei=\"P\" lemma=\"de\">de</w>\n" +
//                "            <NP>\n" +
//                "                <w cat=\"N\" ee=\"N-C-mp\" ei=\"NCmp\" lemma=\"militaire\" mph=\"mp\" subcat=\"C\">militaires</w>\n" +
//                "                <AP>\n" +
//                "                    <w cat=\"A\" ee=\"A-qual-mp\" ei=\"Amp\" lemma=\"libérien\" mph=\"mp\" subcat=\"qual\">libériens</w>\n" +
//                "                </AP>\n" +
//                "            </NP>\n" +
//                "        </PP>\n" +
//                "    </NP>\n" +
//                "    <VN>\n" +
//                "        <w cat=\"V\" ee=\"V--P3p\" ei=\"VP3p\" lemma=\"avoir\" mph=\"P3p\" subcat=\"\">ont</w>\n" +
//                "        <w cat=\"V\" ee=\"V--Kms\" ei=\"VKms\" lemma=\"être\" mph=\"Kms\" subcat=\"\">été</w>\n" +
//                "        <w cat=\"V\" ee=\"V--Kmp\" ei=\"VKmp\" lemma=\"transférer\" mph=\"Kmp\" subcat=\"\">transférés</w>\n" +
//                "    </VN>\n" +
//                "    <PP fct=\"P-OBJ\">\n" +
//                "        <w cat=\"P\" ee=\"P\" ei=\"P\" lemma=\"à\">à</w>\n" +
//                "        <NP>\n" +
//                "            <w cat=\"N\" ee=\"N-P-ms\" ei=\"NPms\" lemma=\"Abidjan\" mph=\"ms\" subcat=\"P\">Abidjan</w>\n" +
//                "        </NP>\n" +
//                "    </PP>\n" +
//                "    <w cat=\"PONCT\" ee=\"PONCT-S\" ei=\"PONCTS\" lemma=\".\" subcat=\"S\">.</w>\n" +
//                "</SENT>";
//        String text_XML_2 = "<SENT argument=\"ECO\" author=\"LEMONDE\" date=\"1990-01-19\" nb=\"1093\" textID=\"467\">\n" +
//                "    <NP fct=\"SUJ\">\n" +
//                "        <w cat=\"D\" ee=\"D-def-fs\" ei=\"Dfs\" lemma=\"le\" mph=\"fs\" subcat=\"def\">La</w>\n" +
//                "        <w cat=\"N\" ee=\"N-C-fs\" ei=\"NCfs\" lemma=\"diminution\" mph=\"fs\" subcat=\"C\">diminution</w>\n" +
//                "    </NP>\n" +
//                "    <VN>\n" +
//                "        <w cat=\"V\" ee=\"V--P3s\" ei=\"VP3s\" lemma=\"paraître\" mph=\"P3s\" subcat=\"\">paraît</w>\n" +
//                "    </VN>\n" +
//                "    <w cat=\"PONCT\" ee=\"PONCT-W\" ei=\"PONCTW\" lemma=\",\" subcat=\"W\">,</w>\n" +
//                "    <w cat=\"ADV\" ee=\"ADV\" ei=\"ADV\" lemma=\"toutefois\">toutefois</w>\n" +
//                "    <w cat=\"PONCT\" ee=\"PONCT-W\" ei=\"PONCTW\" lemma=\",\" subcat=\"W\">,</w>\n" +
//                "    <AP fct=\"ATS\">\n" +
//                "        <w cat=\"ADV\" ee=\"ADV\" ei=\"ADV\" lemma=\"moins\">moins</w>\n" +
//                "        <w cat=\"A\" ee=\"A-qual-fs\" ei=\"Afs\" lemma=\"net\" mph=\"fs\" subcat=\"qual\">nette</w>\n" +
//                "    </AP>\n" +
//                "    <PP fct=\"MOD\">\n" +
//                "        <w cat=\"P\" ee=\"P\" ei=\"P\" lemma=\"en\">en</w>\n" +
//                "        <NP>\n" +
//                "            <w cat=\"N\" ee=\"N-P-fs\" ei=\"NPfs\" lemma=\"France\" mph=\"fs\" subcat=\"P\">France</w>\n" +
//                "        </NP>\n" +
//                "        <COORD>\n" +
//                "            <w cat=\"C\" ee=\"C-C\" ei=\"CC\" lemma=\"et\" subcat=\"C\">et</w>\n" +
//                "            <PP>\n" +
//                "                <w cat=\"P\" ee=\"P\" ei=\"P\" lemma=\"en\">en</w>\n" +
//                "                <NP>\n" +
//                "                    <w cat=\"N\" ee=\"N-P-fs\" ei=\"NPfs\" lemma=\"Italie\" mph=\"fs\" subcat=\"P\">Italie</w>\n" +
//                "                </NP>\n" +
//                "            </PP>\n" +
//                "        </COORD>\n" +
//                "    </PP>\n" +
//                "    <w cat=\"PONCT\" ee=\"PONCT-S\" ei=\"PONCTS\" lemma=\".\" subcat=\"S\">.</w>\n" +
//                "</SENT>";
//        Component falsigram = new Component(text_FTB);
//        Component falsigram_2 = new Component(text_XML);
//        Component falsigram_3 = new Component(text_XML_2);
        Component falsigram_4 = new Component(text_FILE);

//        System.out.println(falsigram.toString());
//        System.out.println(falsigram_2.toString());
//        System.out.println(falsigram_3.toString());
        System.out.println(falsigram_4);


        /*String txt = "Une quinzaine de militaires libériens ont été transférés à Abidjan." ;



        for (int i = 0; i < 10; ++i){
            GenerateError falsigram = new GenerateError(txt, 1);
            //falsigram.Doublage();
            //System.out.println("Doublage : \n" + falsigram.getStr_out());
            //falsigram.Move();
            //System.out.println("Move : \n" + falsigram.getStr_out());
            //falsigram.Delete();
            //System.out.println("Delete : \n" + falsigram.getStr_out());
            //falsigram.Insert();
            //System.out.println("Insert : \n" + falsigram.getStr_out());
            //falsigram.InsertLettre();
            //System.out.println("Insert Lettre : \n" + falsigram.getStr_out());
            //falsigram.MoveLettre();
            //System.out.println("Move Lettre : \n" + falsigram.getStr_out());
            falsigram.DeleteLettre();
            System.out.println("Delete Lettre : \n" + falsigram.getStr_out());
            //falsigram.DoublageLettre();
            //System.out.println("Doublage Lettre : \n" + falsigram.getStr_out() + '\n');
        }*/


    }
}

// src/iut/montexte.txt