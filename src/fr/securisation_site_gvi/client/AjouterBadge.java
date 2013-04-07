/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.BadgeService;
import metier.MetierFactory;
import metier.entitys.Badge;

/**
 *
 * @author damien
 */
public class AjouterBadge extends TemplateActivity {

    private EditText edittextNumero;
    private Button buttonValider;
    private BadgeService badgeSrv = MetierFactory.getBadgeSrv();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_badge);
        this.setThisActivityOn();
    }

    @Override
    public void initGraphicalObjects() {
        this.edittextNumero = (EditText) findViewById(R.id.editTextAjouterBadgeNumero);
        this.buttonValider = (Button) findViewById(R.id.buttonAjouterBadge);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonValider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String numero = edittextNumero.getText().toString();
                Badge b = new Badge();
                b.setNumero(numero);
                
            }
        });
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
                Toast.makeText(activityContext, "Badge bien ajouté.", Toast.LENGTH_LONG).show();
            }else if(result instanceof IOException){
                throwIOException();
            }else if(result instanceof MalformedURLException){
                throwMalformedURLException();
            }else{
              throwException();
            }
        }

        @Override
        protected Object doInBackground(Object... params) {
            Object ret = null;
            try {
                badgeSrv.add(activityContext, (Badge)params[0]);
            } catch (MalformedURLException ex) {
                erreur=true;
                ret= new MalformedURLException();
                Logger.getLogger(AjouterBadge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                erreur=true;
                ret= new IOException();
                Logger.getLogger(AjouterBadge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur=true;
                Logger.getLogger(AjouterBadge.class.getName()).log(Level.SEVERE, null, ex);
            }               
            return ret;
        }
    }
}
