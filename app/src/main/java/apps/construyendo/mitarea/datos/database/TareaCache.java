package apps.construyendo.mitarea.datos.database;

import java.util.List;

import apps.construyendo.mitarea.datos.entity.TareaEntity;

/**
 * Created by Christian 24 on 23/11/2017.
 */

public interface TareaCache {//4
    List<TareaEntity> listar();
    void guardar(List<TareaEntity> tareaEntityList);
    void limpar();
}
