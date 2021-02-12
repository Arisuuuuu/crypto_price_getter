package at.peer.arisu.crypto.price_getter.repos;

import at.peer.arisu.crypto.price_getter.model.CryptoPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoPriceRepository extends JpaRepository<CryptoPrice, Integer> {

}
