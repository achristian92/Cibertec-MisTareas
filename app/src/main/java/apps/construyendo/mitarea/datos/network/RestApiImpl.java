package apps.construyendo.mitarea.datos.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;

import apps.construyendo.mitarea.datos.entity.TareaEntity;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
        //...agrego ultimo
        HttpLoggingInterceptor logging=new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.backendless.com/1734FE12-E43F-C123-FFB8-513C6796DA00/6D2297A9-EF4D-7B0D-FF35-4D891E4C3600/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)//...agrego ultimo
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

  /*  @Override
    public TareaEntity actualizarTarea(String id, TareaEntity tareaEntity) throws Exception {
        if(hayInternet()){
            Call<TareaEntity> callupdate=apiService.updateTarea(id,tareaEntity);
            Response<TareaEntity> response=callupdate.execute();
            if(response.isSuccessful()){
                return response.body();
            }else{
                throw new Exception();
            }

        }else {
            throw new Exception();
        }return null;
    }*/



    public boolean hayInternet() {
        boolean hayInternet;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        hayInternet = (networkInfo != null && networkInfo.isConnectedOrConnecting());
        return hayInternet;
    }
}
