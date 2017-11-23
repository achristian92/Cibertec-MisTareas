package apps.construyendo.mitarea.presentacion.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Christian 24 on 22/11/2017.
 */

public class TareasModel implements Parcelable{
    private String titulo;
    private String fecha;
    private String hora;
    private String activar;

    public TareasModel() {
    }

    public TareasModel(String titulo, String fecha, String hora, String activar) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.hora = hora;
        this.activar = activar;
    }


    protected TareasModel(Parcel in) {
        titulo = in.readString();
        fecha = in.readString();
        hora = in.readString();
        activar = in.readString();
    }

    public static final Creator<TareasModel> CREATOR = new Creator<TareasModel>() {
        @Override
        public TareasModel createFromParcel(Parcel in) {
            return new TareasModel(in);
        }

        @Override
        public TareasModel[] newArray(int size) {
            return new TareasModel[size];
        }
    };

    public String getActivar() {
        return activar;
    }

    public void setActivar(String activar) {
        this.activar = activar;
    }

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


    @Override
    public String toString() {
        return titulo + "-" + fecha + "-" + hora + "-" + activar;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titulo);
        parcel.writeString(fecha);
        parcel.writeString(hora);
        parcel.writeString(activar);
    }
}

