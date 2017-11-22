package apps.construyendo.mitarea.presentacion.View.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;

import apps.construyendo.mitarea.R;
import apps.construyendo.mitarea.presentacion.Model.Tareas;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tareas_Detalle_fragment extends Fragment {
    Toolbar toolbar_detalle;
    EditText eedit_titulo,eedit_fecha,eedit_hora;
    Switch aSwitch;
    private Tareas tareas;
    private static final String ARG_TAREAS = "fragment.NOTICIADETALLEFRAFMENT.ARG_NOTICIA";

    //CREAMOS  RECUPERAR LOS DATOS ENVIADOS
    //(1)
    public Fragment newInstance(Tareas tareas) {

        Tareas_Detalle_fragment f = new Tareas_Detalle_fragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TAREAS, tareas);
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
            tareas=getArguments().getParcelable(ARG_TAREAS);
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
        eedit_titulo=view.findViewById(R.id.edit_titulo);
        eedit_fecha=view.findViewById(R.id.edit_fecha);
        eedit_hora=view.findViewById(R.id.edit_hora);
        aSwitch=view.findViewById(R.id.switch1);
        toolbar_detalle=view.findViewById(R.id.toolbar_princi);
        showToolbar("Detalle de Mi Tarea",true);
        initUI();
    }
    private void initUI(){
        if(tareas!=null){
            eedit_titulo.setText(tareas.getTitulo());
            eedit_fecha.setText(tareas.getFecha());
            eedit_hora.setText(tareas.getHora());
        }

    }

    public void setTareas(Tareas tareas){
        this.tareas=tareas;
        initUI();
    }
    //creamos
    public void showToolbar(String titulo, boolean upButton){

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar_detalle);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(titulo);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}
