package list;
import java.util.ArrayList;

public interface IList {
    void addElement(String e);
    
    void printList();
    
    void read(String input);
    
    String getElement(int index);
    
    int listSize();

    ArrayList<String> getList();
}
