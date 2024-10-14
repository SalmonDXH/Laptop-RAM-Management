package object;

// Lib list
import list.IList;

// lib java
import java.util.ArrayList;

public interface ITypeDao {

    IList getDDR();
    
    IList getLPDDR();
    
    IList getGDDR();
    
    IList getHBM();

    void printList();

    ArrayList<String> getList();

    String getType();
}
