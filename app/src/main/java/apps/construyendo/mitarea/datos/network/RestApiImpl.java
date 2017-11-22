package apps.construyendo.mitarea.datos.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

import apps.construyendo.mitarea.datos.entity.TareaEntity;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public class RestApiImpl implements RestApi {
    private final ApiService apiService;
    private final Context context;


    public RestApiImpl(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.backendless.com/1734FE12-E43F-C123-FFB8-513C6796DA00/6D2297A9-EF4D-7B0D-FF35-4D891E4C3600/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiService = retrofit.create(ApiService.class);
        this.context = context;

    }




    @Override
    public List<TareaEntity> listarTareas() throws Exception {
        if (hayInternet()) {
            Call<List<TareaEntity>> call = apiService.listarTareas();
            Response<List<TareaEntity>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

    @Override
    public TareaEntity guardarTarea(TareaEntity tareaEntity) throws Exception {
        if (hayInternet()) {
            Call<TareaEntity> call = apiService.guardarTarea(tareaEntity);
            Response<TareaEntity> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }


    public boolean hayInternet() {
        boolean hayInternet;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        hayInternet = (networkInfo != null & networkInfo.isConnectedOrConnecting());
        return hayInternet;
    }
}
