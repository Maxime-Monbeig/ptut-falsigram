package FalsigramV2;

import Falsigram.Component;
import Falsigram.GenerateError;
import Falsigram.Group;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FalsigramV2 {
    private String chemin;
    private String str;
    private ArrayList<Xml> xml = new ArrayList<>();
    private ArrayList<Ftb> ftb = new ArrayList<>();
    private ArrayList<Normal> normal = new ArrayList<>();

    public ArrayList<Normal> getNormal() {
        return normal;
    }

    public ArrayList<Ftb> getFtb() {
        return ftb;
    }

    public ArrayList<Xml> getXml() {
        return xml;
    }

    public String getStr() {
        return str;
    }

    public String getChemin() {
        return chemin;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public FalsigramV2(String chemin) {
        this.chemin = chemin;
        this.Init();
        this.ReadStr();
        System.out.println("\nPhrases normales");
        for (Normal ge : getNormal()){
            System.out.println(ge);
        }
        System.out.println("\nPhrases xml");
        for (Xml g : getXml()){
            System.out.println(g);
        }
        System.out.println("\nPhrases ftb");
        for (Ftb g : getFtb()){
            System.out.println(g);
        }
    }

    public void Init(){
        if (getChemin().charAt(0) == '/' || (getChemin().charAt(0) == 'C' && getChemin().charAt(1) == ':')){
            this.FromFile();
        }
        else {
            this.setStr(getChemin());
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
                str.charAt(pos+1) != '\n' &&
                !(str.charAt(pos+1) == '(' && str.charAt(pos+2) == 'S' && str.charAt(pos+3) == 'E'))){
            ++pos;
            if (!(str.charAt(pos) == ' ' && newStr.charAt(newStr.length() - 1) == ' ')){
                newStr = newStr + str.charAt(pos);
            }

        }
        ++pos;
        newStr = newStr + str.charAt(pos);

        while (str.charAt(pos + 1) == '.'){
            ++pos;
            newStr = newStr + str.charAt(pos);
        }

        return newStr;
    }

    public int getGlobalSize(){
        return this.getNormal().size() + this.getXml().size() + this.getFtb().size();
    }

    public void ReadStr(){
        int current = 0;
        while (current < getStr().length()){
            if (getStr().charAt(current) == '<'){
                String xml_to_add = cutStringXML(getStr(), current);
                xml.add(new Xml(xml_to_add, false));
                current = current + xml_to_add.length();
            }
            else if (getStr().charAt(current) == '('){
                String ftb_to_add = cutStringFTB(getStr(), current);
                ftb.add(new Ftb(ftb_to_add, false));
                current = current + ftb_to_add.length();
            }
            else if (getStr().charAt(current) != ' ' && getStr().charAt(current) != '\n' && getStr().charAt(current) != '\r'){
                String normal_to_add = cutStringNormal(getStr(), current);
                normal.add(new Normal(normal_to_add));
                current = current + normal_to_add.length() - 1;
            }
            current++;
        }
    }

    public void FromFile(){
        try
        {
            File file = new File(chemin);
            FileReader fileR = new FileReader(file);
            BufferedReader fileBR = new BufferedReader(fileR);

            int r;
            StringBuilder contenu = new StringBuilder();
            while((r = fileBR.read())!=-1)
            {
                contenu.append((char)r);
            }
            fileR.close();
            fileBR.close();
            this.setStr(contenu.toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
