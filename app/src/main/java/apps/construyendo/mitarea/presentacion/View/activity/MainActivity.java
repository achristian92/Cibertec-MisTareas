package apps.construyendo.mitarea.presentacion.View.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import apps.construyendo.mitarea.R;
import apps.construyendo.mitarea.presentacion.Model.TareasModel;
import apps.construyendo.mitarea.presentacion.View.fragment.Tareas_Detalle_fragment;
import apps.construyendo.mitarea.presentacion.View.fragment.Tareas_fragment;

public class MainActivity extends AppCompatActivity implements Tareas_fragment.onTareasClickListerner{
    Toolbar toolbar;

    //si es tablet
    private boolean isDualPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar_princi);
        showToolbar("Mis TareasModel",false);

        Fragment tareadetallefragment=getSupportFragmentManager().findFragmentById(R.id.frag_tareas_detalle);
        isDualPane = tareadetallefragment!=null;
    }
    public void showToolbar(String titulo, boolean upButton){
        //toolbar.setTitle(R.string.crear_usu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(titulo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
    @Override
    public void onTareaClick(TareasModel tareasModel) {
        if(!isDualPane){
            //si es telefono
            Intent intent = new Intent(MainActivity.this, Detalle_TareaActivity.class);
            intent.putExtra(Detalle_TareaActivity.EXTRA_NOTICIA, tareasModel);
            startActivity(intent);
        }else{
            //si es tablet
            Tareas_Detalle_fragment tareas_detalle_fragment= (Tareas_Detalle_fragment) getSupportFragmentManager().findFragmentById(R.id.frag_tareas_detalle);
            tareas_detalle_fragment.setTareasModel(tareasModel);
        }
    }

    @Override
    public void onAgregarTareasCkick() {
        Intent intent=new Intent(MainActivity.this,Detalle_TareaActivity.class);
        startActivity(intent);
    }
}
