package Falsigram;

import java.lang.String;
import java.util.HashMap;

public abstract class Group {
    private String mySentence;
    private String myType;
    private HashMap<String, String> xml_Attributes = new HashMap<>();

    public Group(){

    }

    public Group(String myType) {
        this.myType = myType;
    }

    public String getMyType() {
        return myType;
    }

    public void setMyType(String myType) {
        this.myType = myType;
    }

    public HashMap<String, String> getXml_Attributes() {
        return xml_Attributes;
    }

    public void setXml_Attributes(HashMap<String, String> xml_Attributes) {
        this.xml_Attributes = xml_Attributes;
    }

    public String getMySentence() {
        return mySentence;
    }

    public void setMySentence(String mySentence) {
        this.mySentence = mySentence;
    }

    public abstract void readString(String sentence);


}
