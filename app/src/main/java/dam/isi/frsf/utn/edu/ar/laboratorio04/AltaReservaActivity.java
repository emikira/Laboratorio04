package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Usuario;

public class AltaReservaActivity extends AppCompatActivity {

    private DepartamentoAdapter departamentosAdapter;
    private ArrayList<Departamento> lista;
    private EditText EditFechaIn;
    private EditText EditFechaOut;
    private Button btnReservar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reserva);
        lista = new ArrayList<>();
        EditFechaIn = (EditText) findViewById(R.id.editFechaIn);
        EditFechaOut = (EditText) findViewById(R.id.editFechaFin);
        btnReservar = (Button) findViewById(R.id.buttonReserva);
        btnReservar.setOnClickListener(reservar);

    }
    private View.OnClickListener reservar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Date fechaIn = df.parse(EditFechaIn.getText().toString());
                Date fechaOut = df.parse(EditFechaOut.getText().toString());
                Reserva r = new Reserva();
                Intent i2 = getIntent();
                Departamento depto = (Departamento) i2.getSerializableExtra("value");
                r.setAlojamiento(depto);
                r.setPrecio(depto.getPrecio());
                r.setFechaInicio(fechaIn);
                r.setFechaFin(fechaOut);
                r.setUsuario(Usuario.getInstance());
                Usuario.getInstance().setReserva(r);

            }
            catch (Exception e){
                e.printStackTrace();
            }

            Toast.makeText(AltaReservaActivity.this, "Reserva realizada, datos disponibles en el menu de reservas", Toast.LENGTH_LONG).show();
        }

    };
}
