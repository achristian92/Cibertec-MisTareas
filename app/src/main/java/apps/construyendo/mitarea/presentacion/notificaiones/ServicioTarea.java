package apps.construyendo.mitarea.presentacion.notificaiones;

import apps.construyendo.mitarea.datos.entity.TareaEntity;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Christian 24 on 24/11/2017.
 */

public class ServicioTarea {
    private Realm realm;

    public ServicioTarea(Realm realm) {
        this.realm = realm;
    }

    public void crearTarea(String id,String titulo,String fecha,String hora,String activar){
        //guardar en bd tiemos q llamar
        realm.beginTransaction();

        TareaEntity tareaEntity=realm.createObject(TareaEntity.class,id);
        tareaEntity.setTitulo(titulo);
        tareaEntity.setFecha(fecha);
        tareaEntity.setHora(hora);
        tareaEntity.setActivar(activar);

        realm.commitTransaction();
    }



}
