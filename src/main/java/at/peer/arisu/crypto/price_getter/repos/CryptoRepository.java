package at.peer.arisu.crypto.price_getter.repos;

import at.peer.arisu.crypto.price_getter.model.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CryptoRepository extends JpaRepository<Crypto, Integer> {

    @Query("select c from Crypto c")
    List<Crypto> getAll();

}
