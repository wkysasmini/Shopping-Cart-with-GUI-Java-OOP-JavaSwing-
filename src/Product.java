public abstract class Product {
    protected String productID;
    protected String productName;
    protected int numAvailable;
    protected double price;
    private String productType;

    public Product(String productID, String productName, int availableItems, double price, String productType) {
        this.productID = productID;
        this.productName = productName;
        this.numAvailable = availableItems;
        this.price = price;
        this.productType = productType;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getNumAvailable() {
        return numAvailable;
    }

    public double getPrice() {
        return price;
    }

    public String getProductType() {
        return productType;
    }

}
