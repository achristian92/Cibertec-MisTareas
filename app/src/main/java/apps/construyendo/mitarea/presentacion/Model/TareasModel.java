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
    private boolean activar;

    public TareasModel() {
    }

    public TareasModel(String titulo, String fecha, String hora, boolean activar) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.hora = hora;
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

    public boolean isActivar() {
        return activar;
    }

    public void setActivar(boolean activar) {
        this.activar = activar;
    }

    protected TareasModel(Parcel in) {
        titulo = in.readString();
        fecha = in.readString();
        hora = in.readString();
        activar = in.readByte() != 0;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titulo);
        parcel.writeString(fecha);
        parcel.writeString(hora);
        parcel.writeByte((byte) (activar ? 1 : 0));
    }

    @Override
    public String toString() {
        return titulo+"-"+fecha+"-"+hora+""+activar;
    }
}
