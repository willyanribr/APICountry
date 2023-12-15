package Service;

import java.util.ArrayList;

import DTO.CountryDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ICountry {
    @GET("api/paises")
    Call<ArrayList<CountryDTO>> getPaisDtoCall();
}
