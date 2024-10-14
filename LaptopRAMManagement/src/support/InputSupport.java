package support;

// scanner
import java.util.Scanner;

public class InputSupport {

    static Scanner sc = new Scanner(System.in);

    // string
    public static String getString(String content) {
        String input;
        do {
            try {
                System.out.print(String.format("  %s:\n> ", content));
                input = sc.nextLine().trim();
                if (input.trim().matches("[a-zA-Z_ ]+")) {
                    return input.trim();
                } else {
                    System.out.println("!!! -   Wrong format  -\n   Input should be Character only");
                }
            } catch (Exception e) {

            }
        } while (true);
    }
    
    
    // integer
    public static int getInt(String content, int min, int max) {
        int input;
        boolean flag = true;
        do {
            try {
                do {
                    if (flag) {
                        flag = false;
                    } else {
                        System.out.println(String.format("!!! - Input should be between %d - %d", min, max));
                    }
                    System.out.print(String.format("    %s (%d - %d):\n> ", content,min,max));
                    input = Integer.parseInt(sc.nextLine());
                    System.out.println("");
                } while (input < min || input > max);
                return input;
            } catch (Exception e) {
                System.out.println("!!! -   Wrong format -\n    Input should be Integer only");
            }
        } while (true);
    }

    // double
    public static double getDouble(String content, int min, int max) {
        double input;
        boolean flag = true;
        do {
            try {
                do {
                    if (flag) {
                        flag = false;
                    } else {
                        System.out.println(String.format("!!! - Input should be between %d - %d", min, max));
                    }
                    System.out.print(String.format("    %s (%d - %d):\n> ", content,min,max));
                    input = Double.parseDouble(sc.nextLine());
                    System.out.println("");
                } while (input < min || input > max);
                return input;
            } catch (Exception e) {
                System.out.println("!!! - Wrong format -\n  Input should be Integer or Double only");
            }
        } while (true);
    }
    
    // boolean
    public static boolean getBoolean(String content) {
        String choice;
        boolean flag = true;
        do {
            if (flag){
                flag = false;
            } else {
                System.out.println("!!! - Wrong format -\n Input should be y or n");
            }
            System.out.print(String.format("%s (y-n):\n> ",content));
            choice = sc.nextLine();
            System.out.println("");
        } while (!(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")));
        
        return choice.equalsIgnoreCase("y");
    }
    
    // String + Digit
    static public String getCode(String content){
        String input;
        do {
            try {
                System.out.print(String.format("  %s:\n> ", content));
                input = sc.nextLine().trim();
                if (input.trim().matches("[a-zA-Z0-9_ ]+")) {
                    return input.trim();
                } else {
                    System.out.println("!!! -   Wrong format  -\n   Input should be Character, Digit and '_' only");
                }
            } catch (Exception e) {

            }
        } while (true);
    }
}
