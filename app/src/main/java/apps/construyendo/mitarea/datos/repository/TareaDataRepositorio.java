package apps.construyendo.mitarea.datos.repository;

import java.util.List;

import apps.construyendo.mitarea.datos.entity.TareaEntity;
import apps.construyendo.mitarea.datos.entity.mapper.TareaEntityDataMapper;
import apps.construyendo.mitarea.datos.repository.datasource.TareaDataSourceFactory;
import apps.construyendo.mitarea.datos.repository.datasource.TareaDatasource;
import apps.construyendo.mitarea.dominio.model.Tareas;
import apps.construyendo.mitarea.dominio.repository.TareaRepositorio;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public class TareaDataRepositorio implements TareaRepositorio {

    private final TareaDataSourceFactory tareaDataSourceFactory;
    private final TareaEntityDataMapper tareaEntityDataMapper;

    public TareaDataRepositorio(TareaDataSourceFactory tareaDataSourceFactory, TareaEntityDataMapper tareaEntityDataMapper) {
        this.tareaDataSourceFactory = tareaDataSourceFactory;
        this.tareaEntityDataMapper = tareaEntityDataMapper;
    }


    /*
    @Override
    public List<Tareas> listarTareas() throws Exception {
        final TareaDatasource tareaDatasource=tareaDataSourceFactory.crearNetworkDatasource();
        List<TareaEntity> tareaEntityList=tareaDatasource.tareaEntityList();
        return tareaEntityDataMapper.transformar(tareaEntityList);
    }*/
    //modificamos hicmos copia abajo 11 data
    @Override
    public List<Tareas> listarTareas(boolean forzarRed) throws Exception {
        final TareaDatasource tareaDatasource=tareaDataSourceFactory.crear(forzarRed);
        List<TareaEntity> tareaEntityList=tareaDatasource.listartarea();
        return tareaEntityDataMapper.transformar(tareaEntityList);
    }



    @Override
    public Tareas crearTarea(Tareas tareas) throws Exception {
        final TareaDatasource tareaDatasource=tareaDataSourceFactory.crearNetworkDatasource();
        TareaEntity tareaEntity=tareaDatasource.crearNoticia(tareaEntityDataMapper.tranformar(tareas));
        return tareaEntityDataMapper.transformar(tareaEntity);

    }

    @Override
    public Tareas actualizarTarea(Tareas tareas) throws Exception {
        return null;
    }

  /*  @Override
    public Tareas actualizarTarea(String id, Tareas tareas) throws Exception {
        final TareaDatasource tareaDatasource=tareaDataSourceFactory.actualizarNetworkDatasource();
        TareaEntity tareaEntity=tareaDatasource.actualizarNotcia(tareaEntityDataMapper.tranformar(id,tareas));
        return tareaEntityDataMapper.transformar(tareaEntity);
    }

*/
}
