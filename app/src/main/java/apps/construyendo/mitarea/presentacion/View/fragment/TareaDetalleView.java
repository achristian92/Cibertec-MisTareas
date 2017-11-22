package apps.construyendo.mitarea.presentacion.View.fragment;

import apps.construyendo.mitarea.presentacion.Model.TareasModel;
import apps.construyendo.mitarea.presentacion.View.LoadingView;

/**
 * Created by Christian 24 on 22/11/2017.
 */
//SE CREO DESPUES DE DATOS
public interface TareaDetalleView extends LoadingView{
    void guardarTarea(TareasModel tareasModel);
    void notificarTareaGuardada();
}
