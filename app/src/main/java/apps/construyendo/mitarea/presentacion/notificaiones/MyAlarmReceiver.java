package apps.construyendo.mitarea.presentacion.notificaiones;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import apps.construyendo.mitarea.R;
import apps.construyendo.mitarea.datos.entity.TareaEntity;
import apps.construyendo.mitarea.presentacion.View.activity.MainActivity;
import apps.construyendo.mitarea.presentacion.View.fragment.Tareas_Detalle_fragment;
import io.realm.Realm;


public class MyAlarmReceiver extends BroadcastReceiver  {
    public static final int REQUEST_CODE = 12345;
    private NotificationManager notificationManager;
    private final int NOTIFICATION_ID = 1010;
    private AdminSQLiteOpenHelper admin;
    private Cursor fila;
    private SQLiteDatabase bd;
    private String alarma,descripcion,titulo;
    private String list;




    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, MyTestService.class);
        context.startService(i);
        Calendar calendario = Calendar.getInstance();
        int hora, min,dia,mes,ano;
        String cadenaF, cadenaH,fecha_sistema,hora_sistema;

        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH)+1;
        ano = calendario.get(Calendar.YEAR);
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        min = calendario.get(Calendar.MINUTE);
        fecha_sistema=mes+"-"+dia+"-"+ano+" ";
        hora_sistema=hora+":"+min;

        admin = new AdminSQLiteOpenHelper(context, vars.bd, null, vars.version);
        bd = admin.getWritableDatabase();

        if(bd!=null) {
            fila = bd.rawQuery("SELECT * FROM alarma WHERE fecha='"+fecha_sistema+"' AND hora= '"+hora_sistema+"'", null);
            if(fila.moveToFirst()){
                alarma=fila.getString(0);
                titulo=fila.getString(1);
                descripcion =fila.getString(2);
                triggerNotification(context,titulo+"");
            }
        }
        bd.close();





    }

    private void triggerNotification(Context contexto, String t) {
        Intent notificationIntent = new Intent(contexto, MainActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(contexto, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long[] pattern = new long[]{2000, 1000, 2000};




        NotificationCompat.Builder builder = new NotificationCompat.Builder(contexto);
        builder.setContentIntent(contentIntent)


                .setTicker("" )
                .setContentTitle("Mis Tarea")
                .setContentTitle("")
                .setContentText(t)
                .setContentInfo("Info")
                .setColor(Color.parseColor("#71b32a"))
                .setLargeIcon(BitmapFactory.decodeResource(contexto.getResources(), R.drawable.ic_create))
                .setSmallIcon(R.drawable.ic_create)
                .setAutoCancel(true) //Cuando se pulsa la notificación ésta desaparece
                .setSound(defaultSound)
                .setVibrate(pattern);

        Notification notificacion = new NotificationCompat.BigTextStyle(builder)
                .bigText(t)
                .setBigContentTitle("Mis Tareas")
                .setSummaryText("Resumen de Tareas")
                .build();

        notificationManager = (NotificationManager) contexto.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notificacion);
    }

}
