package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.AlarmManager;
import android.app.PendingIntent;

import dam.isi.frsf.utn.edu.ar.laboratorio04.R;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Usuario;

/**
 * Created by augusto on 23/11/2016.
 */

public class DivisibleTresReceiver extends BroadcastReceiver {

    private Context mContext = null;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (mContext == null) this.mContext = context;

        Long millis = System.currentTimeMillis();

        if (millis % 3 == 0) {

            Reserva reserva = (Reserva) intent.getSerializableExtra("reserva");
            String descripcionReserva = reserva.toString();
            Usuario.getInstance().setReserva(reserva);

            Intent broadcastIntent = new Intent("dam.isi.frsf.utn.edu.ar.laboratorio04.Notificacion");
            broadcastIntent.putExtra("title", "Reserva Confirmada.");
            broadcastIntent.putExtra("text", descripcionReserva);
            context.sendBroadcast(broadcastIntent);

            Intent i = new Intent(this.mContext, DivisibleTresReceiver.class);
            PendingIntent pi = PendingIntent.getBroadcast(mContext, 1,i, 0);
            AlarmManager am = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
            am.cancel(pi);
        }
    }
}
