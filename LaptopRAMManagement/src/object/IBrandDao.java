package object;
import list.IList;

public interface IBrandDao {
    IList getBrand();
    
    void printList();

    String updateBrand();
}
