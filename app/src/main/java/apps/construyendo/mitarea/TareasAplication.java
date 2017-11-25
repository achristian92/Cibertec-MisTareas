package apps.construyendo.mitarea;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.ArrayList;

import apps.construyendo.mitarea.datos.entity.TareaEntity;
import apps.construyendo.mitarea.presentacion.notificaiones.ServicioTarea;
import io.realm.Realm;

/**
 * Created by Christian 24 on 23/11/2017.
 */

public class TareasAplication extends Application { //2

    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                .build());







    }
}
