import java.io.IOException;
import java.io.OutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ServiceUI {
    private MenuController mc;
    private OrderController oc;

    public ServiceUI() {
        mc = new MenuController();
        oc = new OrderController();
    }

    public void launch() throws IOException {
        int option = 0;
        options();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the option: ");
        option = in.nextInt();
        while (option != 6) {
            switch (option) {
                case 1:
                    mc.displayMenu();
                    break;
                case 2:
                    try {
                        System.out.print("Enter the item name: ");
                        in.nextLine();
                        String name = in.nextLine();
                        if (name.trim().equals("")) throw new Exception("Name cannot be blank");
                        System.out.print("Enter the item description: ");
                        String description = in.nextLine();
                        System.out.print("Enter the price: ");
                        double price = in.nextDouble();
                        if (price < 0) throw new Exception("Price cannot be negative");
                        mc.createMenuItem(name, description, price);
                        System.out.println("Item created!\n");
                    } catch (InputMismatchException e) {
                        System.out.println("Warning: Invalid input!");
                    } catch (Exception e) {
                        System.out.println("Warning: " + e.getMessage());
                    } finally {
                        in.nextLine();
                        break;
                    }
                case 3:
                    try {
                        String oldName, newName, newDescription;
                        oldName = newName = newDescription = null;
                        double newPrice = -1;
                        System.out.print("Enter the item name to update: ");
                        in.nextLine();
                        oldName = in.nextLine();
                        System.out.print("Update item name? (y/n) ");
                        if (in.nextLine().charAt(0) == 'y') {
                            System.out.println("Enter updated name: ");
                            newName = in.nextLine();
                        }
                        System.out.print("Update item description? (y/n) ");
                        if (in.nextLine().charAt(0) == 'y') {
                            System.out.print("Enter updated description: ");
                            newDescription = in.nextLine();
                        }
                        System.out.print("Update item price? (y/n) ");
                        if (in.nextLine().charAt(0) == 'y') {
                            System.out.print("Enter updated price (negative price won't be accepted): ");
                            newPrice = in.nextDouble();
                        }
                        mc.updateMenuItem(oldName, newName, newDescription, newPrice);
                        System.out.println("Item information updated!\n");
                    } catch (InputMismatchException e) {
                        System.out.println("Warning: Invalid input!");
                    } catch (Exception e) {
                        System.out.println("Warning: " + e.getMessage());
                    } finally {
                        break;
                    }
                case 4:
                    try {
                        String name;
                        System.out.print("Enter the item name to remove:");
                        in.nextLine();
                        name = in.nextLine();
                        mc.removeMenuItem(name);
                        System.out.println("Item removed!\n");
                    } catch (Exception e) {
                        System.out.println("Warning: " + e.getMessage());
                    } finally {
                        break;
                    }

            }
            options();
            System.out.print("Enter the option: ");
            option = in.nextInt();
        }
    }

    public static void options() {
        System.out.println("Select your option: ");
        System.out.println("1. Display Menu");
        System.out.println("2. Create Menu Item");
        System.out.println("3. Update Menu Item");
        System.out.println("4. Remove Menu Item");
        System.out.println("5. Make an order");
        System.out.println("6. Go back");
    }

}
