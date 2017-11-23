package apps.construyendo.mitarea.presentacion.Presenter;

import android.util.Log;

import apps.construyendo.mitarea.datos.entity.mapper.TareaEntityDataMapper;
import apps.construyendo.mitarea.datos.repository.TareaDataRepositorio;
import apps.construyendo.mitarea.datos.repository.datasource.TareaDataSourceFactory;
import apps.construyendo.mitarea.dominio.executor.JobExecutor;
import apps.construyendo.mitarea.dominio.executor.UIThread;
import apps.construyendo.mitarea.dominio.model.Tareas;
import apps.construyendo.mitarea.dominio.repository.TareaRepositorio;
import apps.construyendo.mitarea.dominio.usecase.GuardarTarea;
import apps.construyendo.mitarea.dominio.usecase.UseCase;
import apps.construyendo.mitarea.presentacion.Model.TareasModel;
import apps.construyendo.mitarea.presentacion.Model.mapper.TareaModelDataMapper;
import apps.construyendo.mitarea.presentacion.View.TareaDetalleView;

/**
 * Created by Christian 24 on 22/11/2017.
 */
//despues de DATOS
public class TareaDetallePresenter extends BasePresenter<TareaDetalleView> {

    private static final String TAG = "NoticiaDetallePresenter";
    private final GuardarTarea guardarTarea;
    private final TareaModelDataMapper tareaModelDataMapper;

    public TareaDetallePresenter(TareaDetalleView view) {
        super(view);

        this.tareaModelDataMapper=new TareaModelDataMapper();

        TareaRepositorio tareaRepositorio=new TareaDataRepositorio(
                new TareaDataSourceFactory(view.context()),
                new TareaEntityDataMapper()
        );

        this.guardarTarea=new GuardarTarea(
                new JobExecutor(),
                new UIThread(),
                tareaRepositorio
        );
    }

    public void guardarTarea(TareasModel tareasModel){
        view.mostrarLoading();

        this.guardarTarea.setParams(tareaModelDataMapper.transformar(tareasModel));

        this.guardarTarea.ejecutar(new UseCase.Callback<Tareas>() {
            @Override
            public void onSuccess(Tareas response) {
                view.ocultarLoading();
                view.notificarTareaGuardada();
            }

            @Override
            public void onError(Throwable t) {
                view.ocultarLoading();
                Log.e(TAG,"onError",t);
            }
        });
    }
}
