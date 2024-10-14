package object;

// model
import model.RAM;

// utility
import java.util.ArrayList;

// io
import io.IO;

// support
import support.InputSupport;
import support.ProductUI;
import support.Search;

public class RAMDao implements IRAMDao {

    ArrayList<RAM> RAMList;
    ITypeDao typeList = new TypeDao();
    IBrandDao brandList = new BrandDao();

    final String path = "src/output/RAMs.txt";

    // Methods
    public RAMDao() {
        RAMList = new ArrayList<>();

        // Get all the rams from save file
        ArrayList<String> inputRAMs = IO.read(path);
        int index = 0;
        for (String line : inputRAMs) {
            try {
                String[] lines = line.split(", ");

                String code = lines[0];
                String type = lines[1];
                String bus = lines[2];
                String brand = lines[3];
                int quantity = Integer.parseInt(lines[4]);
                int[] month_year = { Integer.parseInt(lines[5].substring(0, 2)),
                        Integer.parseInt(lines[5].substring(3, 7)) }; // format: month-year 00-0000
                boolean active = Boolean.parseBoolean(lines[6]);
                RAM newRAM = new RAM(code, type, bus, brand, quantity, month_year,active);
                RAMList.add(newRAM);
            } catch (Exception e) {
                System.out.println(String.format("Line %d has wrong format", index + 1));
            }
            index++;
        }
    }

    // Generate Code
    private String generateCode(String type) {
        if (RAMList.isEmpty()) {
            return String.format("RAM%s_0000", type);
        }
        RAM latestRAM = RAMList.get(RAMList.size() - 1);

        // get latest code
        String lastCode = latestRAM.getCode();

        // Getting 4 digit number at the back of the code
        String lastDigit = lastCode.substring(lastCode.length() - 4, lastCode.length());

        // Turn it into Integer
        int digit = Integer.parseInt(lastDigit)+1; //

        // Generate new code
        String code = String.format("RAM%s_%04d", type, digit); // RAMx_y
        return code;
    }

    // Check exist code
    private int existCode(String code) {
        if (RAMList.isEmpty()) {
            return -1;
        }
        int index = 0;
        for (RAM ram : RAMList) {
            if (ram.getCode().equalsIgnoreCase(code)) {
                return index;
            }
            index++;
        }
        return -1;
    }
    
    private ArrayList<RAM> getActiveList(ArrayList<RAM> ar){
        ArrayList<RAM> activeList = new ArrayList<>();
        for (RAM ram : ar){
            if (ram.getActive()){
                activeList.add(ram);
            }
        }
        return activeList;
    }

    // 1. Add
    @Override
    public void add() {
        // ATTRIBUTES
        // 1. Code
        // 2. Type
        // 3. Bus
        // 4. Brand
        // 5. Quantity
        // 6. Month year

        // TYPE
        // 1 - DDR
        // 2 - LPDDR
        // 3 - GDDR
        // 4 - HBM
        String type = typeList.getType();

        // Code
        String code = generateCode(type);

        // Bus

        String bus = String.format("%dMHZ", InputSupport.getInt("Input bus", 0, 9999));

        // Brand
        String brand = brandList.updateBrand();

        // Quantity
        int quantity = InputSupport.getInt("Input quantity", 0, 1000000);

        // Month_year
        int month = InputSupport.getInt("+ Input month", 1, 12);

        int year = InputSupport.getInt("+ Input year", 1945, 2024);

        int[] month_year = { month, year };

        // Create new RAM
        RAM nRam = new RAM(code, type, bus, brand, quantity, month_year);

        // Import it to the list
        RAMList.add(nRam);

        // Announce to user
        System.out.println("New RAM has been added");
    }

    // 2. Search
    /*
     * 1. Search by Type
     * - Print out code, type, Production_month_year and quantity
     * 
     * 2. Search by Bus
     * - Print out code, bus, Production_month_year and quanity
     * 
     * 3. Search by Brand
     * - Print out code, brand, Production_month_year and quantity
     */
    @Override
    public void search() {

        // Menu
        IMenu searchMenu = new Menu();
        searchMenu.addElement("Search by type");
        searchMenu.addElement("Search by bus");
        searchMenu.addElement("Seach by brand");
        searchMenu.printList("SEARCH");

        // Choice
        int choice = InputSupport.getInt("Enter choice", 1, 3);

        // Switch choice
        switch (choice) {
            case 1: // search by type

                // Get specific type from user
                typeList.printList();
                ArrayList<String> types = typeList.getList();
                int typeChoice = InputSupport.getInt("Enter type index", 1, types.size());
                String typeString = types.get(typeChoice - 1);

                ArrayList<RAM> typeRams = Search.searchByType(RAMList, typeString);
                if (typeRams.isEmpty()) {
                    System.out.println("There are no RAM with this type in the list");
                    break;
                }

                ProductUI.printByType(typeRams);

                break;
            case 2: // Search by Bus
                    // Get specific bus
                String busString = InputSupport.getInt("Enter bus: ", 0, 1000000) + "MHZ";
                ArrayList<RAM> busList = Search.searchByBus(RAMList, busString);

                if (busList.isEmpty()) {
                    System.out.println("There are no RAM with this bus in the list");
                    break;
                }

                ProductUI.printByBus(busList);
                break;
            case 3: // Search by brand

                brandList.printList();
                ArrayList<String> brands = brandList.getBrand().getList();
                int brandChoice = InputSupport.getInt("Enter brand index:", 1, brands.size());
                String brand = brands.get(brandChoice - 1);
                ArrayList<RAM> brandRAMs = Search.searchByBrand(RAMList, brand);
                if (brandRAMs.isEmpty()) {
                    System.out.println("There are no RAM with this brand in the list");
                    break;
                }

                ProductUI.printByBrand(brandRAMs);
                break;
            default:
                break;
        }

    }

