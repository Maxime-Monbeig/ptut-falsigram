package FTB;

import java.util.ArrayList;

public class Component extends Group{
    private ArrayList<Group> myComponents;
    private String mySentence;

    public Component(ArrayList<Group> myComponents, String mySentence, String myType) {
        super(myType);
        this.myComponents = myComponents;
        this.mySentence = mySentence;
    }

    public void addComponent (Group group) {
        this.myComponents.add(group);
    }

    public void readString(String sentence) {
        int x = 0;
        while (x != sentence.length()) {
            if (sentence.charAt(x) == '(') {
                String typeToUse = null;
                int y = x + 1;
                while (sentence.charAt(y) != ' ') {
                    typeToUse = typeToUse + sentence.charAt(y);
                }
                Component myComponent = new Component(new ArrayList<Group>(), mySentence, typeToUse);
            }
        }
    }
}
