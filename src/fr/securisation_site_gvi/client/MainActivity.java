package fr.securisation_site_gvi.client;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.entitys.Technicien;
import physique.dataOut.UtilisateurServiceWeb;

public class MainActivity extends TemplateActivity {

    private Button buttonConnnexion;
    private EditText editTextLogin;
    private TextView textView;
    private EditText editTextPassword;
    private UtilisateurServiceWeb utilisateurSrv = physique.dataOut.PhysiqueDataOutFactory.getPersonneClientServiceWeb();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.setThisActivityOn();
        this.editTextLogin.setText("damienChes");
        this.editTextPassword.setText("damien");

    }

    @Override
    public void initGraphicalObjects() {
        this.buttonConnnexion = (Button) findViewById(R.id.buttonConnexion);
        this.editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        this.textView = (TextView) findViewById(R.id.textView1);
        this.editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonConnnexion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Technicien t = new Technicien();
                t.setLogin(editTextLogin.getText().toString());
                t.setPassword(editTextPassword.getText().toString());
                Technicien tech = null;
                try {
                    tech = utilisateurSrv.verificationConnexion(t, MainActivity.this);
                } catch (Exception ex) {
                    Toast.makeText(activityContext, "Erreur du serveur", Toast.LENGTH_SHORT);
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (tech == null) {
                    textView.setText("Il y à une erreur dans votre login ou votre mot de passe.");
                } else {
                    textView.setText("Connexion réussi.");
                    addNotification("Conncté", 12);
                    Intent intent = new Intent(MainActivity.this, AccueilActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.dismitDialog();
        }
        return true;
    }

    private void dismitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
        builder.setCancelable(false);
        builder.setMessage("Etes vous sur de vouloir quitter cette application ?");
        builder.setTitle("Attention");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finishFromChild(MainActivity.this);
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.dismitDialog();                
                return true;
            case R.id.menu_settings:
                Intent intent = new Intent(this.activityContext, Parametres.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
