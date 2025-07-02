import java.util.*;

public class Main {
    static WestminsterShoppingManager shopManager = new WestminsterShoppingManager();
    static ShoppingCentre shoppingmall = new ShoppingCentre();
    private static ArrayList<User> userList = new ArrayList<>();

    public static void main(String[] args) {
        shopManager.displayManagerMenue();
    }

    public static void displayUserMenue() {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n******************************\n          USER MENUE\n******************************\n");
            System.out.println("--------------------------------------\nSelect an option:\n1) Signup\n2) Login\n3) Back to the Menue\n 0) Quit");
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
                        userSignup();
                        break;
                    case 2:
                        userLogin();
                        break;
                    case 3:
                        shopManager.displayManagerMenue();
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

    private static boolean isUserNameExists(String name) {
        for (User user : userList) {
            if (user.getUsername().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void userSignup() {
        Scanner input = new Scanner(System.in);

        String username;
        System.out.print("Enter your Username: ");
        username = input.next();
        while (isUserNameExists(username)) {
            System.out.println("Given Username already exists. Please give a different Username.");
            System.out.print("Enter your Username: ");
            username = input.next();
        }

        System.out.print("Enter your password: ");
        String password = input.next();
        System.out.println("User created successfully!");
        User user = new User(username, password);
        userList.add(user);
    }

    public static void userLogin() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = input.next();
        System.out.print("Enter your password: ");
        String password = input.next();

        User loggedUser = null;
        boolean userExists = false;

        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedUser = user;
                userExists = true;
                break;
            }
        }
        if (userExists) {
            System.out.println("Welcome " + loggedUser.getUsername() + "!");
            shoppingmall.userInterface();

        } else {
            System.out.println("Invalid username or password!");

            // Check if the username exists for more specific feedback
            boolean usernameExists = false;
            for (User user : userList) {
                if (user.getUsername().equals(username)) {
                    usernameExists = true;
                    break;
                }
            }

            if (usernameExists) {
                //do nothing
            } else {
                System.out.println("\nIt seems like you do not hava an account. You have to sign up first");
                displayUserMenue();
            }
        }
    }
}
