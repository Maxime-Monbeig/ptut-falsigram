package FalsigramV2;


import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        //FalsigramV2 test = new FalsigramV2("/amuhome/m20007035/IdeaProjects/ptut-falsigram/fichier_test.txt");
        //FalsigramV2 test = new FalsigramV2("/amuhome/m20007035/IdeaProjects/ptut-falsigram/Lorem_ipsum.txt");
        //FalsigramV2 test = new FalsigramV2("C:\\Users\\maxim\\Documents\\IUT INFO\\PTUT\\ptut-falsigram\\Lorem_ipsum.txt");
        //FalsigramV2 test = new FalsigramV2("C:\\Users\\maxim\\Documents\\IUT INFO\\PTUT\\ptut-falsigram\\fichier_test.txt");
        //C:\Users\cyril\IdeaProjects\ptut-falsigram\Balzac.txt

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le chemin complet de votre fichier texte ou une phrase");
        String s= scanner.nextLine();
        boolean isAFile = false;
        if (s.charAt(0) == '\\' || (s.charAt(2) == '\\' && s.charAt(1) == ':')){
            isAFile = true;
        }
        FalsigramV2 falsigram = new FalsigramV2(s);

        String question = "\nChoisissez un type de phrase à modifier : \n";
        ArrayList<String> type_sent = new ArrayList<>();
        if (falsigram.getXml().size() != 0){
            question = question + "x -> xml\n";
            type_sent.add("x");
        }
        if (falsigram.getFtb().size() != 0){
            question = question + "f -> ftb\n";
            type_sent.add("f");
        }
        if (falsigram.getNormal().size() != 0){
            question = question + "n -> normal\n";
            type_sent.add("n");
        }
        if (falsigram.getXml().size() != 0 || falsigram.getFtb().size() != 0 || falsigram.getNormal().size() != 0){
            question = question + "a -> Mélange aléatoire de toutes les phrases\n";
            question = question + "s -> Sauvegarder les phrases dans un fichier texte\n";
            type_sent.add("a");
            type_sent.add("s");
        }
        question = question + "q -> quitter\n";
        type_sent.add("q");
        do {
            do {
                System.out.print(question);
                s = scanner.nextLine();
            } while (!type_sent.contains(s));
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
                            // choice_xml_sent = "";
                            // listXml.clear();
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
                        System.out.println("\nChoisissez une phrase à modifier (type normal) : ");
                        for (int i = 0; i < falsigram.getNormal().size(); i++) {
                            System.out.println(i + 1 + " -> " + falsigram.getNormal().get(i));
                            listNormal.put(String.valueOf(i + 1), falsigram.getNormal().get(i));
                        }
                        System.out.println("r -> Revenir à l'écran précédent");
                        choice_normal_sent = scanner.nextLine();

                        if (listNormal.containsKey(choice_normal_sent)){
                            String choice_normal_type;
                            do {
                                System.out.println("\nPhrase choisie : \"" + falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1) + "\"");
                                System.out.println("Choisissez ce que vous voulez modifier :");
                                System.out.println("m -> Modifier les mots");
                                System.out.println("l -> Modifier les lettres");
                                System.out.println("i -> Réinitialiser la phrase\n");
                                System.out.println("r -> Revenir à l'écran précédent");
                                choice_normal_type = scanner.nextLine();

                                String choice_normal_action;
                                HashMap<String, String> actionNormal = new HashMap<>();
                                switch (choice_normal_type) {
                                    case "m":
                                        do {
                                            System.out.println("\nChoisissez l'action à effectuer :");
                                            System.out.println("1 -> Doubler un mot");
                                            actionNormal.put("1", "doubler un mot");
                                            System.out.println("2 -> Supprimer un mot");
                                            actionNormal.put("2", "supprimer un mot");
                                            System.out.println("3 -> Déplacer un mot");
                                            actionNormal.put("3", "déplacer un mot");
                                            System.out.println("4 -> Echanger deux mots");
                                            actionNormal.put("4", "échanger deux mots");
                                            System.out.println("5 -> Ajouter un mot");
                                            actionNormal.put("5", "ajouter un mot\n");
                                            System.out.println("r -> Revenir à l'écran précédent");
                                            choice_normal_action = scanner.nextLine();

                                            if (actionNormal.containsKey(choice_normal_action)) {
                                                String choice_normal_number;
                                                boolean isANumber;
                                                ArrayList<Character> numbers = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
                                                do {
                                                    isANumber = true;
                                                    System.out.println("\nCombien de fois voulez vous " + actionNormal.get(choice_normal_action) + " ? \n " +
                                                            "Pour annuler le changement mettez \"0\"");
                                                    System.out.print("Nombre de fois : ");
                                                    choice_normal_number = scanner.nextLine();

                                                    int pos = 0;

                                                    while (isANumber && pos < choice_normal_number.length()) {

                                                        if (!numbers.contains(choice_normal_number.charAt(pos))) {
                                                            isANumber = false;
                                                            break;
                                                        }
                                                        pos = pos + 1;
                                                    }
                                                    if (choice_normal_action.equals("2") && isANumber && Integer.parseInt(choice_normal_number) >= falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).getStock().size()) {
                                                        System.out.println("\nVous ne pouvez pas supprimer plus de " + falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).getStock().size()
                                                                + " mots sinon votre phrase sera vide !");
                                                        isANumber = false;
                                                    } else if (!isANumber) {
                                                        System.out.println("\nCe n'est pas un nombre !");
                                                    }

                                                } while (!choice_normal_number.equals("r") && !isANumber);

                                                if (Integer.parseInt(choice_normal_number) != 0) {
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
                                                    System.out.println("\nPhrase obtenue après modifications :\n\"" + falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) -1) + "\"");
                                                }
                                            } else {
                                                choice_normal_type = "";
                                            }
                                        } while (!actionNormal.containsKey(choice_normal_action) && !choice_normal_action.equals("r"));
                                        break;
                                    case "l":
                                        HashMap<String, StringBuilder> listMots = new HashMap<>();
                                        String choice_normal_word;
                                        do {
                                            System.out.println("Choisissez un mot à modifier : ");
                                            for (int i = 0; i < falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).getStock().size(); i++) {
                                                if (falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).getStock().get(i).length() > 1){
                                                    System.out.println(i + 1 + " -> " + falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).getStock().get(i));
                                                    listMots.put(String.valueOf(i + 1), falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).getStock().get(i));
                                                }
                                            }
                                            System.out.println("\na -> Prendre un mot aléatoirement");
                                            listMots.put("a", new StringBuilder("aléatoire"));
                                            System.out.println("r -> Revenir à l'écran précédent");
                                            listMots.put("r", new StringBuilder("revenir"));
                                            choice_normal_word = scanner.nextLine();

                                            if (listMots.containsKey(choice_normal_word)){
                                                do {
                                                    System.out.println("\nChoisissez l'action à effectuer :");
                                                    System.out.println("1 -> Doubler une lettre");
                                                    actionNormal.put("1", "doubler une lettre");
                                                    System.out.println("2 -> Supprimer une lettre");
                                                    actionNormal.put("2", "supprimer une lettre");
                                                    System.out.println("3 -> Déplacer une lettre");
                                                    actionNormal.put("3", "déplacer une lettre");
                                                    System.out.println("4 -> Echanger deux lettres");
                                                    actionNormal.put("4", "échanger deux lettres");
                                                    System.out.println("5 -> Ajouter une lettre");
                                                    actionNormal.put("5", "ajouter une lettre");
                                                    System.out.println("r -> Revenir à l'écran précédent");
                                                    choice_normal_action = scanner.nextLine();

                                                    if (actionNormal.containsKey(choice_normal_action)) {
                                                        String choice_normal_number;
                                                        boolean isANumber;
                                                        ArrayList<Character> numbers = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
                                                        do {
                                                            isANumber = true;
                                                            System.out.println("\nCombien de fois voulez vous " + actionNormal.get(choice_normal_action) + " ? \n" +
                                                                    "Pour annuler le changement mettez \"0\"");
                                                            System.out.print("Nombre de fois : ");
                                                            choice_normal_number = scanner.nextLine();

                                                            int pos = 0;

                                                            while (isANumber && pos < choice_normal_number.length()) {

                                                                if (!numbers.contains(choice_normal_number.charAt(pos))) {
                                                                    isANumber = false;
                                                                    break;
                                                                }
                                                                pos = pos + 1;
                                                            }
                                                            if (choice_normal_action.equals("2") && isANumber &&
                                                                    Integer.parseInt(choice_normal_number) >= falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).getStock().get(Integer.parseInt(choice_normal_word) - 1).length()) {
                                                                System.out.println("\nVous ne pouvez pas supprimer plus de " + falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).getStock().get(Integer.parseInt(choice_normal_word) - 1).length()
                                                                        + " lettres sinon votre mot sera vide !");
                                                                isANumber = false;
                                                            } else if (!isANumber) {
                                                                System.out.println("\nCe n'est pas un nombre !");
                                                            }

                                                        } while (!isANumber);

                                                        int change_number = Integer.parseInt(choice_normal_number);
                                                        if (change_number != 0) {
                                                            int word_pos;
                                                            AbstractMap.SimpleEntry<Integer, StringBuilder> word_to_change;
                                                            if (choice_normal_word.equals("a")){
                                                                word_to_change = falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).RandomWordFromStock();
                                                            }
                                                            else {
                                                                word_pos = Integer.parseInt(choice_normal_word) - 1;
                                                                word_to_change = new AbstractMap.SimpleEntry<>(word_pos, falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).getStock().get(word_pos));
                                                            }
                                                            switch (choice_normal_action) {
                                                                case "1":
                                                                    for (int i = 0; i < change_number; i++) {
                                                                        falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).DoublageLettre(word_to_change);
                                                                    }
                                                                    break;
                                                                case "2":
                                                                    for (int i = 0; i < change_number; i++) {
                                                                        falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).SupprimerLettre(word_to_change);
                                                                    }
                                                                    break;
                                                                case "3":
                                                                    for (int i = 0; i < change_number; i++) {
                                                                        falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).DeplacerLettre(word_to_change);
                                                                    }
                                                                    break;
                                                                case "4":
                                                                    for (int i = 0; i < change_number; i++) {
                                                                        falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).EchangerLettre(word_to_change);
                                                                    }
                                                                    break;
                                                                case "5":
                                                                    for (int i = 0; i < change_number; i++) {
                                                                        falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).AjouterLettre(word_to_change);
                                                                    }
                                                                    break;
                                                            }
                                                            System.out.println("\nPhrase obtenue après modifications :\n\"" + falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) -1) + "\"");
                                                        }
                                                    } else {
                                                        choice_normal_type = "";
                                                        System.out.println("\nPhrase non modifiée");
                                                    }
                                                } while (!actionNormal.containsKey(choice_normal_action) && !choice_normal_action.equals("r"));
                                            }
                                        } while (!listMots.containsKey(choice_normal_word));

                                        break;
                                    case "i":
                                        falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).initStock(falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) - 1).getStr());
                                        System.out.println("\nPhrase obtenue après modifications :\n\"" + falsigram.getNormal().get(Integer.parseInt(choice_normal_sent) -1) + "\"");
                                        break;
                                    case "r":
                                        choice_normal_sent = "";
                                        break;
                                }
                            }while (!choice_normal_type.equals("m") && !choice_normal_type.equals("l") && !choice_normal_type.equals("i") && !choice_normal_type.equals("r"));

                        }
                    } while (!listNormal.containsKey(choice_normal_sent) && !choice_normal_sent.equals("r"));
                }
                case "a" -> {
                    Random random = new Random();
                    if (falsigram.getNormal().size() != 0){
                        for (int i = 0 ; i < falsigram.getNormal().size() ; i++){
                            int size = falsigram.getNormal().get(i).getStock().size() / 4;
                            for (int j = 0 ; j < size ; j++){
                                int action_type = random.nextInt(5);
                                switch (action_type){
                                    case 0 -> {
                                        falsigram.getNormal().get(i).DoublageMot();
                                        break;
                                    }
                                    case 1 -> {
                                        falsigram.getNormal().get(i).SupprimerMot();
                                        break;
                                    }
                                    case 2 -> {
                                        falsigram.getNormal().get(i).EchangerMot();
                                        break;
                                    }
                                    case 3 -> {
                                        falsigram.getNormal().get(i).DeplacerMot();
                                        break;
                                    }
                                    case 4 -> {
                                        falsigram.getNormal().get(i).AjouterMot();
                                        break;
                                    }
                                }
                            }
                            size = falsigram.getNormal().get(i).getStock().size();
                            for (int j = 0 ; j < size ; j++){
                                int size_word = falsigram.getNormal().get(i).getStock().get(j).length() / 4;
                                for (int k = 0 ; k < size_word ; k++){
                                    int action_type = random.nextInt(5);
                                    switch (action_type){
                                        case 0 -> {
                                            falsigram.getNormal().get(i).DoublageLettre(new AbstractMap.SimpleEntry<>(j, falsigram.getNormal().get(i).getStock().get(j)));
                                            break;
                                        }
                                        case 1 -> {
                                            falsigram.getNormal().get(i).SupprimerLettre(new AbstractMap.SimpleEntry<>(j, falsigram.getNormal().get(i).getStock().get(j)));
                                            break;
                                        }
                                        case 2 -> {
                                            falsigram.getNormal().get(i).EchangerLettre(new AbstractMap.SimpleEntry<>(j, falsigram.getNormal().get(i).getStock().get(j)));
                                            break;
                                        }
                                        case 3 -> {
                                            falsigram.getNormal().get(i).DeplacerLettre(new AbstractMap.SimpleEntry<>(j, falsigram.getNormal().get(i).getStock().get(j)));
                                            break;
                                        }
                                        case 4 -> {
                                            falsigram.getNormal().get(i).AjouterLettre(new AbstractMap.SimpleEntry<>(j, falsigram.getNormal().get(i).getStock().get(j)));
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    System.out.println("Phrases après modifications :");
                    for (Normal n : falsigram.getNormal()){
                        System.out.println(n);
                    }
                }
                case "s" -> {
                    if (isAFile){
                        System.out.println("Veuillez entrer le nom du fichier dans lequel il faut sauvegarder les phrases modifiées.\nIl sera placé dans le même répertoire que le fichier initial");
                        System.out.print("Nom du fichier : ");
                        String filename = scanner.nextLine();
                        falsigram.ToFile(filename, isAFile);
                    }
                }

                default -> {
                }
            }
        } while (!s.equals("q"));


    }
}
