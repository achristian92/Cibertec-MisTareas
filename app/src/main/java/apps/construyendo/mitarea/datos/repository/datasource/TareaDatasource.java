package apps.construyendo.mitarea.datos.repository.datasource;

import java.util.List;

import apps.construyendo.mitarea.datos.entity.TareaEntity;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public interface TareaDatasource {
    List<TareaEntity> tareaEntityList() throws Exception;

    TareaEntity crearNoticia(TareaEntity tareaEntity) throws Exception;
}
