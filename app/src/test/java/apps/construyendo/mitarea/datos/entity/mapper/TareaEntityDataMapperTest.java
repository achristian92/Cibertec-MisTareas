package apps.construyendo.mitarea.datos.entity.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import apps.construyendo.mitarea.datos.entity.TareaEntity;
import apps.construyendo.mitarea.dominio.model.Tareas;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Christian 24 on 25/11/2017.
 */
@RunWith(MockitoJUnitRunner.class)//4
public class TareaEntityDataMapperTest { //comparar con lo q viene de datamapper
    private static final String PRUEBA_TITULO="Titulo de Prueba";
    private static final String FECHA="11-25-2017";
    private static final String HORA="13:21";
    private static final String ACTIVAR="true";

    private TareaEntityDataMapper tareaEntityDataMapper;

    @Before // lo q hace es ejecutar este metodo primero//3
    public void setUpp() throws Exception{
        this.tareaEntityDataMapper=new TareaEntityDataMapper();
    }

    @Test
    public void testTransformarTareaEntity(){//comprobamos q el contenido de adentro tenga el correcto
        TareaEntity tareaEntity=createTareaEntityPrueba();//2
        Tareas tareas=tareaEntityDataMapper.transformar(tareaEntity);

        assertThat(tareas, is(instanceOf(Tareas.class)));
        assertThat(tareas.getTitulo(), is(PRUEBA_TITULO));
        assertThat(tareas.getFecha(), is(FECHA));
        assertThat(tareas.getHora(), is(HORA));
        assertThat(tareas.getActivar(), is(ACTIVAR));

    }
    //OTRA PRUEBA
    @Test
    public void testTransformarTareaEntityList(){
        TareaEntity tareaEntity1=new TareaEntity();
        TareaEntity tareaEntity2=new TareaEntity();


        List<TareaEntity> tareaEntityList=new ArrayList<>();
        tareaEntityList.add(tareaEntity1);
        tareaEntityList.add(tareaEntity2);

        List<Tareas> tareaList=tareaEntityDataMapper.transformar(tareaEntityList);
        assertThat(tareaList.get(0),is(instanceOf(Tareas.class)));
        assertThat(tareaList.get(1),is(instanceOf(Tareas.class)));
        assertThat(tareaList.size(),is(2));

    }

    public TareaEntity createTareaEntityPrueba(){//1
        TareaEntity tareaEntity=new TareaEntity();
        tareaEntity.setTitulo(PRUEBA_TITULO);
        tareaEntity.setFecha(FECHA);
        tareaEntity.setHora(HORA);
        tareaEntity.setActivar(ACTIVAR);
        return tareaEntity;
    }
}