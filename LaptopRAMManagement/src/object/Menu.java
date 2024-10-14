package object;
import java.util.ArrayList;
import support.ProductUI;


public class Menu extends ArrayList<String> implements IMenu {
    
    @Override
    public void addElement(String s){
        this.add(s);
    }
    
    @Override
    public void printList(String head){
        ProductUI.printListString(this, head);
    }
}
