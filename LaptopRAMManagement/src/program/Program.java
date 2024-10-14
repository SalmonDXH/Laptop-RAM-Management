package program;

import service.IService;
import service.Service;
import support.InputSupport;
import object.IMenu;
import object.Menu;

public class Program {
    public static void main(String[] args) {
        IService service = new Service();
        IMenu mainMenu = new Menu();
        mainMenu.addElement("Add new RAM"); // 1. Add
        mainMenu.addElement("Search RAM");  // 2. Search
        mainMenu.addElement("Update RAM");  // 3. Update
        mainMenu.addElement("Delete");      // 4. Delete
        mainMenu.addElement("Print list");  // 5. Print list
        mainMenu.addElement("Save");        // 6. Save
        mainMenu.addElement("Quit");        // 7. Quit
        boolean flag = true;
        do {
            mainMenu.printList("MENU");
            int choice = InputSupport.getInt("Enter chocie", 1, 7);

            switch (choice) {
                case 1:
                    service.add();
                    break;
                case 2:
                    service.search();
                    break;
                case 3:
                    service.update();
                    break;
                case 4:
                    service.delete();
                    break;
                case 5:
                    service.printList();
                    break;
                case 6:
                    service.save();
                    break;
                case 7:
                    flag = false;
                    System.out.println("Bye");
                    break;
                default:
                    break;
            }
        } while (flag);
    }

}
