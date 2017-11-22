package apps.construyendo.mitarea.presentacion.View;

import java.util.List;


import apps.construyendo.mitarea.presentacion.Model.TareasModel;

/**
 * Created by Christian 24 on 17/11/2017.
 */

public interface TareasView extends LoadingView{
    void verDetalle(TareasModel tareasModel);
    void agregarTarea();
    void mostrarTareas(List<TareasModel> tareasModelList);
}
