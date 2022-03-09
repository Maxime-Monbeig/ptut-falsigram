package Falsigram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Falsigram {
    private String str_init;
    private String str_final;
    private ArrayList<Component> xml = new ArrayList<>();
    private ArrayList<Component> ftb = new ArrayList<>();
    private ArrayList<GenerateError> normal = new ArrayList<>();

    public ArrayList<GenerateError> getNormal() {
        return normal;
    }

    public ArrayList<Component> getFtb() {
        return ftb;
    }

    public ArrayList<Component> getXml() {
        return xml;
    }

    public String getStr_final() {
        return str_final;
    }

    public String getStr_init() {
        return str_init;
    }

    public void setStr_final(String str_final) {
        this.str_final = str_final;
    }

    public void setStr_init(String str_init) {
        this.str_init = str_init;
    }

    public Falsigram(String str_init) {
        this.str_init = str_init;
        this.Init();
        this.ReadStr_final();
        System.out.println("Phrases normales \n");
        for (GenerateError ge : getNormal()){
            System.out.println(ge);
        }
        System.out.println("Phrases xml \n");
        for (Group g : getXml()){
            System.out.println(g);
        }
        System.out.println("Phrases ftb \n");
        for (Group g : getFtb()){
            System.out.println(g);
        }
    }

    public void Init(){
        if (getStr_init().charAt(0) == '/'){
            this.FromFile();
        }
        else {
            this.setStr_final(getStr_init());
        }
    }

    public String cutStringXML (String str, int pos){
        String newStr = "";
        int x = 0;
        do{
            if (str.charAt(pos) == '<') {
                ++x;
            }
            else if (str.charAt(pos) == '/') {
                x = x - 2;
            }
            newStr = newStr + str.charAt(pos);
            ++pos;
        }while (x > 0);

        while (str.charAt(pos) != '>'){
            newStr = newStr + str.charAt(pos);
            ++pos;
        }
        newStr = newStr + str.charAt(pos);
        return newStr;
    }

    public String cutStringFTB (String str, int pos){
        String newStr = "";
        int x = 0;
        do{
            if (str.charAt(pos) == '(') {
                ++x;
            }
            else if (str.charAt(pos) == ')') {
                --x;
            }
            newStr = newStr + str.charAt(pos);
            ++pos;
        }while (x > 0);

        return newStr;
    }

    public String cutStringNormal (String str, int pos){
        String newStr = "";
        newStr = newStr + str.charAt(pos);
        while ((str.charAt(pos+1) != '.' &&
                str.charAt(pos+1) != '!' &&
                str.charAt(pos+1) != '?' &&
                str.charAt(pos+1) != '<' &&
                !(str.charAt(pos+1) == '(' && str.charAt(pos+2) == 'S' && str.charAt(pos+3) == 'E'))){
            ++pos;
            newStr = newStr + str.charAt(pos);
        }

        return newStr;
    }

    public void ReadStr_final(){
        int current = 0;
        while (current < getStr_final().length()){
            if (getStr_final().charAt(current) == '<'){
                String xml_to_add = cutStringXML(getStr_final(), current);
                xml.add(new Component(xml_to_add));
                current = current + xml_to_add.length();
            }
            else if (getStr_final().charAt(current) == '('){
                String ftb_to_add = cutStringFTB(getStr_final(), current);
                ftb.add(new Component(ftb_to_add));
                current = current + ftb_to_add.length();
            }
            else if (getStr_final().charAt(current) != ' '){
                String normal_to_add = cutStringNormal(getStr_final(), current);
                normal.add(new GenerateError(normal_to_add));
                current = current + normal_to_add.length() - 1;
            }
            current++;
        }
    }

    public void FromFile(){
        try
        {
            File file = new File(str_init);
            FileReader fileR = new FileReader(file);
            BufferedReader fileBR = new BufferedReader(fileR);

            int r = 0;
            StringBuilder contenu = new StringBuilder();
            while((r = fileBR.read())!=-1)
            {
                contenu.append((char)r);
            }
            fileR.close();
            fileBR.close();
            this.setStr_final(contenu.toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
