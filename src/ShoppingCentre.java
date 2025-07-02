import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.*;

public class ShoppingCentre extends JFrame {

    // Class variables
    public static ArrayList<Object> productList = new ArrayList<>();
    private HashMap<String, Integer> cart = new HashMap<>();

    // GUI components
    JPanel productDetailsPanel, addCartPanel;
    JButton addToShoppingCartButton, shoppingCartButton;
    JComboBox<String> categoryComboBox;
    JLabel selectCatLable;
    JTable productTable;
    DefaultTableModel model;
    JFrame addToCartFrame;


    // Method to set up the user interface
    public void userInterface() {
        JFrame frame = new JFrame("Westminster Shopping Center");
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        shoppingCartButton = new JButton("Shopping Cart");

        selectCatLable = new JLabel("Select Product Category ");
        selectCatLable.setHorizontalAlignment(JLabel.CENTER);
        selectCatLable.setVerticalAlignment(JLabel.TOP);

        String[] categories = {"All", "Electronics", "Clothing"};
        categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.setSelectedIndex(-1);

        String[] columnNames = {"Product ID", "Product Name", "Category", "Price", "Info"};
        model = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setPreferredSize(new Dimension(500, 200));

        JPanel labelAndComboBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelAndComboBoxPanel.add(selectCatLable);
        labelAndComboBoxPanel.add(categoryComboBox);

        productDetailsPanel = new JPanel();
        productDetailsPanel.setLayout(new BoxLayout(productDetailsPanel, BoxLayout.Y_AXIS));

        addToShoppingCartButton = new JButton("Add to Shopping Cart");
        addCartPanel = new JPanel();
        addCartPanel.setLayout(new FlowLayout());
        addCartPanel.add(addToShoppingCartButton);


        // Adding components to the frame with GridBagLayout
        GridBagConstraints gbPositionLimit = new GridBagConstraints();
        gbPositionLimit.gridx = 2;
        gbPositionLimit.gridy = 1;
        gbPositionLimit.weightx = 2;
        gbPositionLimit.weighty = 1;
        gbPositionLimit.anchor = GridBagConstraints.NORTHEAST;
        frame.add(shoppingCartButton, gbPositionLimit);

        gbPositionLimit.gridy = 1;
        gbPositionLimit.anchor = GridBagConstraints.NORTH;
        frame.add(labelAndComboBoxPanel, gbPositionLimit);

        gbPositionLimit.gridy = 2;
        gbPositionLimit.weighty = 1;
        gbPositionLimit.fill = GridBagConstraints.BOTH;
        frame.add(scrollPane, gbPositionLimit);

        gbPositionLimit.gridy = 6;
        gbPositionLimit.weighty = 3;
        gbPositionLimit.fill = GridBagConstraints.BOTH;
        frame.add(productDetailsPanel, gbPositionLimit);

        gbPositionLimit.gridy = 7;
        gbPositionLimit.weighty = 0;
        gbPositionLimit.anchor = GridBagConstraints.SOUTH;
        frame.add(addCartPanel, gbPositionLimit);

        frame.setVisible(true);

        addListeners();
    }

