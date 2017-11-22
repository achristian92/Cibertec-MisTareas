package apps.construyendo.mitarea.presentacion.View;

import java.util.List;


import apps.construyendo.mitarea.presentacion.Model.Tareas;

/**
 * Created by Christian 24 on 17/11/2017.
 */

public interface TareasView extends LoadingView{
    void verDetalle(Tareas tareas);
    void agregarTarea();
    void mostrarTareas(List<Tareas> tareasList);
}
