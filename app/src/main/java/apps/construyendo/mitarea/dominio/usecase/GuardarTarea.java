package apps.construyendo.mitarea.dominio.usecase;

import apps.construyendo.mitarea.dominio.executor.PostExecutionThread;
import apps.construyendo.mitarea.dominio.executor.ThreadExecutor;
import apps.construyendo.mitarea.dominio.model.Tareas;
import apps.construyendo.mitarea.dominio.repository.TareaRepositorio;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public class GuardarTarea extends UseCase<Tareas> {

    private final TareaRepositorio tareaRepository;
    private Tareas tareas;

    protected GuardarTarea(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, TareaRepositorio tareaRepository) {
        super(threadExecutor, postExecutionThread);
        this.tareaRepository = tareaRepository;
    }

    @Override
    protected void contruirUseCase() {
        try {
            Tareas tareas=this.tareaRepository.crearTarea(this.tareas);
            notificarUseCaseSatisfactorio(tareas);

        }catch (Exception e){
            notificarUseCaseError(e);
        }
    }
    public void setParams(Tareas tareas){
        this.tareas=tareas;
    }
}
