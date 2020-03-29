import java.io.Serializable;
import java.util.List;

public class OrderList implements Serializable {
    private List<Order> orders;

    public OrderList(List<Order> orders) {
        this.orders = orders;
    }
}
