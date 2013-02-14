package fr.securisation_site_gvi.client;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import clientServiceWeb.UtilisateurServiceWeb;
import entitys.Technicien;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends Activity
{
    private Button buttonConnnexion;
    private EditText editTextLogin;
    private TextView textView;
    private EditText editTextPassword;
    private UtilisateurServiceWeb utilisateurSrv = clientServiceWeb.ClientServiceWebFactory.getPersonneClientServiceWeb();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.buttonConnnexion = (Button) findViewById(R.id.buttonConnexion);
        this.editTextLogin =(EditText) findViewById(R.id.editTextLogin);
        this.textView= (TextView) findViewById(R.id.textView1);
        this.editTextPassword =(EditText) findViewById(R.id.editTextPassword);
        this.buttonConnnexion.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Technicien t=new Technicien();
                t.setLogin(editTextLogin.getText().toString());
                t.setPassword(editTextPassword.getText().toString());
                Technicien tech = null;
                try {
                    tech = utilisateurSrv.verificationConnexion(t);
                } catch (Exception ex) {
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(tech==null){
                    textView.setText("Il y à une erreur dans votre login ou votre mot de passe.");
                }else{
                    textView.setText("Connexion réussi.");
                }
            }
        });
        

        
    }
}
