package fr.securisation_site_gvi.client;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.MetierFactory;
import metier.UtilisateurService;
import metier.entitys.Utilisateur;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author damien
 */
public class AjouterUtilisateur extends TemplateActivity {

    private ToggleButton toggleButtonSexe;
    private UtilisateurService utilisateurSrv = MetierFactory.getUtilisateurSrv();
    private EditText editTextNom;
    private EditText editTextPrenom;
    private Button buttonDateDeNaissance;
    private EditText editTextEmail;
    private EditText editTextTelephoneFixe;
    private EditText editTextTelephonePortable;
    private EditText editTextAdresse;
    private EditText editTextCodePostale;
    private EditText editTextVille;
    private Button buttonSupprimerUtilisateur;
    private Button buttonAjouterUtilisateur;
    private Utilisateur utilisateur = new Utilisateur();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_utilisateur);
        this.setThisActivityOn();
    }

    @Override
    public void initGraphicalObjects() {
        this.buttonAjouterUtilisateur = (Button) findViewById(R.id.buttonAjouterUtilisateur);
        this.editTextNom = (EditText) findViewById(R.id.editTextAjouterNom);
        this.editTextPrenom = (EditText) findViewById(R.id.editTextAjouterPrenom);
        this.buttonDateDeNaissance = (Button) findViewById(R.id.buttonAjouterDateDeNaissance);
        this.editTextEmail = (EditText) findViewById(R.id.editTextAjouterEmail);
        this.toggleButtonSexe = (ToggleButton) findViewById(R.id.toggleButtonAjouter1);
        this.editTextTelephoneFixe = (EditText) findViewById(R.id.editTextAjouterFixe);
        this.editTextTelephonePortable = (EditText) findViewById(R.id.editTextAjouterPortable);
        this.editTextAdresse = (EditText) findViewById(R.id.editTextAjouterAdresse);
        this.editTextCodePostale = (EditText) findViewById(R.id.editTextAjouterCodePostale);
        this.editTextVille = (EditText) findViewById(R.id.editTextAjouterVille);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonAjouterUtilisateur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nom = editTextNom.getText().toString();
                String prenom = editTextPrenom.getText().toString();
                String email = editTextEmail.getText().toString();
                String telephoneFixe = editTextTelephoneFixe.getText().toString();
                String telephonePortable = editTextTelephonePortable.getText().toString();
                String adresse = editTextAdresse.getText().toString();
                int codePostale = 0;
                if (editTextCodePostale.getText().toString().length() > 0) {
                    codePostale = Integer.valueOf(editTextCodePostale.getText().toString());
                }
                String ville = editTextVille.getText().toString();
                utilisateur.setNom(nom);
                utilisateur.setPrenom(prenom);
                utilisateur.setEmail(email);
                utilisateur.setTelephoneFixe(telephoneFixe);
                utilisateur.setTelephonePortable(telephonePortable);
                utilisateur.setAdresse(adresse);
                utilisateur.setHomme(toggleButtonSexe.isChecked());
                utilisateur.setCodePostale(codePostale);
                utilisateur.setVille(ville);
                if (utilisateur.getDateDeNaissance() != null) {
                    utilisateur.setDateDeNaissance(utilisateur.getDateDeNaissance());
                }
                new RESTUtilisateurAjout().execute();
            }
        });
        this.buttonDateDeNaissance.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Date date;
                int year;
                int month;
                int day;
                if (utilisateur.getDateDeNaissance() != null) {
                    date = utilisateur.getDateDeNaissance();
                    year = date.getYear();
                    month = date.getMonth();
                    day = date.getDay();
                } else {
                    Calendar c = Calendar.getInstance();
                    year = 1990;
                    month = c.MONTH;
                    day = c.DAY_OF_MONTH;
                }
                DatePickerDialog datePickerDialog = new DatePickerDialog(AjouterUtilisateur.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Date d = new Date(year, monthOfYear + 1, dayOfMonth + 1);
                        utilisateur.setDateDeNaissance(d);
                        afficherDate();
                    }
                }, year, month - 1, day + 1);
                datePickerDialog.setTitle("Date de naissance");
                datePickerDialog.setCancelable(true);
                datePickerDialog.show();
            }
        });
    }

    private void afficherDate() {
        Date d = this.utilisateur.getDateDeNaissance();
        int day = d.getDay() + 1;
        int year = d.getYear();
        int month = d.getMonth();
        String ret = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
        this.buttonDateDeNaissance.setText(ret);
    }
    private class RESTUtilisateurAjout extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Envoie des données ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (!erreur) {
                Toast.makeText(activityContext, "Utilisateur bien ajouté", Toast.LENGTH_LONG).show();
            }else if(result instanceof IOException){
                throwIOException();
            }else{
              throwException();
            }
        }

        @Override
        protected Object doInBackground(Object... params) {
            Object technicien = null;
            try {            
                utilisateurSrv.add(utilisateur, activityContext);
            } catch (IOException ex) {
                erreur = true;
                technicien = new IOException();
                Logger.getLogger(AjouterUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur=true;
                Logger.getLogger(AjouterUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }           
            return technicien;
        }
    }
}
