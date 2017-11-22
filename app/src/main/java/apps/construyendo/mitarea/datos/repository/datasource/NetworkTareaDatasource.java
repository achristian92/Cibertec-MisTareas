package apps.construyendo.mitarea.datos.repository.datasource;

import java.util.List;

import apps.construyendo.mitarea.datos.entity.TareaEntity;
import apps.construyendo.mitarea.datos.network.RestApi;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public class NetworkTareaDatasource implements TareaDatasource {
    private final RestApi restApi;


    public NetworkTareaDatasource(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public List<TareaEntity> tareaEntityList() throws Exception {
        return restApi.listarTareas();
    }

    @Override
    public TareaEntity crearNoticia(TareaEntity tareaEntity) throws Exception {
        return restApi.guardarTarea(tareaEntity);
    }
}
