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
import android.widget.Toast;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.MetierFactory;
import metier.NumeroPredefiniService;

/**
 *
 * @author damien
 */
public class AjouterNumero extends TemplateActivity {

    private EditText edittextNumero;
    private Button buttonCree;
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_numero);
        this.setThisActivityOn();
    }

    /**
     *
     */
    @Override
    public void initGraphicalObjects() {
        this.edittextNumero = (EditText) findViewById(R.id.AjouterNumeroEditTextNumero);
        this.buttonCree = (Button) findViewById(R.id.AjouterNumeroButtonCree);
    }

    /**
     *
     */
    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonCree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new RESTNumeroPredefiniAjout().execute(edittextNumero.getText().toString());
                
            }
        });
    }
    private class RESTNumeroPredefiniAjout extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;
        private NumeroPredefiniService numeroPredefiniSrv = MetierFactory.getNumeroPredefiniService();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Envoie des données ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (!erreur) {
                Toast.makeText(activityContext, "Numéro bien ajouté.", Toast.LENGTH_LONG).show();
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
                numeroPredefiniSrv.add(activityContext, (String)params[0]);
            } catch (MalformedURLException ex) {
                erreur=true;
                ret= new MalformedURLException();
                Logger.getLogger(AjouterNumero.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                erreur=true;
                ret= new IOException();
                Logger.getLogger(AjouterNumero.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur=true;
                Logger.getLogger(AjouterNumero.class.getName()).log(Level.SEVERE, null, ex);
            }               
            return ret;
        }
    }
}
