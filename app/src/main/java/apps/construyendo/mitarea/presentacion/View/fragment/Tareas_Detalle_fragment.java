package apps.construyendo.mitarea.presentacion.View.fragment;



import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import apps.construyendo.mitarea.R;
import apps.construyendo.mitarea.presentacion.Model.TareasModel;
import apps.construyendo.mitarea.presentacion.Presenter.TareaDetallePresenter;
import apps.construyendo.mitarea.presentacion.View.TareaDetalleView;
import apps.construyendo.mitarea.presentacion.View.activity.MainActivity;
import apps.construyendo.mitarea.presentacion.notificaiones.AdminSQLiteOpenHelper;
import apps.construyendo.mitarea.presentacion.notificaiones.CancelarNotificacionReceiver;
import apps.construyendo.mitarea.presentacion.notificaiones.MyAlarmReceiver;
import apps.construyendo.mitarea.presentacion.notificaiones.vars;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tareas_Detalle_fragment extends Fragment implements TareaDetalleView,View.OnClickListener {

    Toolbar toolbar_detalle;
    EditText eedit_titulo,eedit_fecha,eedit_hora;
    Switch aswitch;
    private TareasModel tareasModel;
    private static final String ARG_TAREAS = "fragment.NOTICIADETALLEFRAFMENT.ARG_NOTICIA";
    private Button btn_guardartarea;
    private ProgressBar progressBar;

    //ADICIONAL PARA NOTIFICACIONES
    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase bd;
    private ContentValues registro;
    private int diaS, mesS, anoS,horaS,minutosS;
    Calendar calendario = Calendar.getInstance();
    int hora, min,dia,mes,ano;
    String fecha_sistema,hora_sistema;


    //despues de DATOS
    private TareaDetallePresenter tareaDetallePresenter;

    //CREAMOS  RECUPERAR LOS DATOS ENVIADOS
    //(1)
    public static  Tareas_Detalle_fragment newInstance(TareasModel tareasModel) {

        Tareas_Detalle_fragment f = new Tareas_Detalle_fragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TAREAS, tareasModel);
        f.setArguments(args);
        return f;

    }
    //DEFECTO SE CREA
    public Tareas_Detalle_fragment() {
        // Required empty public constructor
    }

    //(2)unicamente se va a llarmar cuando se esta creando o recreando
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            tareasModel =getArguments().getParcelable(ARG_TAREAS);
        }
    }
    //DEFECTO SE CREA
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tareas_detalle, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState){
        super.onViewCreated(view,saveInstanceState);
        //ADIONAL PARA NOTIFICACIONES
        admin = new AdminSQLiteOpenHelper(getActivity(), vars.bd, null, vars.version);
        bd = admin.getWritableDatabase();
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH)+1;
        ano = calendario.get(Calendar.YEAR);
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        min = calendario.get(Calendar.MINUTE);
        fecha_sistema=mes+"-"+dia+"-"+ano+" ";
        hora_sistema = hora+":"+min;
        servicio();


        eedit_titulo=view.findViewById(R.id.edit_titulo);
        eedit_fecha=view.findViewById(R.id.edit_fecha);
        eedit_hora=view.findViewById(R.id.edit_hora);
        aswitch =view.findViewById(R.id.switch1);
        toolbar_detalle=view.findViewById(R.id.toolbar_princi);
        btn_guardartarea=view.findViewById(R.id.btn_guardar);
        progressBar=view.findViewById(R.id.progress);


        showToolbar("Detalle de Mi Tarea",true);
        initUI();

        tareaDetallePresenter=new TareaDetallePresenter(this);

        btn_guardartarea.setOnClickListener(this);

        eedit_fecha.setOnClickListener(this);
        eedit_hora.setOnClickListener(this);




    }


    public void servicio() {
        Intent intent = new Intent(getActivity(), MyAlarmReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(getActivity(), MyAlarmReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long firstMillis = System.currentTimeMillis(); //first run of alarm is immediate // aranca la palicacion
        int intervalMillis = 1 * 3 * 1000; //3 segundos
        AlarmManager alarm = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, intervalMillis, pIntent);
    }








    private void initUI(){
        if(tareasModel !=null){
            eedit_titulo.setText(tareasModel.getTitulo());
            eedit_fecha.setText(tareasModel.getFecha());
            eedit_hora.setText(tareasModel.getHora());
           aswitch.setChecked(Boolean.parseBoolean(tareasModel.getActivar()));

        }

    }

    public void setTareasModel(TareasModel tareasModel){
        this.tareasModel = tareasModel;
        initUI();
    }
    //creamos
    public void showToolbar(String titulo, boolean upButton){

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_detalle);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(titulo);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
    public void onClick(View view) {
        if(view==btn_guardartarea){
            if(tareasModel==null){
                tareasModel=new TareasModel();
            }
            tareasModel.setTitulo(eedit_titulo.getText().toString());
            tareasModel.setFecha(eedit_fecha.getText().toString());
            tareasModel.setHora(eedit_hora.getText().toString());
            tareasModel.setActivar(eedit_titulo.getText().toString());
            if(aswitch.isChecked()){
                tareasModel.setActivar(true+"");
            }else{
                tareasModel.setActivar(false+"");
            }
            //ADIONAL PARA NOTIFICACIONES

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(), vars.bd, null, vars.version);
            SQLiteDatabase bd = admin.getReadableDatabase();
            bd = admin.getWritableDatabase();
            registro = new ContentValues();
            registro.put("encabezado", eedit_titulo.getText().toString());
            registro.put("mensaje", eedit_titulo.getText().toString());//nombre del campo
            registro.put("fecha", eedit_fecha.getText().toString());
            registro.put("hora", eedit_hora.getText().toString());
            bd.insert("alarma", null, registro);//nombre de la tabla
            bd.close();
           // edt_titulo.setText("");
           // edt_mensaje.setText("");
           // edit_fecha.setText("");
           // edit_hora.setText("");
            Toast.makeText(getActivity(), "alarma registrada", Toast.LENGTH_LONG).show();
            guardarTarea(tareasModel);


        }
        if(view==eedit_fecha){
            traerdialogfecha();
        }
        if(view==eedit_hora){
            traerdialoghora();
        }


    }


    private void traerdialoghora() {
        final Calendar c = Calendar.getInstance();
        horaS = c.get(Calendar.HOUR_OF_DAY);
        minutosS = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int horaofDay, int minutos) {
                eedit_hora.setText(horaofDay+":"+minutos);
            }
        },horaS,minutosS,false);
        timePickerDialog.show();

    }

    private void traerdialogfecha() {
        final Calendar c = Calendar.getInstance();
        anoS = c.get(Calendar.YEAR);
        mesS = c.get(Calendar.MONTH);
        diaS = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        eedit_fecha.setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + year+" ");

                    }
                }, diaS, mesS, anoS);
        datePickerDialog.show();
    }



    @Override
    public void mostrarLoading() {
    progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarLoading() {
    progressBar.setVisibility(View.GONE);
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void guardarTarea(TareasModel tareasModel) {
    tareaDetallePresenter.guardarTarea(tareasModel);
    }

    @Override
    public void notificarTareaGuardada() {
    getActivity().finish();
    }


}
