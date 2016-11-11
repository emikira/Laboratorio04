package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Ciudad;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BusquedaFinalizadaListener;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.FormBusqueda;

/**
 * Created by martdominguez on 22/09/2016.
 */
public class BuscarDepartamentosTask extends AsyncTask<FormBusqueda,Integer,List<Departamento>> {

    private BusquedaFinalizadaListener<Departamento> listener;

    public BuscarDepartamentosTask(BusquedaFinalizadaListener<Departamento> dListener){
        this.listener = dListener;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<Departamento> departamentos) {
        listener.busquedaFinalizada(departamentos);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        listener.busquedaActualizada("departamento "+values[0]);


    }

    @Override
    protected List<Departamento> doInBackground(FormBusqueda... busqueda) {
        List<Departamento> todos = Departamento.getAlojamientosDisponibles();
        List<Departamento> resultado = new ArrayList<Departamento>();
        int contador = 0;
        Ciudad ciudadBuscada = busqueda[0].getCiudad();
        double preMin = busqueda[0].getPrecioMinimo();
        double preMax = busqueda[0].getPrecioMaximo();
        int huespedes = busqueda[0].getHuespedes();
        boolean fumar = busqueda[0].getPermiteFumar();

        for(Departamento i : todos){
            if (i.getPrecio()>preMin && i.getPrecio()<preMax && i.getCapacidadMaxima()>huespedes && i.getCiudad().equals(ciudadBuscada) && i.getFumador()==fumar){
                resultado.add(i);
                contador++;
            }
        }
        return resultado;
    }
}
