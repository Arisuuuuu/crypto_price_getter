package at.peer.arisu.crypto.price_getter;

import at.peer.arisu.crypto.price_getter.model.Crypto;
import at.peer.arisu.crypto.price_getter.repos.CryptoRepository;
import at.peer.arisu.crypto.price_getter.restclient.CryptoServiceGenerator;
import at.peer.arisu.crypto.price_getter.restclient.PriceService;
import at.peer.arisu.crypto.price_getter.restclient.ResponseModel;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private CryptoRepository cr;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Crypto> cryptoList = new LinkedList<>();

        try(Scanner sc = new Scanner(new File("config.conf"))){
            while(sc.hasNextLine()){
                cryptoList.add(new Crypto(sc.nextLine().toLowerCase()));
            }

        }catch(IOException io){
            System.err.println("Config File not found!\nApplication will shut down");
            System.exit(1);
        }


        for (Crypto c : cryptoList) {
            PriceService service = CryptoServiceGenerator.createService(PriceService.class);
            Call<Map<String, Map<String, Double>>> callSync = service.getPrice(c.getCurrency(), "eur");

            try {
                Response<Map<String, Map<String, Double>>> response = callSync.execute();
                Map<String, Map<String, Double>> price = response.body();
                c.setPrice(Double.parseDouble(String.valueOf(price.get(c.getCurrency()).get("eur"))));
                c.setTimestamp(new Date(System.currentTimeMillis()));
            }catch(Exception ex){

            }

        }
        for (Crypto c: cryptoList) {
            cr.save(c);
        }
        System.exit(0);
    }
}
