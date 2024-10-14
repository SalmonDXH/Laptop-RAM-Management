package support;

// Lib model
import model.RAM;

// Lib util
import java.util.ArrayList;

public class Search {
    /*
     * Search by Type
     * 
     * @param Array RAM to traverse, type to check
     * return list of RAMS with the same type
     */
    static public ArrayList<RAM> searchByType(ArrayList<RAM> ar, String type) {
        ArrayList<RAM> output = new ArrayList<>();
        for (RAM r : ar) {
            if (r.getType().equalsIgnoreCase(type)) {
                output.add(r);
            }
        }
        return output;
    }

    /*
     * Search by Bus
     * 
     * @param Array RAM to traverse, bus to check
     * return list of RAMS with the same bus
     */
    static public ArrayList<RAM> searchByBus(ArrayList<RAM> ar, String bus) {
        ArrayList<RAM> output = new ArrayList<>();
        for (RAM r : ar) {
            if (r.getBus().equalsIgnoreCase(bus)) {
                output.add(r);
            }
        }
        return output;
    }

    /*
     * Search by Brand
     * 
     * @param Array ARAM to traverse, brand to check
     * return list of RAMS with the same brand
     */
    static public ArrayList<RAM> searchByBrand(ArrayList<RAM> ar, String brand) {
        ArrayList<RAM> output = new ArrayList<>();
        for (RAM r : ar) {
            if (r.getBrand().equalsIgnoreCase(brand)) {
                output.add(r);
            }
        }
        return output;
    }
}
