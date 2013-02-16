package fr.securisation_site_gvi.client;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import clientServiceWeb.UtilisateurServiceWeb;
import entitys.Technicien;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends Activity {

    private Button buttonConnnexion;
    private EditText editTextLogin;
    private TextView textView;
    private EditText editTextPassword;
    private UtilisateurServiceWeb utilisateurSrv = clientServiceWeb.ClientServiceWebFactory.getPersonneClientServiceWeb();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.buttonConnnexion = (Button) findViewById(R.id.buttonConnexion);
        this.editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        this.textView = (TextView) findViewById(R.id.textView1);
        this.editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        this.buttonConnnexion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Technicien t = new Technicien();
                t.setLogin(editTextLogin.getText().toString());
                t.setPassword(editTextPassword.getText().toString());
                Technicien tech = null;
                 ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "", "Vérification ...", true);
                try {
                    tech = utilisateurSrv.verificationConnexion(t);
                } catch (Exception ex) {
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                }
                progressDialog.dismiss();
                if (tech == null) {
                    textView.setText("Connexion erreur.");
                   Toast to= Toast.makeText(MainActivity.this,"Il y à une erreur dans votre login ou votre mot de passe.",Toast.LENGTH_LONG);
                   to.show();
                } else {
                    textView.setText("Connexion réussi.");
                    
                    Intent intent = new Intent(MainActivity.this, AccueilActivity.class);

                    startActivity(intent);
                }
            }
        });



    }
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			// Comportement du bouton "A Propos"
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
