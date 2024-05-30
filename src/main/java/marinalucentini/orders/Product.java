package marinalucentini.orders;

public class Product {
    private Long id;
    private String name;
    private String cathegory;
    private double price;

    public Product(String name, String cathegory, double price, Long id) {
        this.name = name;
        this.cathegory = cathegory;
        this.price = price;
        this.id = id;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCathegory() {
        return cathegory;
    }

    public void setCathegory(String cathegory) {
        this.cathegory = cathegory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cathegory='" + cathegory + '\'' +
                ", price=" + price +
                '}';
    }

}
