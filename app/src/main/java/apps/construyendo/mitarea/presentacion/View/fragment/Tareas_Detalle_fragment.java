package apps.construyendo.mitarea.presentacion.View.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;

import apps.construyendo.mitarea.R;
import apps.construyendo.mitarea.presentacion.Model.TareasModel;
import apps.construyendo.mitarea.presentacion.Presenter.TareaDetallePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tareas_Detalle_fragment extends Fragment implements TareaDetalleView,View.OnClickListener {
    Toolbar toolbar_detalle;
    EditText eedit_titulo,eedit_fecha,eedit_hora;
    Switch aSwitch;
    private TareasModel tareasModel;
    private static final String ARG_TAREAS = "fragment.NOTICIADETALLEFRAFMENT.ARG_NOTICIA";
    private Button btn_guardartarea;
    private ProgressBar progressBar;

    //despues de DATOS
    private TareaDetallePresenter tareaDetallePresenter;

    //CREAMOS  RECUPERAR LOS DATOS ENVIADOS
    //(1)
    public Fragment newInstance(TareasModel tareasModel) {

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
        eedit_titulo=view.findViewById(R.id.edit_titulo);
        eedit_fecha=view.findViewById(R.id.edit_fecha);
        eedit_hora=view.findViewById(R.id.edit_hora);
        aSwitch=view.findViewById(R.id.switch1);
        toolbar_detalle=view.findViewById(R.id.toolbar_princi);
        btn_guardartarea=view.findViewById(R.id.btn_guardar);
        progressBar=view.findViewById(R.id.progress);
        showToolbar("Detalle de Mi Tarea",true);
        initUI();

        tareaDetallePresenter=new TareaDetallePresenter(this);

        btn_guardartarea.setOnClickListener(this);

    }
    private void initUI(){
        if(tareasModel !=null){
            eedit_titulo.setText(tareasModel.getTitulo());
            eedit_fecha.setText(tareasModel.getFecha());
            eedit_hora.setText(tareasModel.getHora());
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
        if(tareasModel==null){
            tareasModel=new TareasModel();
        }
        tareasModel.setTitulo(eedit_titulo.getText().toString());
        tareasModel.setFecha(eedit_fecha.getText().toString());
        tareasModel.setHora(eedit_hora.getText().toString());
        guardarTarea(tareasModel);
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
