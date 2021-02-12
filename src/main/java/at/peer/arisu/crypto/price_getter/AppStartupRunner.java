package at.peer.arisu.crypto.price_getter;

import at.peer.arisu.crypto.price_getter.model.Crypto;
import at.peer.arisu.crypto.price_getter.model.CryptoPrice;
import at.peer.arisu.crypto.price_getter.repos.CryptoPriceRepository;
import at.peer.arisu.crypto.price_getter.repos.CryptoRepository;
import at.peer.arisu.crypto.price_getter.restclient.CryptoServiceGenerator;
import at.peer.arisu.crypto.price_getter.restclient.PriceService;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private CryptoPriceRepository cpr;

    @Autowired
    private CryptoRepository cr;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Crypto> cryptos = new LinkedList<>();
        List<CryptoPrice> cryptoPrices = new LinkedList<>();
        cryptos = cr.getAll();


        for (Crypto c : cryptos) {
            PriceService service = CryptoServiceGenerator.createService(PriceService.class);
            Call<Map<String, Map<String, Double>>> callSync = service.getPrice(c.getName(), "eur");
            try {
                Response<Map<String, Map<String, Double>>> response = callSync.execute();
                Map<String, Map<String, Double>> price = response.body();
                CryptoPrice newPrice = new CryptoPrice(c, (price.get(c.getName().toLowerCase()).get("eur")), new Date(System.currentTimeMillis()));
                cryptoPrices.add(newPrice);
            }catch(Exception ex){

            }

        }
        cpr.saveAll(cryptoPrices);

        System.exit(0);
    }
}
