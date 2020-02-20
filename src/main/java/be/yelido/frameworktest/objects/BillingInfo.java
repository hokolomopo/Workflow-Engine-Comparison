package be.yelido.frameworktest.objects;

public class BillingInfo {
    private String clientName;
    private String billingInfo;
    private String product;
    private int amount;
    private int price;
    private boolean accepted = false;

    public BillingInfo() {
    }

    public BillingInfo(String clientName, String product, int amount, int price) {
        this.clientName = clientName;
        this.product = product;
        this.amount = amount;
        this.price = price;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(String billingInfo) {
        this.billingInfo = billingInfo;
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

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return "BillingInfo{" +
                "clientName='" + clientName + '\'' +
                ", billingInfo='" + billingInfo + '\'' +
                ", product='" + product + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", accepted=" + accepted +
                '}';
    }
}
