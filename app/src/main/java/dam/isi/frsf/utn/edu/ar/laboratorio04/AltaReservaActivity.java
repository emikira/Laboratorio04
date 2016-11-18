package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;

public class AltaReservaActivity extends AppCompatActivity {

    private ListView listaReservas;
    private DepartamentoAdapter departamentosAdapter;
    private ArrayList<Departamento> lista;
    private TextView hayReservas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reserva);
        listaReservas = (ListView) findViewById(R.id.listaReservas);
        lista = new ArrayList<>();
        hayReservas = (TextView) findViewById(R.id.hayReservas);
    }

    @Override
    protected void onStart() {
        super.onStart();
        departamentosAdapter = new DepartamentoAdapter(AltaReservaActivity.this,lista);
        listaReservas.setAdapter(departamentosAdapter);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Boolean desdeLista = intent.getExtras().getBoolean("desdeLista");
        if(desdeLista){
            Departamento reservado = (Departamento) bundle.getSerializable("value");
            lista.add(reservado);
        }
        mostrarLista(lista);

    }

    public void mostrarLista (List lista){
        if(lista.size()>0){
            departamentosAdapter.addAll(lista);
            departamentosAdapter.notifyDataSetChanged();
            listaReservas.setVisibility(View.VISIBLE);
        }else{
            departamentosAdapter.clear();
            hayReservas.setVisibility(View.VISIBLE);
            hayReservas.setText(getString(R.string.sin_reservas));
        }
    }
}
