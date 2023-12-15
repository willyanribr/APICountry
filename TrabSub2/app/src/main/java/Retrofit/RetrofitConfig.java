package Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import Service.ICountry;

public class RetrofitConfig {
    private Retrofit retrofit;
    private static final String BASE_URL = "https://falabr.cgu.gov.br/";

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public ICountry paisService() {
        return this.retrofit.create(ICountry.class);
    }
}
