package apps.construyendo.mitarea.datos.network;

import java.util.List;

import apps.construyendo.mitarea.datos.entity.TareaEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public interface ApiService {
    @GET("data/PruebaTareas")
    Call<List<TareaEntity>> listarTareas();

    @POST("data/PruebaTareas")
    Call<TareaEntity> guardarTarea(@Body TareaEntity noticiaEntity);
}
