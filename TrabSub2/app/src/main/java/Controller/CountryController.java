package Controller;

import android.content.Context;

import java.util.ArrayList;

import DTO.CountryDTO;
import Dao.CountryDao;
import Model.Country;
import Retrofit.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryController {
    private static Context context;

    public static void getViaPais(Context context) {
        try {
            retrofit2.Call<ArrayList<CountryDTO>> call = new RetrofitConfig().paisService().getPaisDtoCall();
            call.enqueue(new Callback<ArrayList<CountryDTO>>() {
                @Override
                public void onResponse(Call<ArrayList<CountryDTO>> call, Response<ArrayList<CountryDTO>> response) {
                    ArrayList<CountryDTO> listaPais = response.body();
                    for (int i = 0; i < listaPais.size(); i++) {
                        CountryDTO pais = listaPais.get(i);

                        salvar(pais.getCodigo(), pais.getDescricao(), context);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<CountryDTO>> call, Throwable t) {
                }
            });
        } catch (Exception ex) {
        }
    }

    public static String salvar(int codigo, String descricao, Context context) {
        try {
            Country pais = new Country();

            pais.setCodio(codigo);
            pais.setDescricao(descricao);

            CountryDao.getInstancia(context).insert(pais);
        } catch (Exception ex) {
            return "Erro ao gravar pais." + ex.getMessage();
        }
        return null;
    }

    public static ArrayList<Country> retornar(Context context) {
        return CountryDao.getInstancia(context).getAll();
    }
}
