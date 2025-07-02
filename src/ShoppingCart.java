import java.util.ArrayList;

public class ShoppingCart {

    private static ArrayList<Product> products = new ArrayList<>();

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Clothing clothing) {
        products.add(clothing);
    }

    public void addProduct(Electronics electronics) {
        products.add(electronics);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void calculateTotalCost(ArrayList<Object> cart) {
        double totalCost = 0;
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            totalCost += product.getPrice();
        }
    }
}