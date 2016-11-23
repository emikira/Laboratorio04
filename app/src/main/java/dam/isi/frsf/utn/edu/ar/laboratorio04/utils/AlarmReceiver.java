package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import dam.isi.frsf.utn.edu.ar.laboratorio04.ListaReservasActivity;
import dam.isi.frsf.utn.edu.ar.laboratorio04.R;


/**
 * Created by augusto on 22/11/2016.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String  title = intent.getStringExtra("title"),
                text = intent.getStringExtra("text");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String ringtoneString = sharedPreferences.getString("pref_ringtone", "DEFAULT_SOUND");
        Uri ringtoneUri = Uri.parse(ringtoneString);


        Intent notificationIntent = new Intent(context, ListaReservasActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setSound(ringtoneUri);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(1, builder.build());
    }
}
