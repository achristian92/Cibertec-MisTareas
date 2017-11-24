package apps.construyendo.mitarea.datos.network;

import java.util.List;

import apps.construyendo.mitarea.datos.entity.TareaEntity;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public interface RestApi {
    List<TareaEntity> listarTareas() throws Exception;

    TareaEntity guardarTarea(TareaEntity tareaEntity) throws Exception;

    //ACTUALZAR-MIO..3
   // TareaEntity actualizarTarea(TareaEntity tareaEntity) throws Exception;
}
