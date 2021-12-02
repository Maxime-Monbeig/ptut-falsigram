package FTB;

import java.lang.String;

public abstract class Group {
    private String mySentence;
    private String myType;

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

    public String getMySentence() {
        return mySentence;
    }

    public void setMySentence(String mySentence) {
        this.mySentence = mySentence;
    }


}
