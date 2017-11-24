package apps.construyendo.mitarea.datos.network;

import java.util.List;

import apps.construyendo.mitarea.datos.entity.TareaEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public interface ApiService {
    @GET("data/PruebaTareas")
    Call<List<TareaEntity>> listarTareas();

    @POST("data/PruebaTareas")
    Call<TareaEntity> guardarTarea(@Body TareaEntity tareaEntity);

    //ACTUALIZAR-MIO
   // @PUT("data/PruebaTareas/{objectId}")
   // Call<TareaEntity> updateTarea(@Path("id") String objectId,@Body TareaEntity tareaEntity);
}
