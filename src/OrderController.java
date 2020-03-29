import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class OrderController {
    private static final String dir = "src/data/order.dat";
    private OrderList orderList;

    public OrderController() {
        File file = new File(dir);
        if (file.exists()) {
            orderList = (OrderList) SerializeDB.readSerializedObject(dir);
        } else {
            file.getParentFile().mkdir();
            orderList = new OrderList(new LinkedList<Order>());
            SerializeDB.writeSerializedObject(dir, orderList);
        }
    }

    public void makeOrder(String roomNum, String remarks, List<MenuItem> items) {
        LocalDateTime dateTime = LocalDateTime.now();


    }

//    public OrderList getOrderListByRoomNum(String roomNum) {
//
//    }
}
