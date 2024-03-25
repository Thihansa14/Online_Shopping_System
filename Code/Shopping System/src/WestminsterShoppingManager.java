import Product.Product;
import Product.Electronic;
import Product.Clothing;
import org.junit.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    private static ArrayList<Product>productsList;
    private final int maxProduct = 50;

    public WestminsterShoppingManager(){
        this.productsList= new ArrayList<>(maxProduct);
    }


    @Test
    public  void addProduct(){

        if (productsList.size()>maxProduct){
            System.out.println("Maximum no of products added");
            return;
        }else{
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n Do you want to add an electronic or a clothing product?");
            System.out.println("      [1] Add Electronic");
            System.out.println("      [2] Add Clothing");
            System.out.println("----------------------------------------------------------");

            int choice = Validation.intValidatorOneAndTwo("Enter your Choice (1 or 2): ");

            switch (choice){
                case  1:
                    addElectronic();
                    break;
                case 2:
                    addClothing();
                    break;
                default:
                    System.out.println("Invalid Choice,Return to menu!!!");
            }
        }
    }

    @Test
    public void addElectronic(){
        String productID = Validation.charValidation("Enter product ID: ");
        String productName = Validation.stringValidation("Enter product Name: ");
        int numberOfItemsAvailable = Validation.integer("Enter number of items available: ");
        double price = Validation.Double("Enter price: ");
        String brand = Validation.stringValidation("Enter brand: ");
        int warrantyperiod = Validation.integer("Enter warranty period(Weeks): ");
        System.out.println("\n Product added successfully! \n ");

        productsList.add(new Electronic(productID , productName , numberOfItemsAvailable, price, brand, warrantyperiod));
    }

    @Test
    public void addClothing(){
        String productID = Validation.charValidation("Enter Product ID: ");
        String productName = Validation.stringValidation("Enter Product Name: ");
        int numberOfItemsAvailable = Validation.integer("Enter number of items available: ");
        double price = Validation.Double("Enter price: ");
        String size = Validation.sizeValidation("Enter Size(XS, S, M, L, XL, XXL, XXXL): ");
        String colour = Validation.stringValidation("Enter Colour: ");
        System.out.println("Product added successfully! \n");

        productsList.add(new Clothing(productID, productName, numberOfItemsAvailable, price, size, colour));
    }

    @Test
    public  void deleteProduct(){
        Scanner scanner = new Scanner(System.in);


        String productIdToDelete = Validation.charValidation("Enter product ID to Delete:");

        boolean productFound = false;
        // Iterate through the productsList to find and remove the product
        for (Product product : productsList) {
            if (product.getProductID().equals(productIdToDelete)) {
                System.out.println( productIdToDelete +" Deleted !!!");
                productsList.remove(product);
                productFound = true;
                break;
            }
        }

        if (!productFound) {
            System.out.println("Product with ID " + productIdToDelete + " not found.");
        }
    }

    @Test
    public void printProducts() {                                               // Printing the product list
        Collections.sort(productsList, Comparator.comparing(Product::getProductID));

        for (Product product : productsList) {
            System.out.println("Product ID : " + product.getProductID());
            System.out.println("Product Name : " + product.getProductName());
            System.out.println("Available items : " + product.getAvailableItems());
            System.out.println("Price : " + product.getPrice());

            if (product instanceof Electronic) {                                   // Checking whether it is an electronic object
                Electronic electronics = (Electronic) product;                     // If it is an electronic object printing the brand and the warrenty period
                System.out.println("Brand: " + electronics.getBrand());
                System.out.println("Warranty Period: " + electronics.getWarrantyPeriod()+"\n");

            } else if (product instanceof Clothing) {                              // Checking whether it is a clothing object
                Clothing clothing = (Clothing) product;
                System.out.println("Size: " + clothing.getSize());
                System.out.println("Color: " + clothing.getColour()+"\n");
            }
        }
    }

    @Test
    public void saveProductsToFile() {
        String directoryPathForCloths = "Products/Clothing";
        String directoryPathForElectronics = "Products/Electronics";
        String filename = "ProductsList.txt";
        System.out.println("Products have been SAVE to the file!!");

        File directoryC = new File(directoryPathForCloths);
        File directoryE = new File(directoryPathForElectronics);

        // Checking if directories exist, and creating them if they don't
        if (!directoryC.exists()) {
            directoryC.mkdirs();                                  // Creates the directory and any missing parent directories
        }
        if (!directoryE.exists()) {
            directoryE.mkdirs();
        }

        try {
            BufferedWriter writerC = new BufferedWriter(new FileWriter(directoryPathForCloths + "/" + filename));
            BufferedWriter writerE = new BufferedWriter(new FileWriter(directoryPathForElectronics + "/" + filename));

            for (Product product : productsList) {                         // Iterate through the list of products
                if(product.getAvailableItems() >0){                        // Check if the product is available
                    if (product instanceof Clothing clothing) {
                        writerC.write(clothing.getProductID() + "|" + clothing.getProductName() + "|" + clothing.getSize() + "|" + clothing.getColour() + "|" + clothing.getAvailableItems() + "|" + clothing.getPrice() + "|" );
                        writerC.newLine();
                    } else if (product instanceof Electronic electronic) {
                        writerE.write(electronic.getProductID() + "|" + electronic.getProductName() + "|" + electronic.getBrand() + "|" + electronic.getWarrantyPeriod() + "|" + electronic.getAvailableItems() + "|" + electronic.getPrice() + "|" );
                        writerE.newLine();
                    }
                }
            }

            // Close the writers after writing data
            writerC.close();
            writerE.close();

            System.out.println("Successfully saved data to the file: " + filename +"\n");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public  ArrayList<Product> getProductFromFiles() {
        String directoryPathForCloths = "Products/Clothing";                        //Define directory path and filename
        String directoryPathForElectronics = "Products/Electronics";
        String filename = "ProductsList.txt";
        System.out.println("Read From File!!");

        try {
            // Reading Products.Clothing file
            BufferedReader readerC = new BufferedReader(new FileReader(directoryPathForCloths + "/" + filename));
            String line;
            while ((line = readerC.readLine()) != null) {                           // Iterate through each line in the file

                if(!line.trim().isEmpty()){
                    String[] data = line.split("\\|");

                    String productID = data[0];                                     // Extracting data for Clothing products
                    String productName = data[1];
                    String size = (data[2]);
                    String color = data[3];
                    int availableItems = Integer.parseInt(data[4]);
                    double price = Double.parseDouble(data[5]);

                    productsList.add(new Clothing(productID, productName, availableItems, price, size, color));     // Create a new Clothing object and add it to the products list
                }
            }
            readerC.close();

            // Reading Products.Electronics file
            BufferedReader readerE = new BufferedReader(new FileReader(directoryPathForElectronics + "/" + filename));
            while ((line = readerE.readLine()) != null) {

                if(!line.trim().isEmpty()) {
                    String[] data = line.split("\\|");

                    String productID = data[0];
                    String productName = data[1];
                    String brand = (data[2]);
                    int warrantyPeriod = Integer.parseInt(data[3]);
                    int availableItems = Integer.parseInt(data[4]);
                    double price = Double.parseDouble(data[5]);

                    productsList.add(new Electronic(productID, productName, availableItems, price, brand, warrantyPeriod));
                }
            }
            readerE.close();
            return productsList;                    // Return the list of products after reading from files

        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return productsList;
    }

}
