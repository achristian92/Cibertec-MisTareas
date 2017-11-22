package apps.construyendo.mitarea.datos.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public class TareaEntity {
    @SerializedName("tituloB")
    private String titulo;
    @SerializedName("fechaB")
    private String fecha;
    @SerializedName("horaB")
    private String hora;
    @SerializedName("activarB")
    private boolean activar;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean isActivar() {
        return activar;
    }

    public void setActivar(boolean activar) {
        this.activar = activar;
    }
}
