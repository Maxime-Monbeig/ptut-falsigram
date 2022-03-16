package Falsigram;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class main {


    public static void main(String[] args) throws IOException {
        String text_FILE = "/amuhome/m20007035/IdeaProjects/ptut-falsigram/fichier_test.txt";
//        String text_FTB = "(SENT (NP-SUJ (D Une) (N quinzaine) (PP (P de) (NP (N militaires) (AP (A libériens))))) (VN (V ont) (V été) (V transférés)) (PP-P_OBJ (P à) (NP (N Abidjan))) (PONCT .))" ;
        Scanner scanner = new Scanner(System.in);
        String s= scanner.nextLine();
//        Component falsigram = new Component(text_FTB);
//        Component falsigram_2 = new Component(text_XML);
//        Component falsigram_3 = new Component(text_XML_2);
        Falsigram falsigram = new Falsigram(s);

        String question = "Choisissez un type de phrase à modifier : \n";
        if (falsigram.getXml().size() != 0){
            question = question + "x -> xml\n";
        }
        if (falsigram.getFtb().size() != 0){
            question = question + "f -> ftb\n";
        }
        if (falsigram.getNormal().size() != 0){
            question = question + "n -> normal\n";
        }
        question = question + "q -> quitter\n";
        do {
            do {
                System.out.print(question);
                s = scanner.nextLine();
            } while (!s.equals("x") && !s.equals("f") && !s.equals("n") && !s.equals("q"));
            switch (s){
                case "x":
                    HashMap<String, Component> list = new HashMap<>();
                    do {
                        System.out.println("Choisissez une phrase à modifier (type xml) : ");
                        for (int i = 0; i<falsigram.getXml().size(); i++){
                            System.out.println(i+1 + " -> " + falsigram.getXml().get(i));
                            list.put(String.valueOf(i + 1), falsigram.getXml().get(i));
                        }
                        s = scanner.nextLine();
                    }while (!list.containsKey(s));

                    List<String> types = new ArrayList<>(list.get(s).getXmlTypes());
                    String ss = "";

                    do {
                        System.out.println("Phrase choisie : \"" + list.get(s) + "\"\n" + "Choisissez un type de mot à modifier : ");
                        for (String str : list.get(s).getXmlTypes()){
                            System.out.println(str);
                        }
                        System.out.println("HASARD");
                        types.add("HASARD");
                        ss = scanner.nextLine();
                    }while (!types.contains(ss));
                    System.out.println(list.get(s).getRandomWordFromType(ss).toString());


                    break;
                case "f":
                    list = new HashMap<>();
                    do {
                        System.out.println("Choisissez une phrase à modifier (type ftb) : ");
                        for (int i = 0; i<falsigram.getFtb().size(); i++){
                            System.out.println(i+1 + " -> " + falsigram.getFtb().get(i));
                            list.put(String.valueOf(i + 1), falsigram.getFtb().get(i));
                        }
                        s = scanner.nextLine();
                    }while (!list.containsKey(s));
                    break;
                case "n":
                    HashMap<String, GenerateError>list2 = new HashMap<>();
                    do {
                        System.out.println("Choisissez une phrase à modifier (type normal) : ");
                        for (int i = 0; i<falsigram.getNormal().size(); i++){
                            System.out.println(i+1 + " -> " + falsigram.getNormal().get(i));
                            list2.put(String.valueOf(i + 1), falsigram.getNormal().get(i));
                        }
                        s = scanner.nextLine();
                    }while (!list2.containsKey(s));
                    break;
                default:
                    break;

            }
        } while (!s.equals("q"));

//        System.out.println(falsigram.toString());
//        System.out.println(falsigram_2.toString());
//        System.out.println(falsigram_3.toString());
//        System.out.println(falsigram.getStr_final());


        //String txt = "Une quinzaine de militaires libériens ont été transférés à Abidjan." ;






    }
}

// src/iut/montexte.txt