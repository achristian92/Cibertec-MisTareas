package apps.construyendo.mitarea.datos.repository.datasource;

import java.util.List;

import apps.construyendo.mitarea.datos.database.TareaCache;
import apps.construyendo.mitarea.datos.entity.TareaEntity;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public class DiskTareaDatasource implements TareaDatasource{ // 5 ...data

    private final TareaCache tareaCache;

    public DiskTareaDatasource(TareaCache tareaCache) {
        this.tareaCache = tareaCache;
    }

    @Override
    public List<TareaEntity> listartarea() throws Exception {
        return tareaCache.listar();
    }

    @Override
    public TareaEntity crearNoticia(TareaEntity tareaEntity) throws Exception {
        throw  new UnsupportedOperationException("Operacion no valida");
    }

    /*@Override
    public TareaEntity actualizarNotcia(String id, TareaEntity tareaEntity) throws Exception {
        return null;
    }*/
}
