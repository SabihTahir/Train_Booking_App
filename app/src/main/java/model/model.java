package model;

public class model {
    private String current;
    private String destination;
    private String price;
    private String quantity;

    public model(){}

    public model(String current, String destination, String price, String quantity) {
        this.current = current;
        this.destination = destination;
        this.price = price;
        this.quantity = quantity;
    }

    public String getCurrent() {
        return current;
    }

    public String getDestination() {
        return destination;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }
}
