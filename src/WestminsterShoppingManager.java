import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {
    private static ArrayList<Product>products = new ArrayList<>();
    private final static int maxproduct=50;

    public void displayManagerMenue() {
        Scanner input = new Scanner(System.in);
        boolean running = true;
        System.out.println("\n\n......................................................\n       Welcome to Westminster Shopping Centre\n......................................................\n");

        while (running) {
            System.out.println("\n******************************\n        MANAGER MENUE\n******************************\n");
            System.out.println("--------------------------------------\nSelect an option:\n1) Add a new product\n2) Delete a product\n3) Print the list of the product\n4) Save in a file\n5) Load from file\n6) Open GUI as a User\n0) Quit");
            System.out.println("--------------------------------------");

            int option = 0;
            try {
                System.out.print("\nEnter your option: ");
                boolean isValidInput = false;

                while (!isValidInput) {
                    try {
                        option = input.nextInt();
                        isValidInput = true; // to exit loop if input is valid
                    } catch (InputMismatchException e) {
                        System.out.print("Invalid input. Please enter a valid option: ");
                        input.nextLine(); // discard invalid input
                    }
                }
                switch (option) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        deleteProduct();
                        break;
                    case 3:
                        printListProduct();
                        break;
                    case 4:
                        saveToFile();
                        break;
                    case 5:
                        readFromFile();
                        break;
                    case 6:
                        System.out.println("\n*BEFORE OPEN THE GUI, MANAGER MUST ADD PRODUCTS (Option 1)");
                        Main.displayUserMenue();
                        break;
                    case 0:
                        System.out.println("\nThank you for using the Westminster Shopping Centre\nQuit....\n\n");
                        running = false;
                        System.exit(0); // This line will terminate the program
                        break;
                    default:
                        System.out.println("Invalid option. Please try again. ");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        // Close the scanner outside the loop
        input.close();
    }

    private static boolean isIdExists(String id) {
        for (Product product : products) {
            if (product.getProductID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addProduct() {
        System.out.println("\n\nAdd a New Product\n-----------------");
        Scanner input = new Scanner(System.in);
        System.out.println("Select One Category: Clothing, Electronics\nPress C for Cloths and E for Electronics\n\n");
        System.out.print("Enter the category: ");
        char option = input.nextLine().charAt(0);

        if (option == 'c' || option == 'C') {
            System.out.println("\n------Clothing Category------\n");

            String productId;
            System.out.print("Enter product ID: ");
            productId = input.next();
            while (isIdExists(productId)) {
                System.out.println("Product ID already exists. Please enter a different ID.");
                System.out.print("Enter product ID: ");
                productId = input.next();
            }
            System.out.print("Enter product name: ");
            String productName=input.next();
            System.out.print("Enter number of available items: ");
            int numAvailable=0;
            boolean isValidInput = false;

            while (!isValidInput) {
                try {
                    numAvailable = input.nextInt();
                    isValidInput = true; //  to exit loop if input is valid
                } catch (InputMismatchException e) {
                    System.out.print("Invalid input. Please enter a valid number of available items: ");
                    input.nextLine(); // discard invalid input
                }
            }
            System.out.print("Enter price: ");
            double price = 0d;
            boolean isValidPrice = false;

            while (!isValidPrice) {
                try {
                    price = input.nextDouble();
                    isValidPrice = true; // to exit loop if input is valid
                } catch (InputMismatchException e) {
                    System.out.print("Invalid input. Please enter a valid price: ");
                    input.nextLine(); // discard invalid input
                }
            }
            System.out.print("Enter size: ");
            int size = 0;
            boolean isValidSize = false;
            while (!isValidSize) {
                try {
                    size = input.nextInt();
                    isValidSize = true; // to exit loop if input is valid
                } catch (InputMismatchException e) {
                    System.out.print("Invalid input. Please enter a valid size: ");
                    input.nextLine(); // discard invalid input
                }
            }
            System.out.print("Enter colour: ");
            String colour = input.next();

            Clothing clothing = new Clothing(productId, productName, numAvailable, price, "c", size, colour);
            ShoppingCart shoppingCart = new ShoppingCart();

            if (products.size() >= maxproduct) {
                System.out.println("\nSorry.Products reached their limits.Cannot add more products.");
                displayManagerMenue();
            } else {
                products.add(clothing );
                shoppingCart.addProduct(clothing);
                System.out.println("Product successfully added.");
            }

        }
        else if (option == 'e' || option == 'E') {
            System.out.println("\n------Electronic Category------\n");

            String productId;
            System.out.print("Enter product ID: ");
            productId = input.next();
            while (isIdExists(productId)) {
                System.out.println("Product ID already exists. Please enter a different ID.");
                System.out.print("Enter product ID: ");
                productId = input.next();
            }
            System.out.print("Enter product name: ");
            String productName = input.next();
            System.out.print("Enter number of available items: ");
            int numAvailable = 0;
            boolean isValidInput = false;
            while (!isValidInput) {
                try {
                    numAvailable = input.nextInt();
                    isValidInput = true; //  to exit loop if input is valid
                } catch (InputMismatchException e) {
                    System.out.print("Invalid input. Please enter a valid number of available items: ");
                    input.nextLine(); // discard invalid input
                }
            }
            System.out.print("Enter price: ");
            double price = 0d;
            boolean isValidPrice = false;

            while (!isValidPrice) {
                try {
                    price = input.nextDouble();
                    isValidPrice = true; // exit loop if input is valid
                } catch (InputMismatchException e) {
                    System.out.print("Invalid input. Please enter a valid price: ");
                    input.nextLine(); // discard invalid input
                }
            }
            System.out.print("Enter brand: ");
            String brand = input.next();

            System.out.print("Enter warranty period (years): ");
            int warrantyPeriod = 0;
            boolean isValidSize = false;

            while (!isValidSize) {
                try {
                    warrantyPeriod = input.nextInt();
                    isValidSize = true; // to exit loop if input is valid
                } catch (InputMismatchException e) {
                    System.out.print("Invalid input. Please enter a valid number of years: ");
                    input.nextLine(); // discard invalid input
                }
            }
            Electronics electronics = new Electronics(productId, productName, numAvailable, price, "e", brand, warrantyPeriod);
            ShoppingCart shoppingCart = new ShoppingCart();

            if (products.size() >= maxproduct){
                System.out.println("Products reached their limits. Cannot add more products.");
                displayManagerMenue();
            } else {
                products.add(electronics);
                shoppingCart.addProduct(electronics);
                System.out.println("Product successfully added.");
            }
        }else {
            System.out.println("invalid option.try again");
            addProduct();
        }
    }

    @Override
    public void deleteProduct() {
        System.out.println("\n\nDelete a Product\n------------------");
        ShoppingCart shoppingcart = new ShoppingCart();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the product ID to delete: ");
        String productIdToDelete = input.next();

        int indexToRemove = -1;
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getProductID().equals(productIdToDelete)) {
                System.out.print("\nDetails of the product being deleted: \n");
                System.out.println(product.toString());

                indexToRemove = i;
                break; // Found the product, exit the loop
            }
        }

        if (indexToRemove != -1) {
            shoppingcart.removeProduct(products.get(indexToRemove));
            products.remove(indexToRemove);
            System.out.println("\nProduct deleted successfully.");
            System.out.println("Remaining products in the system: " + products.size());
        } else {
            System.out.println("Product with ID " + productIdToDelete + " not found.");
        }
    }


    @Override
    public void printListProduct() {
        System.out.println("\nPrint List of Products\n-----------------------");
        Collections.sort(products, Comparator.comparing(Product::getProductID));

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println("\nProduct " + (i + 1) + "\n-----------");

            if (product instanceof Clothing) {
                Clothing clothing = (Clothing) product;
                System.out.println("Product Category: Clothing");
                System.out.println("Product ID: "+ product.getProductID()+"\nProduct Name: " + product.getProductName()+"\nNumber of Available Items: " + product.getNumAvailable()+"\nPrice: " + product.getPrice()+"\nSize: " + clothing.getSize()+"\nColour: " + clothing.getColour());

            } else if (product instanceof Electronics) {
                Electronics electronics = (Electronics) product;
                System.out.println("Product Category: Electronic");
                System.out.println("Product ID: "+ product.getProductID()+"\nProduct Name: " + product.getProductName()+"\nNumber of Available Items: " + product.getNumAvailable()+"\nPrice: " + product.getPrice()+"\nBrand: " + electronics.getBrand()+"\nWarranty Period: " + electronics.getWarrantyPeriod());
            }
            System.out.println("\n");
        }
    }

    @Override
    public void saveToFile() {
        System.out.println("\n\nSave to File\n---------------\n");
        try {
            FileWriter writer = new FileWriter("W1953132 Products.txt");
            writer.write("Products Information\n.................\n\n\n");

            Collections.sort(products, Comparator.comparing(Product::getProductID));
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                writer.write("Product " + (i + 1) + "\n-----------\n");

                if (product instanceof Clothing) {
                    Clothing clothing = (Clothing) product;
                    writer.write("Product Category: Clothing\n");
                    writer.write("Product ID: "+ product.getProductID()+"\nProduct Name: " + product.getProductName()+"\nNumber of Available Items: " + product.getNumAvailable()+"\nPrice: " + product.getPrice()+"\nSize: " + clothing.getSize()+"\nColour: " + clothing.getColour());

                } else if (product instanceof Electronics) {
                    Electronics electronics = (Electronics) product;
                    writer.write("Product Category: Electronic\n");
                    writer.write("Product ID: "+ product.getProductID()+"\nProduct Name: " + product.getProductName()+"\nNumber of Available Items: " + product.getNumAvailable()+"\nPrice: " + product.getPrice()+"\nBrand: " + electronics.getBrand()+"\nWarranty Period: " + electronics.getWarrantyPeriod());
                }
                writer.write("\n\n");
            }
            writer.close();
            System.out.println("Products saved to the file 'W1953132 Products.txt' successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the products to file");
        }
    }

    @Override
    public void readFromFile() {
        System.out.println("\n\nLoad File\n---------------\n");
        try {
            File file = new File("W1953132 Products.txt");
            boolean file_exists = file.exists();
            if (file_exists) {
                Scanner file_reader = new Scanner(file);
                while (file_reader.hasNextLine()) {
                    String text = file_reader.nextLine();
                    System.out.println(text);
                }
                file_reader.close();
            }
            else {
                System.out.println("File not found...");
            }
        } catch (IOException e) {
            System.out.println("Error while reading the file.");
        }
    }
}
