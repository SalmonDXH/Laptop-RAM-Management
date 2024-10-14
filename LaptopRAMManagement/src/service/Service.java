package service;
import support.InputSupport;
import object.IRAMDao;
import object.RAMDao;


public class Service implements IService{

    protected IRAMDao rams;

    public Service(){
        rams = new RAMDao();
    }

    // 1. Add item
    @Override
    public void add(){
        do {
            rams.add();
        } while (!InputSupport.getBoolean("Would you like to return to Menu"));
    }
    
    // 2. Search 
    @Override
    public void search(){
        do {
            rams.search();
        } while (!InputSupport.getBoolean("Would you like to return to Menu"));
    }
    
    // 3. Update info
    @Override
    public void update(){
        do {
            rams.update();;
        } while (!InputSupport.getBoolean("Would you like to return to Menu"));
    }
    
    // 4. Delete item
    @Override
    public void delete(){
        do {
            rams.delete();
        } while (!InputSupport.getBoolean("Would you like to return to Menu"));
    }
    
    // 5. Show all item
    @Override
    public void printList(){
        do {
            rams.printList();
        } while (!InputSupport.getBoolean("Would you like to return to Menu"));
    }
    
    // 6. Save
    @Override
    public void save(){
        rams.save();
    }
}
