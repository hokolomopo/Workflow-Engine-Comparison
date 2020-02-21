package be.yelido.frameworktest.objects;

public class BillingInfo {
    private String clientName;
    private String billingInfo;
    private int price;
    private boolean accepted = false;

    public BillingInfo() {
    }

    public BillingInfo(String clientName, int price) {
        this.clientName = clientName;
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
                ", price=" + price +
                ", accepted=" + accepted +
                '}';
    }
}
