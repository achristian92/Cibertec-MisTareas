package apps.construyendo.mitarea.presentacion.Model.mapper;

import java.util.ArrayList;
import java.util.List;

import apps.construyendo.mitarea.dominio.model.Tareas;
import apps.construyendo.mitarea.presentacion.Model.TareasModel;

/**
 * Created by Christian 24 on 22/11/2017.
 */
//se creo despues de package DATOS
public class TareaModelDataMapper {
    public TareasModel transformar(Tareas tareas) {
        TareasModel tareasModel = new TareasModel();
        tareasModel.setTitulo(tareas.getTitulo());
        tareasModel.setFecha(tareas.getFecha());
        tareasModel.setHora(tareas.getHora());
        tareasModel.setActivar(tareas.isActivar());
        return tareasModel;
    }

    public Tareas transformar(TareasModel tareasModel) {
        Tareas tareas = new Tareas();
        tareas.setTitulo(tareasModel.getTitulo());
        tareas.setFecha(tareasModel.getFecha());
        tareas.setHora(tareasModel.getHora());
        tareasModel.setActivar(tareasModel.isActivar());
        return tareas;
    }

    public List<TareasModel> transformar(List<Tareas> tarealist) {
        List<TareasModel> tareaModelList = new ArrayList<>();
        for (Tareas tareas : tarealist) {
            TareasModel tareasModel = transformar(tareas);
            tareaModelList.add(tareasModel);
        }
        return tareaModelList;
    }
}
