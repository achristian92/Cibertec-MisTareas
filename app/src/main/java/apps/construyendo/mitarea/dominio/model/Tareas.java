package apps.construyendo.mitarea.dominio.model;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public class Tareas {
    private String titulo;
    private String fecha;
    private String hora;
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
