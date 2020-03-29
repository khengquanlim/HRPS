import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {
    private static final long serialVersionUID = 4485137913238413815L;
    private List<MenuItem> menu;

    public Menu(List<MenuItem> menu) {
        this.menu = menu;
    }

//    public List<MenuItem> getMenu() {
//        return menu;
//    }
//
//    public void setMenu(List<MenuItem> menu) {
//        this.menu = menu;
//    }

    public void addItem(MenuItem item) {
        menu.add(item);
    }

    public MenuItem getItem(String name) {
        for (MenuItem item: menu) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public boolean removeItem(MenuItem item) {
        return menu.remove(item);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("Menu:\nName\tDescription\tPrice\n");
        for (MenuItem item: menu) {
            String s = item.getName() + "\t" + item.getDescription() + "\t" + String.valueOf(item.getPrice()) + "\n";
            sb.append(s);
        }
        return sb.toString();
    }
}
