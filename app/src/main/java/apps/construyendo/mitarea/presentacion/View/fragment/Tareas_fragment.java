package apps.construyendo.mitarea.presentacion.View.fragment;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import apps.construyendo.mitarea.R;
import apps.construyendo.mitarea.presentacion.Model.TareasModel;
import apps.construyendo.mitarea.presentacion.Presenter.TareasPresenter;
import apps.construyendo.mitarea.presentacion.View.TareasView;
import apps.construyendo.mitarea.presentacion.notificaiones.AdminSQLiteOpenHelper;
import apps.construyendo.mitarea.presentacion.notificaiones.MyAlarmReceiver;
import apps.construyendo.mitarea.presentacion.notificaiones.vars;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tareas_fragment extends Fragment implements TareasView,AdapterView.OnItemClickListener {
    //probando
   /* private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase bd;
    private ContentValues registro;
    // date
    private int year;
    private int month;
    private int day;
    static final int DATE_DIALOG_ID = 999;
    // hora
    private int minute;
    private int hour;
    private static final int TIME_DIALOG_ID = 998;
    Calendar calendario = Calendar.getInstance();
    int hora, min,dia,mes,ano,hora11;
    String cadenaF, cadenaH,fecha_sistema,hora_sistema;
*/

    //instanciar widget
    ListView listatareas;
    ProgressBar progressBar;
    FloatingActionButton fabagregar;
    Toolbar toolbar;

    //requerido
    private TareasPresenter tareasPresenter;
    private ArrayAdapter<TareasModel> adapter;
    private List<TareasModel> tareasModelList =new ArrayList<>();
    private int index=0;
    private onTareasClickListerner onTareasClickListerner;


    public Tareas_fragment() {
        // Required empty public constructor
    }
    //debo haceguaserme que la activity mainactivity implemente este fragments --> para poder comunicarme con el activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onTareasClickListerner = (onTareasClickListerner) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"Debe implementar onnoticiaclicklistener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tarea, container, false);
        fabagregar=view.findViewById(R.id.float_agregar);
        return view;


    }
    //creamos
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        progressBar=view.findViewById(R.id.progress);
       // fabagregar=view.findViewById(R.id.float_agregar);
        listatareas=view.findViewById(R.id.listview_tareas);
        //super.onViewCreated(view,savedInstanceState);
        listatareas.setOnItemClickListener(this);
        //Click en flo FloatButton para Agregar nueva Tarea
        fabagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarTarea();
            }
        });

        if(savedInstanceState!=null){
            index=savedInstanceState.getInt("index",0);

        }
        adapter=new ArrayAdapter<TareasModel>(getContext(),android.R.layout.simple_list_item_1, tareasModelList);
        listatareas.setAdapter(adapter);

        tareasPresenter=new TareasPresenter(this);
        //tareasPresenter.cargarTareas();


        /*Click a los Item de la ListView
        listatareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listatareas.setItemChecked(i,true);
                index=i;
                verDetalle(tareasModelList.get(i));

            }
        });*/

    }


    @Override
    public void onStart() {
        super.onStart();
        tareasPresenter.cargarTareas();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("index",index);
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
    public void mostrarTareas(final List<TareasModel> tareasModelList) {
        adapter.clear();
        adapter.addAll(tareasModelList);
        if(getResources().getBoolean(R.bool.dual_pane)){
            listatareas.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            listatareas.setItemChecked(index,true);
            verDetalle(tareasModelList.get(index));
        }

    }





    //Ver detalle de cada listview
    @Override
    public void verDetalle(TareasModel tareasModel) {
        onTareasClickListerner.onTareaClick(tareasModel);
    }
    //Agregar Una Nueva Tarea
    @Override
    public void agregarTarea() {
        onTareasClickListerner.onAgregarTareasCkick();
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        index=i;
        listatareas.setItemChecked(i,true);
        verDetalle(tareasModelList.get(i));
    }

    public interface onTareasClickListerner{
        void onTareaClick(TareasModel tareasModel);
        void onAgregarTareasCkick();
    }

}
