import java.io.*;
import java.util.LinkedList;

public class MenuController {
    private static final String dir = "src/data/menu.dat";
    private Menu menu;

    public MenuController() {
        File file = new File(dir);
        if (file.exists()) {
            menu = (Menu) SerializeDB.readSerializedObject(dir);
        } else {
            file.getParentFile().mkdir();
            menu = new Menu(new LinkedList<MenuItem>());
            SerializeDB.writeSerializedObject(dir, menu);
        }
    }

    private MenuItem searchMenuItem(String name) {
        return menu.getItem(name);
    }

    public void createMenuItem(String name, String description, double price) {
        if (searchMenuItem(name) == null) {
            MenuItem item = new MenuItem(name, description, price);
            menu.addItem(item);
            SerializeDB.writeSerializedObject(dir, menu);
        }
    }

    public void removeMenuItem(String name) throws Exception {
        MenuItem target;
        if ((target = searchMenuItem(name)) != null) {
            if (menu.removeItem(target)) {
                SerializeDB.writeSerializedObject(dir, menu);
            } else {
                throw new Exception("Item not exists!");
            }
        }
    }

    public void updateMenuItem(String oldName, String newName, String newDescription, double newPrice) throws Exception {
        MenuItem target;
        if ((target = searchMenuItem(oldName)) != null) {
            // itemName check
            if (searchMenuItem(newName) != null) {
                throw new Exception("ITEM_NAME_EXISTS");
            }
            target.setName(newName);
            target.setDescription(newDescription);
            target.setPrice(newPrice);
            SerializeDB.writeSerializedObject(dir, menu);
        }
    }

    public void displayMenu() {
        System.out.println(menu);
    }
}
