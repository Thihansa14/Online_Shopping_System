package Product;

public class Electronic extends Product {
    private String brand;
    private int warrantyPeriod;

    public Electronic(String productID, String productName, int availableItems, double price, String brand, int warrantyPeriod){
        super(productID, productName, availableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String Brand) {
        this.brand = Brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }
    public void setWarrantyPeriod(int WarrantyPeriod) {
        this.warrantyPeriod = WarrantyPeriod;
    }
}

