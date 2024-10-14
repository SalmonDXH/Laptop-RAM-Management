package object;

// List
import list.IList;
import list.List;
import support.InputSupport;

// ArrayList
import java.util.ArrayList;

// io
import io.IO;

public class BrandDao implements IBrandDao{
    
    final private String path = "src/input/Brand.txt";
    protected IList brandList = new List();
    
    public BrandDao(){
        ArrayList<String> ar = IO.read(path);
        
        for (String s : ar){
            brandList.addElement(s);
        }
    }
    
    @Override
    public IList getBrand(){
        return brandList;
    }

    @Override
    public void printList(){
        brandList.printList();
    }

    @Override
    public String updateBrand(){
        brandList.printList();

        int indexBrand = InputSupport.getInt("Input brand index", 1, getBrand().listSize()) - 1;
        String brand = brandList.getElement(indexBrand);
        return brand;
    }
}
