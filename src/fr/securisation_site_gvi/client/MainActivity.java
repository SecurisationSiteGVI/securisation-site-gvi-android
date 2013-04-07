package fr.securisation_site_gvi.client;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.MetierFactory;
import metier.UtilisateurService;
import metier.entitys.Technicien;

public class MainActivity extends TemplateActivity {

    public Button buttonConnnexion;
    public EditText editTextLogin;
    public TextView textView;
    public EditText editTextPassword;
    private UtilisateurService utilisateurSrv = MetierFactory.getUtilisateurSrv();

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
                AsyncTask<Object, Void, Object> ret = new RESTConexion().execute(t);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.dismitDialog();
        }
        return false;
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

    private class RESTConexion extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Vérification ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (result == null) {
                textView.setText("Il y à une erreur dans votre login ou votre mot de passe.");
            } else {
                textView.setText("Connexion réussi.");
                addNotification("Conncté", 12);
                Intent intent = new Intent(MainActivity.this, AccueilActivity.class);
                startActivity(intent);
            }
        }

        @Override
        protected Object doInBackground(Object... params) {
            Technicien technicien =null;
            Technicien utilisateur = (Technicien) params[0];
            try {
                technicien = utilisateurSrv.verificationConnexion(utilisateur, activityContext);
            } catch (Exception ex) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
            }
            return technicien;
        }
    }
}
