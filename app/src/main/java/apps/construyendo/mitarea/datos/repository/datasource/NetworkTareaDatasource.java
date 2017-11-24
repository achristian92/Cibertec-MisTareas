package apps.construyendo.mitarea.datos.repository.datasource;

import java.util.List;

import apps.construyendo.mitarea.datos.database.TareaCache;
import apps.construyendo.mitarea.datos.entity.TareaEntity;
import apps.construyendo.mitarea.datos.network.RestApi;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public class NetworkTareaDatasource implements TareaDatasource {
    private final RestApi restApi;
    //16
    private final TareaCache tareaCache;


    public NetworkTareaDatasource(RestApi restApi,TareaCache tareaCache) {
        this.restApi = restApi;
        this.tareaCache=tareaCache;
    }



    @Override
    public List<TareaEntity> listartarea() throws Exception {
        List<TareaEntity> tareaEntities=restApi.listarTareas(); //17
        tareaCache.guardar(tareaEntities);
        return tareaEntities;
    }

    @Override
    public TareaEntity crearNoticia(TareaEntity tareaEntity) throws Exception {
        return restApi.guardarTarea(tareaEntity);
    }

   /* @Override
    public TareaEntity actualizarNotcia(String id, TareaEntity tareaEntity) throws Exception {
        return restApi.actualizarTarea(id,tareaEntity);
    }*/


}
