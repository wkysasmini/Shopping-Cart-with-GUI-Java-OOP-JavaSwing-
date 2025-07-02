public class Clothing extends Product{
    private int size;
    private String colour;

    public Clothing(String productID, String productName, int availableItems, double price, String productType, int size, String color) {
        super(productID, productName, availableItems, price, productType);
        this.size = size;
        this.colour = color;
    }

    public int getSize() {
        return size;
    }

    public String getColour() {
        return colour;
    }


    @Override
    public String toString() {
        return "\nClothing Catagory \n" +
                " Product ID: " + productID +
                ",\n Product Name: " + productName +
                ",\n Size: " + size  +
                ",\n Colour: " + colour  +
                ",\n Number of Available Items: " + numAvailable +
                ",\n Price: " + price ;
    }
}
