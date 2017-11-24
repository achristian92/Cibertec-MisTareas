package apps.construyendo.mitarea.presentacion.Presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import apps.construyendo.mitarea.datos.entity.mapper.TareaEntityDataMapper;
import apps.construyendo.mitarea.datos.repository.TareaDataRepositorio;
import apps.construyendo.mitarea.datos.repository.datasource.TareaDataSourceFactory;
import apps.construyendo.mitarea.dominio.executor.JobExecutor;
import apps.construyendo.mitarea.dominio.executor.UIThread;
import apps.construyendo.mitarea.dominio.model.Tareas;
import apps.construyendo.mitarea.dominio.repository.TareaRepositorio;
import apps.construyendo.mitarea.dominio.usecase.ListarTareas;
import apps.construyendo.mitarea.dominio.usecase.UseCase;
import apps.construyendo.mitarea.presentacion.Model.TareasModel;
import apps.construyendo.mitarea.presentacion.Model.mapper.TareaModelDataMapper;
import apps.construyendo.mitarea.presentacion.NetworkUtils;
import apps.construyendo.mitarea.presentacion.View.TareasView;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public class TareasPresenter extends BasePresenter<TareasView> {

    private static final String TAG="TareasPresenter";

    private final ListarTareas listarTareas; //despues de DATOS
    private final TareaModelDataMapper tareaModelDataMapper;//despues de DATOS


    public TareasPresenter(TareasView view) {
        super(view);
        //despues de DATOS
        this.tareaModelDataMapper=new TareaModelDataMapper();

        TareaRepositorio tareaRepositorio=new TareaDataRepositorio(
                new TareaDataSourceFactory(view.context()),
                new TareaEntityDataMapper()
        );
        this.listarTareas=new ListarTareas(
                new JobExecutor(),
                new UIThread(),
                tareaRepositorio
        );
    }



    public void cargarTareas(){

        //despues de DATOS
        view.mostrarLoading();
        //15...data.realm
        this.listarTareas.setParam(NetworkUtils.hayInternet(view.context()));

        this.listarTareas.ejecutar(new UseCase.Callback<List<Tareas>>() {
            @Override
            public void onSuccess(List<Tareas> response) {
                view.ocultarLoading();
                view.mostrarTareas(tareaModelDataMapper.transformar(response));
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG,"onError: ",t);
                view.ocultarLoading();
            }
        });




        /*view.mostrarLoading();

        List<TareasModel> tareasModelList =new ArrayList<>();
        for (int i=0;i<10;i++){
            TareasModel tareasModel =new TareasModel();
            tareasModel.setTitulo("Tarea"+(i+1));
            tareasModel.setFecha("Fecha"+(i+1));
            tareasModel.setHora("Hora"+(i+1));
            tareasModel.setActivar(true);

            tareasModelList.add(tareasModel);
        }
        view.mostrarTareas(tareasModelList);
        view.ocultarLoading();*/




    }
}
