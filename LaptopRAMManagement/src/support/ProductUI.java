package support;

// Lib model
import model.RAM;

// Lib java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProductUI {
    public static void printListString(ArrayList<String> ar, String name) {
        if (ar.isEmpty()) {
            System.out.println("There is nothing on this list");
            return;
        }
        String header = String.format("----------- %s -----------", name);
        System.out.println(header);
        int index = 1;
        for (String s : ar) {
            System.out.println(String.format("%-2d -  %s", index, s));
            index++;
        }
        for (int i = 0; i < header.length(); i++) {
            System.out.print("-");
        }

        System.out.println("\n");
    }

    /*
     * Print by Type
     * 
     * @param Array RAM to traverse
     * - Print out code, type, Production_month_year and quantity
     * 
     */
    public static void printByType(ArrayList<RAM> ar) {
        if (ar.isEmpty()) {
            System.out.println("There is nothing on this list");
            return;
        }
        System.out.println("------------------------- SEARCH BY TYPE -------------------------");
        System.out.println("| INDEX | CODE            | TYPE    | PRODUCTION DATE | QUANTITY |");
        System.out.println("------------------------------------------------------------------");
        int index = 1;
        for (RAM ram : ar) {
            int[] date = ram.getMonthYear();
            String stringDate = String.format("%02d-%04d", date[0], date[1]);
            System.out.println(String.format("| %-5d | %-15s | %-7s | %-15s | %-9s |", index, ram.getCode(), ram.getType(), stringDate, ram.getQuantity()));
            System.out.println("------------------------------------------------------------------");
            index++;
        }
        System.out.println("\n");
    }

    /*
     * Print by Brand
     * 
     * @Param Array RAM to traverse
     * - Print out code, bus, Production_month_year and quantity
     */
    public static void printByBrand(ArrayList<RAM> ar) {
        if (ar.isEmpty()) {
            System.out.println("There is nothing on this list");
            return;
        }
        System.out.println("---------------------------- SEARCH BY BRAND ---------------------------");
        System.out.println("| INDEX | CODE            | BRAND         | PRODUCTION DATE | QUANTITY |");
        System.out.println("------------------------------------------------------------------------");
        int index = 1;
        for (RAM ram : ar) {
            int[] date = ram.getMonthYear();
            String stringDate = String.format("%02d-%04d", date[0], date[1]);
            System.out.println(String.format("| %-5d | %-15s | %-13s | %-15s | %-9s |", index, ram.getCode(),ram.getBrand(), stringDate, ram.getQuantity()));

            index++;
        }
                    System.out.println("------------------------------------------------------------------------");
        System.out.println("\n");
    }

    /*
     * Print by Bus
     * 
     * @Param Array RAM to traverse
     * - Print out code, bus, Production_month_year and quantity
     */
    public static void printByBus(ArrayList<RAM> ar) {
        if (ar.isEmpty()) {
            System.out.println("There is nothing on this list");
            return;
        }
        System.out.println("-------------------------- SEARCH BY BUS -------------------------");
        System.out.println("| INDEX | CODE            | BUS     | PRODUCTION DATE | QUANTITY |");
        System.out.println("------------------------------------------------------------------");
        int index = 1;
        for (RAM ram : ar) {
            int[] date = ram.getMonthYear();
            String stringDate = String.format("%02d-%04d", date[0], date[1]);
            System.out.println(String.format("| %-5d | %-15s | %-8s | %-15s | %-9s |", index, ram.getCode(), ram.getBus(), stringDate, ram.getQuantity()));

            index++;
        }
                    System.out.println("------------------------------------------------------------------");
        System.out.println("\n");
    }

    /*
     * Print with sorting type
     * 
     * @Param Array RAM to traverse
     * 1 - Default
     * 2/3 - Bus - Ascending / Descending
     * 4/5 - Quantity - Ascending / Descending
     * 6/7 - Month year - Ascending / Descending
     */
    public static void printSorted(ArrayList<RAM> ar, int choice) {
        if (ar.isEmpty()) {
            System.out.println("There is nothing on this list");
            return;
        }
        ArrayList<RAM> a = ar;
        switch (choice) {
            case 2: // BUS
                // Acsending
                Collections.sort(a, new Comparator<RAM>() {
                    @Override
                    public int compare(RAM r1, RAM r2) {
                        int bus1 = Integer.parseInt(r1.getBus().substring(0, r1.getBus().length() - 3));
                        int bus2 = Integer.parseInt(r2.getBus().substring(0, r2.getBus().length() - 3));
                        return Integer.compare(bus1, bus2);
                    }
                });

                break;
            case 3:
                // Descending
                Collections.sort(a, new Comparator<RAM>() {

                    @Override
                    public int compare(RAM r1, RAM r2) {
                        int bus1 = Integer.parseInt(r1.getBus().substring(0, r1.getBus().length() - 3));
                        int bus2 = Integer.parseInt(r2.getBus().substring(0, r2.getBus().length() - 3));
                        return Integer.compare(bus2, bus1);
                    }
                });

                break;
            case 4: // Quantity
                Collections.sort(a, new Comparator<RAM>() {
                    @Override
                    public int compare(RAM r1, RAM r2) {

                        return Integer.compare(r1.getQuantity(), r2.getQuantity());
                    }
                });

                break;
            case 5:
                // Descending
                Collections.sort(a, new Comparator<RAM>() {

                    @Override
                    public int compare(RAM r1, RAM r2) {
                        return Integer.compare(r2.getQuantity(), r1.getQuantity());
                    }
                });

                break;
            case 6: // Month Year
                Collections.sort(a, new Comparator<RAM>() {
                    @Override
                    public int compare(RAM r1, RAM r2) {
                        int month1 = r1.getMonthYear()[0];
                        int year1 = r1.getMonthYear()[1];

                        int month2 = r2.getMonthYear()[0];
                        int year2 = r2.getMonthYear()[1];
                        if (year1 == year2) {
                            return Integer.compare(month2, month1);
                        }

                        return Integer.compare(year2, year1);
                    }
                });
                break;
            case 7:
                Collections.sort(a, new Comparator<RAM>() {
                    @Override
                    public int compare(RAM r1, RAM r2) {
                        int month1 = r1.getMonthYear()[0];
                        int year1 = r1.getMonthYear()[1];

                        int month2 = r2.getMonthYear()[0];
                        int year2 = r2.getMonthYear()[1];
                        if (year1 == year2) {
                            return Integer.compare(month1, month2);
                        }

                        return Integer.compare(year1, year2);
                    }
                });
                break;
            default:
                break;
        }

        printList(ar);
    }

    /*
     * Print all list
     * 
     * @Param Array RAM to traverse
     */
    static public void printList(ArrayList<RAM> ar) {
        if (ar.isEmpty()) {
            System.out.println("There is nothing on this list");
            return;
        }
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("| INDEX | CODE            | TYPE    | BUS     | BRAND         | QUANTITY | PRODUCTION DATE | ACTIVE |");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        int index = 1;
        for (RAM ram : ar) {
            int[] date = ram.getMonthYear();
            String stringDate = String.format("%02d-%04d", date[0], date[1]);
            System.out.println(String.format("| %-5d | %-15s | %-7s | %-7s | %-13s | %-9s| %-15s | %-6s |", index, ram.getCode(), ram.getType(), ram.getBus(), ram.getBrand(), ram.getQuantity(), stringDate, ram.getActive()));
            
            index++;
        }
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("\n");
    }

    /*
     * Print Single RAM
     * 
     * @Param Array RAM to traverse
     */
    static public void printSingle(RAM ram) {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("| CODE            | TYPE    | BUS     | BRAND         | QUANTITY | PRODUCTION DATE | ACTIVE |");
        System.out.println("---------------------------------------------------------------------------------------------");
        int[] date = ram.getMonthYear();
        String stringDate = String.format("%02d-%04d", date[0], date[1]);
        System.out.println(String.format("| %-15s | %-7s | %-7s | %-13s | %-9s | %-15s | %-6s |", ram.getCode(), ram.getType(), ram.getBus(), ram.getBrand(), ram.getQuantity(), stringDate, ram.getActive()));
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("\n");
    }
}