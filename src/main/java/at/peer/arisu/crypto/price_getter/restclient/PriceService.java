package at.peer.arisu.crypto.price_getter.restclient;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;
import java.util.Map;

public interface PriceService {

    @GET("simple/price")
    Call<Map<String, Map<String, Double>>> getPrice(@Query("ids") String ids,
                                                    @Query("vs_currencies") String vsCurrencies);
}
