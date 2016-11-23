package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Usuario;

/**
 * Created by augusto on 23/11/2016.
 */

public class ConfigUsuarioActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String name = preferences.getString("pref_nombre", "DefaultUser");
        String mail = preferences.getString("pref_email", "user@mail.com");

        Usuario.getInstance().setCorreo(mail);
    }
}
