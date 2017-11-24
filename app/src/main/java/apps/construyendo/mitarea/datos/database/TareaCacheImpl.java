package apps.construyendo.mitarea.datos.database;

import java.util.List;

import apps.construyendo.mitarea.datos.entity.TareaEntity;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Christian 24 on 23/11/2017.
 */

public class TareaCacheImpl implements TareaCache { //6
    @Override
    public List<TareaEntity> listar() {
        Realm realm=Realm.getDefaultInstance();
        RealmQuery<TareaEntity> query=realm.where(TareaEntity.class);
        RealmResults<TareaEntity> results=query.findAll();
        realm.close();
        return results;
    }

    @Override
    public void guardar(final List<TareaEntity> tareaEntityList) { //  en caso no tenga interner guardar en cache ---> 7
        //realm trabaja con transsacciones
    final  Realm realm=Realm.getDefaultInstance();
    realm.executeTransaction(new Realm.Transaction() {
        @Override
        public void execute(Realm bgRealm) {
            bgRealm.copyToRealmOrUpdate(tareaEntityList);
        }
    });
    realm.close();
    }

    @Override
    public void limpar() {
    final Realm realm=Realm.getDefaultInstance();
    realm.executeTransaction(new Realm.Transaction() {
        @Override
        public void execute(Realm bgRealm) {
            bgRealm.deleteAll();
        }
    });
    }
}
