package Falsigram;

import java.io.*;
import java.util.Scanner;

public class main {


    public static void main(String[] args) throws IOException {
        String text_FILE = "/amuhome/m20007035/IdeaProjects/ptut-falsigram/fichier_test.txt";
//        String text_FTB = "(SENT (NP-SUJ (D Une) (N quinzaine) (PP (P de) (NP (N militaires) (AP (A libériens))))) (VN (V ont) (V été) (V transférés)) (PP-P_OBJ (P à) (NP (N Abidjan))) (PONCT .))" ;
        Scanner scanner = new Scanner(System.in);
        String s= new String(scanner.nextLine());
//        Component falsigram = new Component(text_FTB);
//        Component falsigram_2 = new Component(text_XML);
//        Component falsigram_3 = new Component(text_XML_2);
        Falsigram falsigram = new Falsigram(s);

//        System.out.println(falsigram.toString());
//        System.out.println(falsigram_2.toString());
//        System.out.println(falsigram_3.toString());
        System.out.println(falsigram.getStr_final());


        //String txt = "Une quinzaine de militaires libériens ont été transférés à Abidjan." ;






    }
}

// src/iut/montexte.txt