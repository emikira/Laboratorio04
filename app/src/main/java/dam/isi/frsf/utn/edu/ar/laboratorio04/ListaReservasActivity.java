package dam.isi.frsf.utn.edu.ar.laboratorio04;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Usuario;

/**
 * Created by augusto on 22/11/2016.
 */

public class ListaReservasActivity extends AppCompatActivity {

    ListView reservas;
    TextView sinRes;
    List lista;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reservas);
        reservas = (ListView) findViewById(R.id.listaReservas);
        sinRes = (TextView) findViewById(R.id.sinReservas);
        lista = new ArrayList<Reserva>();

        Usuario u = Usuario.getInstance();
        if(u.getReservas()==null || u.getReservas().isEmpty()){
            reservas.setVisibility(View.GONE);
            sinRes.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No hay reservas para mostrar", Toast.LENGTH_SHORT).show();
        }
        else{
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ListaReservasActivity.this, android.R.layout.simple_list_item_1,u.getReservas());
            reservas.setAdapter(adapter);
            sinRes.setVisibility(View.GONE);
            reservas.setVisibility(View.VISIBLE);

        }
    }
}
