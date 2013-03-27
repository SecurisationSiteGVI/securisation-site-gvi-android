package fr.securisation_site_gvi.client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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
                    Intent intent = new Intent(MainActivity.this, AccueilActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
