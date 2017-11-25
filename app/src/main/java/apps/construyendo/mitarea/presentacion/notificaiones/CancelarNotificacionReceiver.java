package apps.construyendo.mitarea.presentacion.notificaiones;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

/**
 * Created by Christian 24 on 25/11/2017.
 */

public class CancelarNotificacionReceiver extends BroadcastReceiver {
    private static final String TAG ="CANCELARNOTIFICACION" ;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG,"onReive; Cancelar");
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
        notificationManagerCompat.cancel(intent.getIntExtra("notificacion_id",0));
    }
}
