package shop.entity;

/**
 * Created with IntelliJ IDEA.
 * User: skplanet
 * Date: 13. 3. 28.
 * Time: 오후 3:28
 * To change this template use File | Settings | File Templates.
 */
public class Product {
    public Product() {}

    private int id;
    private String name;
    private int state;
    private int price;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
