package fr.securisation_site_gvi.client;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.MetierFactory;
import metier.UtilisateurService;
import metier.entitys.Ressource;
import metier.entitys.Utilisateur;

public class ModifierUtilisateur extends Activity {

    private UtilisateurService utilisateurSrv = MetierFactory.getUtilisateurSrv();
    private EditText editTextNom;
    private EditText editTextPrenom;
    private EditText editTextDateDeNaissance;
    private EditText editTextEmail;
    private EditText editTextSexe;
    private EditText editTextTelephoneFixe;
    private EditText editTextTelephonePortable;
    private EditText editTextAdresse;
    private EditText editTextCodePostale;
    private EditText editTextVille;
    private Button buttonSupprimerUtilisateur;
    private Button buttonModifierUtilisateur;
    private Utilisateur utilisateurSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier_utilisateur);
        this.initGraphicalObjects();
        Bundle extras = getIntent().getExtras();
        Long id = extras.getLong("id");
        this.setTextInAllEditText(id);
//        
    }
    private void setTextInAllEditText(Long id){
        Utilisateur utilisateur =null;
        try {
           utilisateur  = utilisateurSrv.getById(id);
        } catch (Exception ex) {
            Logger.getLogger(ModifierUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.editTextNom.setText(utilisateur.getNom());
        
        this.editTextPrenom.setText(utilisateur.getPrenom());
        if(utilisateur.getDateDeNaissance()!=null){
            this.editTextDateDeNaissance.setText(utilisateur.getDateDeNaissance().toString());
        }
        if(utilisateur.getEmail()!=null){
            this.editTextEmail.setText(utilisateur.getEmail());
        }
        
        if(utilisateur.isHomme()){
            this.editTextSexe.setText("homme");
        }else{
            this.editTextSexe.setText("femme");
        }
        if(utilisateur.getTelephoneFixe()!=null){
            this.editTextTelephoneFixe.setText(utilisateur.getTelephoneFixe());
        }
        if(utilisateur.getTelephonePortable()!=null){
            this.editTextTelephonePortable.setText(utilisateur.getTelephonePortable());
        }
        if(utilisateur.getAdresse()!=null){
           this.editTextAdresse.setText(utilisateur.getAdresse()); 
        }
        if(utilisateur.getCodePostale()>200){
            this.editTextCodePostale.setText(String.valueOf(utilisateur.getCodePostale()));
        }
        if(utilisateur.getVille()!=null){
            this.editTextVille.setText(utilisateur.getVille());
        }
        
    }

    private void initGraphicalObjects() {
        this.buttonSupprimerUtilisateur = (Button) findViewById(R.id.buttonSupprimerUtilisateur);
        this.buttonModifierUtilisateur = (Button) findViewById(R.id.buttonModifierUtilisateur);
        this.editTextNom = (EditText) findViewById(R.id.editTextNom);
        this.editTextPrenom = (EditText) findViewById(R.id.editTextPrenom);
        this.editTextDateDeNaissance = (EditText) findViewById(R.id.editTextDateDeNaissance);
        this.editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        this.editTextSexe = (EditText) findViewById(R.id.editTextSexe);
        this.editTextTelephoneFixe = (EditText) findViewById(R.id.editTextFixe);
        this.editTextTelephonePortable = (EditText) findViewById(R.id.editTextPortable);
        this.editTextAdresse = (EditText) findViewById(R.id.editTextAdresse);
        this.editTextCodePostale = (EditText) findViewById(R.id.editTextCodePostale);
        this.editTextVille = (EditText) findViewById(R.id.editTextVille);
        this.addActionListnerForAllGraphicalObjects();
    }

    private void addActionListnerForAllGraphicalObjects() {
        this.buttonModifierUtilisateur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               Toast.makeText(ModifierUtilisateur.this, "LA PAGE PAS ENCORE CREE.", Toast.LENGTH_SHORT).show();
            }
        });
         this.buttonSupprimerUtilisateur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               Toast.makeText(ModifierUtilisateur.this, "LA PAGE PAS ENCORE CREE.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
