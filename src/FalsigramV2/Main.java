package FalsigramV2;

import Falsigram.Falsigram;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        //FalsigramV2 test = new FalsigramV2("/amuhome/m20007035/IdeaProjects/ptut-falsigram/fichier_test.txt");
        //FalsigramV2 test = new FalsigramV2("/amuhome/m20007035/IdeaProjects/ptut-falsigram/Lorem_ipsum.txt");
        //FalsigramV2 test = new FalsigramV2("C:\\Users\\maxim\\Documents\\IUT INFO\\PTUT\\ptut-falsigram\\Lorem_ipsum.txt");
        //FalsigramV2 test = new FalsigramV2("C:\\Users\\maxim\\Documents\\IUT INFO\\PTUT\\ptut-falsigram\\fichier_test.txt");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le chemin complet de votre fichier texte ou une phrase");
        String s= scanner.nextLine();
        FalsigramV2 falsigram = new FalsigramV2(s);

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
            switch (s) {
                case "x" -> {
                    HashMap<String, Xml> listXml = new HashMap<>();
                    String choice_xml_sent;
                    do {
                        System.out.println("Choisissez une phrase à modifier (type xml) : ");
                        for (int i = 0; i < falsigram.getXml().size(); i++) {
                            System.out.println(i + 1 + " -> " + falsigram.getXml().get(i));
                            listXml.put(String.valueOf(i + 1), falsigram.getXml().get(i));
                        }
                        System.out.println("r -> Revenir à l'écran précédent");
                        listXml.put("r", falsigram.getXml().get(listXml.size() - 1));
                        choice_xml_sent = scanner.nextLine();

                        if (listXml.containsKey(choice_xml_sent) && !choice_xml_sent.equals("r")){
                            String choice_xml_action = "";
                            do{
                                System.out.println("Choisissez l'action à effectuer :");
                                System.out.println("m -> Modifier les mots dans la phrase");
                                System.out.println("l -> Modifier les lettres dans les mots");
                                System.out.println("r -> Revenir à l'écran précédent");
                                choice_xml_action = scanner.nextLine();
                            }while(!choice_xml_action.equals("m") && !choice_xml_action.equals("l") && !choice_xml_action.equals("r"));
                            if (choice_xml_action.equals("l")){
                                List<String> types_xml = new ArrayList<String>(listXml.get(choice_xml_sent).getXmlTypes());
                                String choice_xml_wordtype = "";
                                do {
                                    System.out.println("Phrase choisie : \"" + listXml.get(choice_xml_sent) + "\"\n" + "Choisissez un type de mot à modifier : ");
                                    for (String str : types_xml) {
                                        System.out.println(str);
                                    }
                                    System.out.println("HASARD");
                                    types_xml.add("HASARD");
                                    choice_xml_wordtype = scanner.nextLine();
                                } while (!types_xml.contains(choice_xml_wordtype));
                                System.out.println(listXml.get(s).getRandomWordFromType(choice_xml_wordtype).toString());
                            }
                            else if (choice_xml_action.equals("r")){
                                choice_xml_sent = "";
                                choice_xml_action = "";
                                listXml.clear();
                            }
                        }
                        else if (choice_xml_sent.equals("r")) {
                            choice_xml_sent = "";
                            listXml.clear();
                            break;
                        }


                    } while (!listXml.containsKey(choice_xml_sent));




                }
                case "f" -> {
                    HashMap<String, Ftb> listFtb = new HashMap<>();
                    do {
                        System.out.println("Choisissez une phrase à modifier (type ftb) : ");
                        for (int i = 0; i < falsigram.getFtb().size(); i++) {
                            System.out.println(i + 1 + " -> " + falsigram.getFtb().get(i));
                            listFtb.put(String.valueOf(i + 1), falsigram.getFtb().get(i));
                        }
                        s = scanner.nextLine();
                    } while (!listFtb.containsKey(s));
                }
                case "n" -> {
                    HashMap<String, Normal> listNormal = new HashMap<>();
                    do {
                        System.out.println("Choisissez une phrase à modifier (type normal) : ");
                        for (int i = 0; i < falsigram.getNormal().size(); i++) {
                            System.out.println(i + 1 + " -> " + falsigram.getNormal().get(i));
                            listNormal.put(String.valueOf(i + 1), falsigram.getNormal().get(i));
                        }
                        s = scanner.nextLine();
                    } while (!listNormal.containsKey(s));
                }
                default -> {
                }
            }
        } while (!s.equals("q"));
    }
}
