package apps.construyendo.mitarea.dominio.repository;

import java.util.List;


import apps.construyendo.mitarea.dominio.model.Tareas;

/**
 * Created by Christian 24 on 18/11/2017.
 */

public interface TareaRepositorio {

    List<Tareas> listarTareas() throws Exception;

    Tareas crearTarea(Tareas tareas) throws Exception;

    Tareas actualizarTarea(Tareas tareas) throws Exception;
}
