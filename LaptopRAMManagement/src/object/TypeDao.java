package object;

// lib java
import java.util.ArrayList;
import io.IO;

import list.IList;
import list.List;
import support.InputSupport;
import support.ProductUI;

public class TypeDao implements ITypeDao{
    final String path = "src/input/Type.txt";
    protected IList DDR = new List("DDR");
    protected IList LPDDR = new List("LPDDR");
    protected IList GDDR = new List("GDDR");
    protected IList HBM = new List("HBM");
    
    
    public TypeDao(){
       ArrayList<String> typeList = IO.read(path);
       DDR.read(typeList.get(0));   // DDR
       LPDDR.read(typeList.get(1)); // LPDDR
       GDDR.read(typeList.get(2));  // GDDR
       HBM.read(typeList.get(3));   // HBM
    }
    
    // getter
    @Override
    public IList getDDR(){
        return DDR;
    }
    
    @Override
    public IList getLPDDR(){
        return LPDDR;
    }
    @Override
    public IList getGDDR(){
        return GDDR;
    }
    @Override
    public IList getHBM(){
        return HBM;
    }
    
    public void printList(){
        ArrayList<String> ar = new ArrayList<>();
        ar.addAll(DDR.getList());
        ar.addAll(LPDDR.getList());
        ar.addAll(GDDR.getList());
        ar.addAll(HBM.getList());
        
        ProductUI.printListString(ar, "TYPE LIST");
    }

    @Override
    public ArrayList<String> getList(){
        ArrayList<String> ar = new ArrayList<>();
        ar.addAll(DDR.getList());
        ar.addAll(LPDDR.getList());
        ar.addAll(GDDR.getList());
        ar.addAll(HBM.getList());

        return ar;
    }

    @Override
    public String getType(){
        IMenu typeMenu = new Menu();
        typeMenu.addElement("DDR");
        typeMenu.addElement("LPDDR");
        typeMenu.addElement("GDDR");
        typeMenu.addElement("HBM");
        typeMenu.printList("TYPE");

        int typeChoiceFirst = InputSupport.getInt("Input type", 1, 4);

        String type = "N/A";
        switch (typeChoiceFirst) {
            case 1: // DDR
                this.getDDR().printList();
                int indexDDR = InputSupport.getInt("Input index for DDR", 1, getDDR().listSize());
                type = this.getDDR().getElement(indexDDR - 1);

                break;
            case 2: // LPDDR
                this.getLPDDR().printList();
                int indexLPDDR = InputSupport.getInt("Input index for DDR", 1, this.getLPDDR().listSize());
                type = this.getLPDDR().getElement(indexLPDDR - 1);

                break;
            case 3: // GDDR
                this.getGDDR().printList();
                int indexGDDR = InputSupport.getInt("Input index for DDR", 1, this.getGDDR().listSize());
                type = this.getGDDR().getElement(indexGDDR - 1);

                break;
            case 4: // HBM
                this.getHBM().printList();
                int indexHBM = InputSupport.getInt("Input index for DDR", 1, this.getHBM().listSize());
                type = this.getHBM().getElement(indexHBM - 1);
                break;
        }
        return type;
    }
    
}
