package apps.construyendo.mitarea.datos.repository.datasource;

import android.content.Context;

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
        return new NetworkTareaDatasource(restApi);
    }
}
