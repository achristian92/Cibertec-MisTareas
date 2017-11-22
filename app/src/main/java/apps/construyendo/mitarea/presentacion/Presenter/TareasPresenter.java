package apps.construyendo.mitarea.presentacion.Presenter;

import java.util.ArrayList;
import java.util.List;

import apps.construyendo.mitarea.presentacion.Model.Tareas;
import apps.construyendo.mitarea.presentacion.View.TareasView;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public class TareasPresenter extends BasePresenter<TareasView> {
    public TareasPresenter(TareasView view) {
        super(view);
    }
    public void cargarTareas(){
        view.mostrarLoading();
        List<Tareas> tareasList=new ArrayList<>();
        for (int i=0;i<10;i++){
            Tareas tareas=new Tareas();
            tareas.setTitulo("Tarea"+(i+1));
            tareas.setFecha("Fecha"+(i+1));
            tareas.setHora("Hora"+(i+1));
            tareas.setActivar(true);

            tareasList.add(tareas);
        }
        view.mostrarTareas(tareasList);
        view.ocultarLoading();
    }
}
