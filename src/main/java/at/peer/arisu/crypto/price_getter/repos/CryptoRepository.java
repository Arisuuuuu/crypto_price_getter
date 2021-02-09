package at.peer.arisu.crypto.price_getter.repos;

import at.peer.arisu.crypto.price_getter.model.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoRepository extends JpaRepository<Crypto, String> {

}
