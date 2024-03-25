package Product;

public abstract class Product {
    private String productID;
    private String productName;
    private int availableItems;
    private double price;

    public Product(String productID, String productName, int availableItems, double price){

        this.productID = productID;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;

    }

    public Product(){}

    public String getProductID() {
        return productID;
    }
    public void setProductID( String ProductID){
        this.productID = ProductID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String ProductName) {
        this.productName = ProductName;
    }

    public int getAvailableItems() {
        return availableItems;
    }
    public void setAvailableItems(int AvailableItems) {
        this.availableItems = AvailableItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
