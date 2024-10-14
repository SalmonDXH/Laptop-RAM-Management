package list;
import java.util.ArrayList;

import support.ProductUI;


public class List extends ArrayList<String> implements IList{
    protected String name;
    
    public List(){
        name = "N/A";
    }
    
    public List(String name){
        this.name = name;
    }
    
    @Override
    public void addElement(String s){
        this.add(s);
    }
    
    @Override
    public void printList(){
        ProductUI.printListString(this, name);
    }
    
    @Override
    public void read(String input){
        String[] ar = input.split(" ");
        for (String s : ar){
            this.add(s);
        }
    }
    
    @Override
    public String getElement(int index){
        return this.get(index);
    }
    
    @Override
    public int listSize(){
        return this.size();
    }

    @Override
    public ArrayList<String> getList(){
        return this;
    }
}
