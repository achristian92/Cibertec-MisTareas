package apps.construyendo.mitarea.datos.repository.datasource;

import android.content.Context;

import apps.construyendo.mitarea.datos.database.TareaCache;
import apps.construyendo.mitarea.datos.database.TareaCacheImpl;
import apps.construyendo.mitarea.datos.network.RestApi;
import apps.construyendo.mitarea.datos.network.RestApiImpl;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public class TareaDataSourceFactory {
    private final Context context;

    public TareaDataSourceFactory(Context context) {
        this.context = context;
    }

    public TareaDatasource crearNetworkDatasource(){
        RestApi restApi=new RestApiImpl(context);
        //data
        TareaCache tareaCache=new TareaCacheImpl();
        return new NetworkTareaDatasource(restApi,tareaCache);
    }
    //MIO-ACTUALIZAR..1
   /* public TareaDatasource actualizarNetworkDatasource(){
        RestApi restApi=new RestApiImpl(context);
        return new NetworkTareaDatasource(restApi);
    }*/
   //9..data
    public TareaDatasource crearDisckDatasource(){
        TareaCache tareaCache=new TareaCacheImpl();
        return new DiskTareaDatasource(tareaCache);
    }
    //.10 preguntar si hay interner o traer de cache
    public TareaDatasource crear(boolean forzarRed){
        return forzarRed? crearNetworkDatasource():crearDisckDatasource();
    }
}
