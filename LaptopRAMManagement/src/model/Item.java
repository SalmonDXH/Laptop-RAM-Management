
package model;


abstract class Item {
    // Attributes
    protected String code;
    protected String type;
    protected String bus;
    
    
    public Item(String code, String type, String bus){
        this.code = code;
        this.type = type;
        this.bus = bus;
    }
    
    public Item(){
        this(null,null,null);
    }
    
    
    // Setters and getters
    
    // Setters
    public void setCode(String code){
        this.code = code;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public void setBus(String bus){
        this.bus = bus;
    }
    
    // Getters
    public String getCode(){
        return code;
    }
    
    public String getType(){
        return type;
    }
    
    public String getBus(){
        return bus;
    }
    
    // toString
    public String toString(){
        return String.format("%s, %s, %s",code,type,bus);
    }
}
