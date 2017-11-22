package apps.construyendo.mitarea.datos.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import apps.construyendo.mitarea.datos.entity.TareaEntity;
import apps.construyendo.mitarea.dominio.model.Tareas;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public class TareaEntityDataMapper {

    public Tareas transformar(TareaEntity tareaEntity){
        Tareas tareas=new Tareas();
        tareas.setTitulo(tareaEntity.getTitulo());
        tareas.setFecha(tareaEntity.getFecha());
        tareas.setHora(tareaEntity.getHora());
        tareas.setActivar(tareaEntity.isActivar());
        return tareas;
    }
    public TareaEntity tranformar(Tareas tareas){
        TareaEntity tareaEntity=new TareaEntity();
        tareaEntity.setTitulo(tareas.getTitulo());
        tareaEntity.setFecha(tareas.getFecha());
        tareaEntity.setHora(tareas.getHora());
        tareaEntity.setActivar(tareas.isActivar());
        return tareaEntity;
    }
    public List<Tareas> transformar(List<TareaEntity> noticiaEntityList) {
        List<Tareas> noticiaModelList = new ArrayList<>();
        for (TareaEntity noticiaEntity : noticiaEntityList) {
            Tareas noticia = transformar(noticiaEntity);
            noticiaModelList.add(noticia);
        }
        return noticiaModelList;
    }
}
