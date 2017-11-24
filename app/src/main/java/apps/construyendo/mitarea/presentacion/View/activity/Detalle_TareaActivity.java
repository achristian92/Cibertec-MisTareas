package apps.construyendo.mitarea.presentacion.View.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import apps.construyendo.mitarea.presentacion.Model.TareasModel;
import apps.construyendo.mitarea.presentacion.View.fragment.Tareas_Detalle_fragment;

public class Detalle_TareaActivity extends AppCompatActivity {
    public static final  String EXTRA_NOTICIA="activity.tareadetalle.EXTRA_TAREA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detalle_tarea);
        TareasModel tareasModel =getIntent().getParcelableExtra(EXTRA_NOTICIA);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();//iniciar una transaccion
        ft.replace(android.R.id.content,Tareas_Detalle_fragment.newInstance(tareasModel));
       // ft.add(android.R.id.content, new Tareas_Detalle_fragment().newInstance(tareasModel));
        ft.commit();
    }
}