    // Method to add event listeners to GUI components
    private void addListeners() {

        // Adding an item listener to the categoryComboBox
        categoryComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String category = (String) e.getItem();
                switch (category) {
                    case "All" -> displayAllProducts();
                    case "Electronics" -> displayElectronics();
                    case "Clothing" -> displayClothes();
                }
            }
        });

        // Adding a list selection listener to the productTable
        productTable.getSelectionModel().addListSelectionListener(e -> {
            int row = productTable.getSelectedRow();
            if (row != -1) {
                displayProductDetails(row);
            }
        });

        // Adding an action listener to the shoppingCartButton
        shoppingCartButton.addActionListener(e -> {
            displayShoppingCartDetails();
        });

        // Adding an action listener to the addToShoppingCartButton
        addToShoppingCartButton.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow != -1) {
                String productName = (String) productTable.getValueAt(selectedRow, 1);
                productList.add(productName);
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.calculateTotalCost(productList);
            }
        });
    }

    // Method to display all products in the productTable
    public void displayAllProducts() {
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0);

        for (Product product : ShoppingCart.getProducts()) {
            String category = product.getProductType().equals("e") ? "Electronics" : "Clothing";
            String details = "";
            if (product instanceof Electronics electronic) {
                details = "Brand: " + electronic.getBrand() + ", Warranty Period: " + electronic.getWarrantyPeriod();
            } else if (product instanceof Clothing clothing) {
                details = "Size: " + clothing.getSize() + ", Colour: " + clothing.getColour();
            }
            Object[] rowData = {product.getProductID(), product.getProductName(), category, product.getPrice(), details};
            model.addRow(rowData);
        }
    }

    // Method to display electronics products in the productTable
    public void displayElectronics() {
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0);
        for (Product product : ShoppingCart.getProducts()) {
            if (product instanceof Electronics electronic) {
                Object[] rowData = {electronic.getProductID(), electronic.getProductName(), "Electronics", electronic.getPrice(), "Brand: " + electronic.getBrand() + ", Warranty Period: " + electronic.getWarrantyPeriod()};
                model.addRow(rowData);
            }
        }
    }

    // Method to display clothing products in the productTable
    public void displayClothes() {
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0);
        for (Product product : ShoppingCart.getProducts()) {
            if (product instanceof Clothing clothing) {
                Object[] rowData = {clothing.getProductID(), clothing.getProductName(), "Clothing", clothing.getPrice(), "Size: " + clothing.getSize() + ", Colour: " + clothing.getColour()};
                model.addRow(rowData);
            }
        }
    }

    // Method to display detailed information about a selected product
    private void displayProductDetails(int row) {
        productDetailsPanel.removeAll();
        String productID = (String) productTable.getValueAt(row, 0);
        String productName = (String) productTable.getValueAt(row, 1);
        int numberOfAvailableItems = 0;
        for (Product product : ShoppingCart.getProducts()) {
            if (product.getProductID().equals(productID)) {
                numberOfAvailableItems = product.getNumAvailable();
                break;
            }
        }

        String category = (String) productTable.getValueAt(row, 2);
        Double price = (Double) productTable.getValueAt(row, 3);
        String info = (String) productTable.getValueAt(row, 4);

        JLabel detailsLabel = new JLabel("<html><b>Selected Product - Details</b><br><br>"
                + "\nProduct ID: " + productID + "<br>"
                + "Category: " + category + "<br>"
                + "Product Name: " + productName + "<br>"
                + "Price: " + price + "<br>"
                + "Info: " + info + "<br>"
                + "Items: " + numberOfAvailableItems + "</html>"
        );

        productDetailsPanel.add(detailsLabel);
        productDetailsPanel.revalidate();
        productDetailsPanel.repaint();
    }

    // Method to display shopping cart details in a new frame
    private void displayShoppingCartDetails() {
        addToCartFrame = new JFrame("Shopping Cart");
        addToCartFrame.setLayout(new BorderLayout());

        updateCartData();

        Object[][] cartData = getCartData();
        JTable AddToCartTable = new JTable(cartData, new String[]{"Product", "Quantity", "Price"});
        JScrollPane cartScrollPane = new JScrollPane(AddToCartTable);

        JPanel lowerpanel = lowerPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, cartScrollPane, lowerpanel);
        splitPane.setResizeWeight(0.5);

        addToCartFrame.add(splitPane, BorderLayout.CENTER);
        addToCartFrame.pack();
        addToCartFrame.setVisible(true);
    }

    // Methods for updating cart data
    private void updateCartData() {
        cart.clear();

        for (Object prodName : productList) {
            for (Product prod : ShoppingCart.getProducts()) {
                if (prod.getProductName().equals(prodName)) {
                    String prodDetails = prod.getProductID() + "," + prod.getProductName();
                    cart.put(prodDetails, cart.getOrDefault(prodDetails, 0) + 1);
                    break;
                }
            }
        }
    }

    // This method to get cart data
    private Object[][] getCartData() {
        Object[][] cartData = new Object[cart.size()][3];
        int i = 0;

        for (String prodDetails : cart.keySet()) {
            int qty = cart.get(prodDetails);
            String[] details = prodDetails.split(",");
            String prodName = details[1];
            double totalCost = qty * getPriceForProduct(prodName);
            cartData[i][0] = prodDetails;
            cartData[i][1] = qty;
            cartData[i][2] = totalCost;
            i++;
        }

        return cartData;
    }

    // This method to get the price for a given product
    private double getPriceForProduct(String prodName) {
        for (Product prod : ShoppingCart.getProducts()) {
            if (prod.getProductName().equals(prodName)) {
                return prod.getPrice();
            }
        }
        return 0.0; // Return 0 if product not found (handle this case based on your requirements)
    }

    //This method to create the lower panel of the shopping cart window
    private JPanel lowerPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        String[] labels = {"Total:", "First Purchase Discount(10%):", "Three Items in the same category Discount(20%):", "Final Total:"};
        JLabel[] values = new JLabel[labels.length];

        double totalCost = computeTotalCost(productList);
        double[] calculations = {totalCost, totalCost * 0.1, totalCost * 0.2, totalCost - (totalCost * 0.1 + totalCost * 0.2)};

        for (int i = 0; i < labels.length; i++) {
            panel.add(new JLabel(labels[i]));
            values[i] = new JLabel(String.valueOf(calculations[i]));
            panel.add(values[i]);
        }
        return panel;
    }

    // This method to compute the total cost of items in the cart
    private double computeTotalCost(ArrayList<Object> cartItems) {
        double cost = 0.0;

        for (int i = 0; i < cartItems.size(); i++) {
            Object itemName = cartItems.get(i);
            ArrayList<Product> products = ShoppingCart.getProducts();

            for (int j = 0; j < products.size(); j++) {
                Product item = products.get(j);
                if (item.getProductName().equals(itemName)) {
                    cost += item.getPrice();
                    break;
                }
            }
        }
        return cost;
    }

   /* public static void main(String[] args) {
        ShoppingCentre c = new ShoppingCentre();
        c.userInterface();
    }*/
}