    // 3. Update
    /*
     * GET RAM BY INDEX / CODE
     * UPDATE RAM:
     * 1. Type - String
     * 2. Bus - String
     * 3. Brand - String
     * 4. Quantity - Integer
     * 5. Month_Year - Integer[2]
     * 6. Active - Boolean
     */
    @Override
    public void update() {
        IMenu getRAMMenu = new Menu();
        getRAMMenu.addElement("Get by Index");
        getRAMMenu.addElement("Get by Code");
        getRAMMenu.addElement("Return to Menu");
        getRAMMenu.printList("GET RAM");

        int choice = InputSupport.getInt("Enter choice", 1, 3);
        int index = -1;
        switch (choice) {
            case 1: // get by index
                
                ProductUI.printList(RAMList);
                index = InputSupport.getInt("Enter index", 1, RAMList.size()) - 1;
                break;
            case 2: // get by code
                String code = InputSupport.getCode("Enter code");
                index = existCode(code);
                break;
            case 3:
                return;
            default:
                break;
        }
        if (index == -1) {
            System.out.println("This code doesn't exist");
            return;
        }
        RAM updatedRAM = RAMList.get(index);
        do {
            IMenu updateMenu = new Menu();
            ProductUI.printSingle(updatedRAM);
            updateMenu.addElement("Type"); // 1
            updateMenu.addElement("Bus"); // 2
            updateMenu.addElement("Brand"); // 3
            updateMenu.addElement("Quantity"); // 4
            updateMenu.addElement("Production date"); // 5
            updateMenu.addElement("Active"); // 6
            updateMenu.addElement("Quit"); // 7
            updateMenu.printList("UPDATE MENU");
            choice = InputSupport.getInt("Enter choice", 1, 7);
            switch (choice) {
                case 1: // Update type
                    String type = typeList.getType();
                    String oldCode = updatedRAM.getCode();
                    String code = "RAM" + type + oldCode.substring(oldCode.length() - 5, oldCode.length());
                    updatedRAM.setType(type);
                    updatedRAM.setCode(code);
                    break;
                case 2: // Update Bus
                    String bus = String.format("%dMHZ", InputSupport.getInt("Input bus", 0, 9999));
                    updatedRAM.setBus(bus);
                    break;
                case 3: // Update Brand
                    String brand = brandList.updateBrand();
                    updatedRAM.setBrand(brand);
                    break;

                case 4: // Update Quantity
                    int quanity = InputSupport.getInt("Enter new quantity", 0, 1000000);
                    updatedRAM.setQuantity(quanity);
                    break;

                case 5: // Update Production Date
                    int month = InputSupport.getInt("+ Input month", 1, 12);

                    int year = InputSupport.getInt("+ Input year", 1945, 2024);
                    int[] month_year = { month, year };

                    updatedRAM.setMonthYear(month_year);
                    break;

                case 6:
                    boolean active = InputSupport.getBoolean("RAM active");
                    updatedRAM.setActive(active);
                    break;

                case 7:
                    RAMList.set(index, updatedRAM);
                    System.out.println("Update successfully");
                    return;
                default:
                    break;
            }

        } while (InputSupport.getBoolean("Would you like to keep updating this RAM"));
        RAMList.set(index, updatedRAM);
        System.out.println("Update successfully");
    }

    // 4. Delete item
    @Override
    public void delete() {
        IMenu getRAMMenu = new Menu();
        getRAMMenu.addElement("Get by Index");
        getRAMMenu.addElement("Get by Code");
        getRAMMenu.addElement("Return to Menu");
        getRAMMenu.printList("GET RAM");
        int choice = InputSupport.getInt("Enter choice", 1, 3);
        int index = -1;
        switch (choice) {
            case 1: // get by index
                ArrayList<RAM> activeList = getActiveList(RAMList);
                ProductUI.printList(activeList);
                index = InputSupport.getInt("Enter index", 1, activeList.size()) - 1;
                index = existCode(activeList.get(index).getCode());
                break;
            case 2: // get by code
                String code = InputSupport.getCode("Enter code");
                index = existCode(code);
                break;
            case 3:
                return;
            default:
                break;
        }
        if (index == -1) {
            System.out.println("This code doesn't exist");
            return;
        }
        RAM deletedRAM = RAMList.get(index);
        ProductUI.printSingle(deletedRAM);
        if (InputSupport.getBoolean("Would you like to inactive this RAM")){
            RAMList.get(index).setActive(false);
            System.out.println("Successfully inactive this RAM\n");
        }
    }

    // 5. Display all RAMS
    /*
     * 1. Default
     * 2. Bus
     * 3. Quantity
     * 4. Production date
     */
    @Override
    public void printList() {
        IMenu printMenu = new Menu();
        printMenu.addElement("Default");                            //  1
        printMenu.addElement("Bus               -   Ascending");    //  2
        printMenu.addElement("Bus               -   Descending");   //  3
        printMenu.addElement("Quantity          -   Ascending");    //  4
        printMenu.addElement("Quantity          -   Descending");   //  5
        printMenu.addElement("Production date   -   Ascending");    //  6
        printMenu.addElement("Production date   -   Descending");   //  7
        printMenu.addElement("Return to Menu");                     //  8
        printMenu.printList("PRINT WITH SORTING");
        int choice = InputSupport.getInt("Enter choice", 1, 8);
        if (choice == 8){
            return;
        } else {
            ArrayList<RAM> activeList = getActiveList(RAMList);
            ProductUI.printSorted(activeList, choice);
        }
    }

    // 6. Save
    @Override
    public void save() {
        IO.save(RAMList, path);
    }
}
