package apps.construyendo.mitarea.datos.repository.datasource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import apps.construyendo.mitarea.datos.database.TareaCache;
import apps.construyendo.mitarea.datos.entity.TareaEntity;
import apps.construyendo.mitarea.datos.network.RestApi;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by Christian 24 on 25/11/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class NetworkTareaDatasourceTest {
    private NetworkTareaDatasource networkTareaDatasource;

    @Mock
    private RestApi mockRestApi;

    @Mock
    private TareaCache mockTareaCache;

    @Before
    public void setUp() throws Exception{
        this.networkTareaDatasource=new NetworkTareaDatasource(mockRestApi,mockTareaCache);
    }
    @Test
    public void testObtenerTareaDesdeRed() throws Exception{
        this.networkTareaDatasource.listartarea();
        verify(mockRestApi).listarTareas();//verificar que tambien llame un metodo listar noticias igual q arriba
    }
    //OTRO PROBAR CON CREAR NOTICIA
    @Test
    public void testCrearTarea() throws Exception{
        TareaEntity tareaEntity=new TareaEntity();

        networkTareaDatasource.crearNoticia(tareaEntity);

        verify(mockRestApi).guardarTarea(tareaEntity);
    }
}