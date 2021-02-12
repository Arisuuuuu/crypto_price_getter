package at.peer.arisu.crypto.price_getter.model;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "crypto_price")
public class CryptoPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crypto_price_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "crypto_id")
    private Crypto crypto;

    @Column(name = "crypto_price_value")
    private Double price;

    @Column(name = "crypto_price_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public CryptoPrice() {
    }

    public CryptoPrice(Crypto crypto, Double price, Date timestamp) {
        this.crypto = crypto;
        this.price = price;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
