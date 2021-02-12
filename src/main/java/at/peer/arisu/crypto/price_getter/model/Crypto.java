package at.peer.arisu.crypto.price_getter.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "crypto")
public class Crypto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crypto_id")
    private Integer id;

    @Column(name = "crypto_name")
    private String name;

    @Column(name = "crypto_shortname")
    private String shortname;

    @Column(name = "crypto_imageurl")
    private String imageUrl;


    @OneToMany(mappedBy="crypto")
    private List<CryptoPrice> prices;

    public Crypto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Crypto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortname='" + shortname + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", prices=" + prices +
                '}';
    }
}



