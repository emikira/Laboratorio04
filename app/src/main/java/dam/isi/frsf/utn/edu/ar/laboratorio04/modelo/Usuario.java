package dam.isi.frsf.utn.edu.ar.laboratorio04.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdominguez on 22/09/16.
 */
public class Usuario implements Serializable {

    private static Usuario singletonInstance = null;
    private Integer id;
    private String correo;
    private List<Reserva> reservas;
    private Integer puntosSuperPremio;

    public static Usuario getInstance(){
        if(singletonInstance == null) {
            singletonInstance = new Usuario(1, "user@mail.com");
        }

        return singletonInstance;
    }

    public Usuario (Integer id, String correo){
        this.id = id;
        this.correo = correo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<String> getReservas() {

        List<String> reservasSt = new ArrayList<String>();

        for(Reserva i : reservas){
            String s = i.toString();
            reservasSt.add(s);
        }

        return reservasSt;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void setReserva(Reserva reserva) {
        if(reservas == null) reservas = new ArrayList<Reserva>();
        reservas.add(reserva);
    }


    public Integer getPuntosSuperPremio() {
        return puntosSuperPremio;
    }

    public void setPuntosSuperPremio(Integer puntosSuperPremio) {
        this.puntosSuperPremio = puntosSuperPremio;
    }
}
