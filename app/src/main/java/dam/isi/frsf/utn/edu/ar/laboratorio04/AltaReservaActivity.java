package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Usuario;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.AlarmReceiver;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.DivisibleTresReceiver;

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

                if( fechaIn.after(fechaOut)){
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    Toast.makeText(AltaReservaActivity.this, "La fecha de check-in debe ser anterior a la de check-out", Toast.LENGTH_SHORT).show();}


                IntentFilter inf = new IntentFilter("dam.isi.frsf.utn.edu.ar.laboratorio04.ListaReservasActivity.Alarma");
                DivisibleTresReceiver div = new DivisibleTresReceiver();
                registerReceiver(div, inf);

                Intent i3 = new Intent(AltaReservaActivity.this, DivisibleTresReceiver.class);
                Bundle b = new Bundle();
                b.putSerializable("reserva", r);
                i3.putExtras(b);
                Calendar cal = Calendar.getInstance();
                PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(),1,i3,0);
                AlarmManager am = (AlarmManager) getSystemService(AltaReservaActivity.ALARM_SERVICE);
                am.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),5*1000,pi);
                Toast.makeText(AltaReservaActivity.this, "Reserva enviada, esperando confirmacion", Toast.LENGTH_SHORT).show();
                finish();

            }
            catch (Exception e){
                e.printStackTrace();
            }


        }

    };
}
