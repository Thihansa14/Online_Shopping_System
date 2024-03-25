import Product.Product;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    private static void managermenu(){
        WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();
        westminsterShoppingManager.getProductFromFiles();
        boolean  quit = false;
        while (!quit) {
            try {
                System.out.println("--- WESTMINSTER SHOPPING MANAGER MENU ---");
                System.out.println("        1| Add a new product");
                System.out.println("        2| Delete a product");
                System.out.println("        3| Print product list");
                System.out.println("        4| Save to file");
                System.out.println("        0| Exit");
                System.out.println("-----------------------------------------");
                int option = Validation.integer("Enter your choice: ");

                switch (option) {

                    case 1:
                        westminsterShoppingManager.addProduct();
                        break;

                    case 2:
                        westminsterShoppingManager.deleteProduct();
                        break;

                    case 3:
                        westminsterShoppingManager.printProducts();
                        break;

                    case 4:
                        westminsterShoppingManager.saveProductsToFile();
                        break;

                    case 0:
                        System.out.println("Exiting the westminster shopping manager");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice please try Again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please re-enter your option!");
                scanner.next();
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("------ User Login ------");
        System.out.println("        [1] User");
        System.out.println("        [2] Manager");
        System.out.println("------------------------");

        int loginOption  = Validation.intValidatorOneAndTwo("Enter Your Choice: ");

        if (loginOption == 1) {

            try {
                WestminsterShoppingGUI westminsterShoppingGUI = new WestminsterShoppingGUI();
                westminsterShoppingGUI.setVisible(true);
                System.out.println("LogIn Successful!!");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (loginOption == 2) {

                    try {
                        File myObj2 = new File("Cart_Storage.txt");

                        if (myObj2.createNewFile()) {
                            System.out.println("File created");
                        } else {
                            System.out.println("File already Created. \n");
                        }
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                    }
                    managermenu();
        } else {
            System.out.println("Invalid choice. Please choose 1 or 2.");
        }
    }
}