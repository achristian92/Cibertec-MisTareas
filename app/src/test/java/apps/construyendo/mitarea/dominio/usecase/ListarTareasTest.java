package apps.construyendo.mitarea.dominio.usecase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import apps.construyendo.mitarea.dominio.executor.PostExecutionThread;
import apps.construyendo.mitarea.dominio.executor.ThreadExecutor;
import apps.construyendo.mitarea.dominio.repository.TareaRepositorio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class ListarTareasTest {
    private ListarTareas listarTareas; //1
    @Mock
    private ThreadExecutor mockthreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;
    @Mock
    private TareaRepositorio mockTareaRepositorio;
    @Before
    public void setUp() throws Exception{ //2
        listarTareas=new ListarTareas(mockthreadExecutor,mockPostExecutionThread,mockTareaRepositorio);
    }
    @Test //3
    public void testListarTareasUseCase() throws Exception{
        listarTareas.setParam(true);
        listarTareas.contruirUseCase();
        verify(mockTareaRepositorio).listarTareas(true);
        verifyNoMoreInteractions(mockTareaRepositorio);
    }

}