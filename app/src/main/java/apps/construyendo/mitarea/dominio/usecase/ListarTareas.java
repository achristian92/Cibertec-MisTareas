package apps.construyendo.mitarea.dominio.usecase;

import java.util.List;

import apps.construyendo.mitarea.dominio.executor.PostExecutionThread;
import apps.construyendo.mitarea.dominio.executor.ThreadExecutor;
import apps.construyendo.mitarea.dominio.model.Tareas;
import apps.construyendo.mitarea.dominio.repository.TareaRepositorio;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public class ListarTareas extends UseCase<List<Tareas>> {
    private final TareaRepositorio tareaRepository;
    private boolean forzarRed;//13 ...data

    public ListarTareas(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, TareaRepositorio tareaRepository) {
        super(threadExecutor, postExecutionThread);
        this.tareaRepository=tareaRepository;
    }
    //13..data
    public void setParam(boolean forzarRed){
        this.forzarRed=forzarRed;
    }

    @Override
    protected void contruirUseCase() {
        try {
            List<Tareas> tareasList=this.tareaRepository.listarTareas(forzarRed); //18..data
            notificarUseCaseSatisfactorio(tareasList);
        } catch (Exception e) {
            notificarUseCaseError(e);
        }
    }
}
