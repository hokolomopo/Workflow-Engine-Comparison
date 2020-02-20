package be.yelido.frameworktest.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private String clientName;
    private String clientType;
    private String product;
    private int amount;
    private int price;

    public Order() {
    }

    public Order(String clientName, String clientType, String product, int amount) {
        this.clientName = clientName;
        this.clientType = clientType;
        this.product = product;
        this.amount = amount;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "clientName='" + clientName + '\'' +
                ", clientType='" + clientType + '\'' +
                ", product='" + product + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}