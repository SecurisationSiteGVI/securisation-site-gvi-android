package fr.securisation_site_gvi.client;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Message;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
    private Button buttonDateDeNaissance;
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
    }

    private void setTextInAllEditText(Long id) {
        Utilisateur utilisateur = null;

        try {
            utilisateur = utilisateurSrv.getById(id, ModifierUtilisateur.this);
        } catch (Exception ex) {
            Logger.getLogger(ModifierUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.utilisateurSelected = utilisateur;
        this.editTextNom.setText(utilisateur.getNom());

        this.editTextPrenom.setText(utilisateur.getPrenom());
        if (utilisateur.getDateDeNaissance() != null) {
            this.afficherDate();
        } else {
            this.buttonDateDeNaissance.setText("Séléctionner");
        }
        if (utilisateur.getEmail() != null) {
            this.editTextEmail.setText(utilisateur.getEmail());
        }

        if (utilisateur.isHomme()) {
            this.editTextSexe.setText("homme");
        } else {
            this.editTextSexe.setText("femme");
        }
        if (utilisateur.getTelephoneFixe() != null) {
            this.editTextTelephoneFixe.setText(utilisateur.getTelephoneFixe());
        }
        if (utilisateur.getTelephonePortable() != null) {
            this.editTextTelephonePortable.setText(utilisateur.getTelephonePortable());
        }
        if (utilisateur.getAdresse() != null) {
            this.editTextAdresse.setText(utilisateur.getAdresse());
        }
        if (utilisateur.getCodePostale() > 200) {
            this.editTextCodePostale.setText(String.valueOf(utilisateur.getCodePostale()));
        }
        if (utilisateur.getVille() != null) {
            this.editTextVille.setText(utilisateur.getVille());
        }

    }

    private void initGraphicalObjects() {
        this.buttonSupprimerUtilisateur = (Button) findViewById(R.id.buttonSupprimerUtilisateur);
        this.buttonModifierUtilisateur = (Button) findViewById(R.id.buttonModifierUtilisateur);
        this.editTextNom = (EditText) findViewById(R.id.editTextNom);
        this.editTextPrenom = (EditText) findViewById(R.id.editTextPrenom);
        this.buttonDateDeNaissance = (Button) findViewById(R.id.buttonDateDeNaissance);
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
                String nom = editTextNom.getText().toString();
                String prenom = editTextPrenom.getText().toString();
                String email = editTextEmail.getText().toString();
                String telephoneFixe = editTextTelephoneFixe.getText().toString();
                String telephonePortable = editTextTelephonePortable.getText().toString();
                String adresse = editTextAdresse.getText().toString();
                int codePostale = Integer.valueOf(editTextCodePostale.getText().toString());
                String ville = editTextVille.getText().toString();
                Utilisateur u = new Utilisateur();
                u.setNom(nom);
                u.setPrenom(prenom);
                u.setEmail(email);
                u.setTelephoneFixe(telephoneFixe);
                u.setTelephonePortable(telephonePortable);
                u.setAdresse(adresse);
                u.setCodePostale(codePostale);
                u.setVille(ville);
                u.setId(utilisateurSelected.getId());
                if (utilisateurSelected.getDateDeNaissance() != null) {
                    u.setDateDeNaissance(utilisateurSelected.getDateDeNaissance());
                }
                try {
                    utilisateurSrv.update(u, ModifierUtilisateur.this);
                } catch (Exception ex) {
                    Toast.makeText(ModifierUtilisateur.this, "Impossible d'éffectuer une modification.", Toast.LENGTH_LONG);
                    Logger.getLogger(ModifierUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.buttonSupprimerUtilisateur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    utilisateurSrv.remove(utilisateurSelected, ModifierUtilisateur.this);
                    Toast.makeText(ModifierUtilisateur.this, "Utilisateur supprimé.", Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    Logger.getLogger(ModifierUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.buttonDateDeNaissance.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Date date;
                int year;
                int month;
                int day;
                if (utilisateurSelected.getDateDeNaissance() != null) {
                    date = utilisateurSelected.getDateDeNaissance();
                    year = date.getYear();
                    month = date.getMonth();
                    day = date.getDay();
                } else {
                    Calendar c = Calendar.getInstance();
                    year = 1990;
                    month = c.MONTH;
                    day = c.DAY_OF_MONTH;
                }
                DatePickerDialog datePickerDialog = new DatePickerDialog(ModifierUtilisateur.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Date d = new Date(year, monthOfYear + 1, dayOfMonth + 1);
                        utilisateurSelected.setDateDeNaissance(d);
                        afficherDate();
                    }
                }, year, month-1, day+1);
               
                datePickerDialog.setTitle("Date de naissance");
                datePickerDialog.setCancelable(true);
                datePickerDialog.show();
            }
        });
    }

    private void afficherDate() {
        Date d = this.utilisateurSelected.getDateDeNaissance();
        int day = d.getDay()+1;
        int year = d.getYear();
        int month = d.getMonth();
        String ret = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
        this.buttonDateDeNaissance.setText(ret);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
