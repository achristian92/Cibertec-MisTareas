package apps.construyendo.mitarea;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

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
