package tracker.app.fursa.moneytracker.data;

/**
 * Created by Ilya Fursa on 24.04.2017.
 */

public class Product {
    private String title;
    private int price;
    private String date;
    private String type;

    public Product(String title, int price, String date, String type) {
        this.title = title;
        this.price = price;
        this.date = date;
        this.type = type;
    }

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public Product() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
