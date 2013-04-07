/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import static android.app.Activity.RESULT_OK;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.BadgeService;
import metier.MetierFactory;
import metier.SecteurService;
import metier.entitys.Secteur;
import metier.entitys.Technicien;

/**
 *
 * @author damien
 */
public class AjoutSecteur extends TemplateActivity {

    private EditText edittextNom;
    private Button buttonAjouter;
    private SecteurService secteurSrv = MetierFactory.getSecteurServ();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_secteur);
        this.setThisActivityOn();
    }

    @Override
    public void initGraphicalObjects() {
        this.edittextNom = (EditText) findViewById(R.id.AjoutSecteureditTextAjouterNom);
        this.buttonAjouter = (Button) findViewById(R.id.AjoutSecteurbuttonAjouter);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonAjouter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nom = edittextNom.getText().toString();
                Secteur secteur = new Secteur();
                secteur.setNom(nom);
                AsyncTask<Object, Void, Object> ret = new AjoutSecteur.RESTConexion().execute(secteur);
            }
        });
    }
    private class RESTConexion extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Envoie des données au serveur ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if(result==null){
                Toast.makeText(activityContext, "Le secteur n'a pas put être ajouté.", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(activityContext, "Le secteur a bien été ajouté.", Toast.LENGTH_LONG).show();
            }           
        }

        @Override
        protected Object doInBackground(Object... params) {
            Secteur secteur = (Secteur) params[0];
            try {
                   secteurSrv.ajouter(activityContext, secteur);
                } catch (Exception ex) {
                    secteur=null;
                    Logger.getLogger(AjouterBadge.class.getName()).log(Level.SEVERE, null, ex);
                }
            return secteur;
        }
    }
}
