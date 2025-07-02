public class Electronics extends Product {
    private String brand;
    private int warrantyPeriod;

    public Electronics(String productID, String productName, int numAvailable, double price, String productType, String brand, int warrantyPeriod) {
        super(productID, productName, numAvailable, price, productType);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }


    public String getBrand() {
        return brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    @Override
    public String toString() {
        return "\nElectronics Catagory \n" +
                " Product ID: " + productID +
                ",\n Product Name: " + productName +
                ",\n Brand: " + brand +
                ",\n Warranty Period: " + warrantyPeriod +
                ",\n Number of Available Items: " + numAvailable +
                ",\n Price: " + price ;
    }
}