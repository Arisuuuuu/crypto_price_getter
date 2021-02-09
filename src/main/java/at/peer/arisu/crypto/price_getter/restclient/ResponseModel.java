package at.peer.arisu.crypto.price_getter.restclient;

public class ResponseModel {


    private String currency;
    private Double price;

    public Double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ResponseModel(Double price) {
        this.price = price;
    }
}
