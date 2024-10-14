package model;

public class RAM extends Item{
    // Attributes
    protected String brand;
    protected int quantity;
    protected int[] month_year = new int[2];
    protected boolean active;
    
    //
    public RAM(String code, String type, String bus, String brand, int quantity, int[] month_year){
        super(code,type,bus);
        this.type = type;
        this.brand = brand;
        this.quantity = quantity;
        this.month_year = month_year;
        this.active = true;
    }
    
    public RAM(String code, String type, String bus, String brand, int quantity, int[] month_year, boolean active){
        this(code,type,bus,brand,quantity,month_year);
        this.active = active;
    }
    
    public RAM(){
        this(null,null,null,null,0,new int[2]);
    }
    
    // Getters and setters
    
    // Setters
    public void setBrand(String brand){
        this.brand = brand;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public void setMonthYear(int[] month_year){
            this.month_year = month_year;
    }

    public void setActive(boolean active){
        this.active = active;
    }
    
    // Getters
    public String getBrand(){
        return brand;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public int[] getMonthYear(){
        return month_year;
    }

    public boolean getActive(){
        return active;
    }
    
    // toString
    @Override
    public String toString(){
        return String.format("%s, %s, %d, %02d-%04d, %s",super.toString(),brand,quantity,month_year[0],month_year[1],active);
    }
}
