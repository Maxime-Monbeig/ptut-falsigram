package FalsigramV2;


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
                    String choice_normal_sent;
                    do {
                        System.out.println("Choisissez une phrase à modifier (type normal) : ");
                        for (int i = 0; i < falsigram.getNormal().size(); i++) {
                            System.out.println(i + 1 + " -> " + falsigram.getNormal().get(i));
                            listNormal.put(String.valueOf(i + 1), falsigram.getNormal().get(i));
                        }
                        choice_normal_sent = scanner.nextLine();
                    } while (!listNormal.containsKey(choice_normal_sent));

                    if (listNormal.containsKey(choice_normal_sent) && !choice_normal_sent.equals("r")){
                        String choice_normal_type;
                        do {
                            System.out.println("Choisissez ce que vous voulez modifier :");
                            System.out.println("m -> Modifier les mots");
                            System.out.println("l -> Modifier les lettres");
                            System.out.println("i -> Réinitialiser la phrase");
                            choice_normal_type = scanner.nextLine();
                        }while (!choice_normal_type.equals("m") && !choice_normal_type.equals("l") && !choice_normal_type.equals("i") && !choice_normal_type.equals("r"));

                        String choice_normal_action;
                        HashMap<String, String> actionNormal = new HashMap<>();
                        if (choice_normal_type.equals("m")){
                            do {
                                System.out.println("Choisissez l'action à effectuer :");
                                System.out.println("1 -> Doubler un mot");
                                actionNormal.put("1", "doubler un mot");
                                System.out.println("2 -> Supprimer un mot");
                                actionNormal.put("2", "supprimer un mot");
                                System.out.println("3 -> Déplacer un mot");
                                actionNormal.put("3", "déplacer un mot");
                                System.out.println("4 -> Echanger deux mots");
                                actionNormal.put("4", "échanger deux mots");
                                System.out.println("5 -> Ajouter un mot");
                                actionNormal.put("5", "ajouter un mot");
                                choice_normal_action = scanner.nextLine();
                            }while (!actionNormal.containsKey(choice_normal_action));


                            if (actionNormal.containsKey(choice_normal_action) && !choice_normal_action.equals("r")){
                                String choice_normal_number;
                                boolean isANumber;
                                do {
                                    isANumber = true;
                                    System.out.println("Combien de fois voulez vous " + actionNormal.get(choice_normal_action) + " ?");
                                    System.out.print("Nombre de fois : ");
                                    choice_normal_number = scanner.nextLine();

                                    int pos = 0;

                                    while (isANumber && pos < choice_normal_number.length()){
                                        ArrayList<Character> numbers = new ArrayList<Character>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
                                        if (!numbers.contains(choice_normal_number.charAt(pos))){
                                            isANumber = false;
                                            break;
                                        }
                                        pos = pos + 1;
                                    }
                                    if (choice_normal_action.equals("2") && isANumber && Integer.parseInt(choice_normal_number) >= falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).getStock().size()){
                                        System.out.println("Vous ne pouvez pas supprimer plus de " + falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).getStock().size()
                                                            + " mots sinon votre phrase sera vide !");
                                        isANumber = false;
                                    }
                                    else if (!isANumber){
                                        System.out.println("Ce n'est pas un nombre !");
                                    }

                                }while (!choice_normal_number.equals("r") && !isANumber);

                                switch (choice_normal_action) {
                                    case "1":
                                        for (int i = 0; i < Integer.parseInt(choice_normal_number); i++) {
                                            falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).DoublageMot();
                                        }
                                        break;
                                    case "2":
                                        for (int i = 0; i < Integer.parseInt(choice_normal_number); i++) {
                                            falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).SupprimerMot();
                                        }
                                        break;
                                    case "3":
                                        for (int i = 0; i < Integer.parseInt(choice_normal_number); i++) {
                                            falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).DeplacerMot();
                                        }
                                        break;
                                    case "4":
                                        for (int i = 0; i < Integer.parseInt(choice_normal_number); i++) {
                                            falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).EchangerMot();
                                        }
                                        break;
                                    case "5":
                                        for (int i = 0; i < Integer.parseInt(choice_normal_number); i++) {
                                            falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).AjouterMot();
                                        }
                                        break;

                                }
                            }

                        }
                        else if (choice_normal_type.equals("l")){

                        }
                        else if (choice_normal_type.equals("i")){
                            falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).initStock(falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).getStr());
                            falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).calculStr_out();
                        }
                        else {

                        }
                    }
                }
                default -> {
                }
            }
        } while (!s.equals("q"));


    }
}
