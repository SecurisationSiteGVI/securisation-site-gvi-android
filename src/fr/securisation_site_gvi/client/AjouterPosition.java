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
import metier.PositionService;
import metier.entitys.Position;

/**
 *
 * @author damien
 */
public class AjouterPosition extends TemplateActivity {

    private EditText editTextLongitude;
    private EditText editTextLatitude;
    private Button buttonCree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_position);
        this.setThisActivityOn();
    }

    /**
     *
     */
    @Override
    public void initGraphicalObjects() {
        this.buttonCree = (Button) findViewById(R.id.AjouterPositionButtonCree);
        this.editTextLatitude = (EditText) findViewById(R.id.AjouterPositionEditTextLatitude);
        this.editTextLongitude = (EditText) findViewById(R.id.AjouterPositionEditTextLongitude);
    }

    /**
     *
     */
    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonCree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Position position = new Position();
                Double latitude = Double.parseDouble(editTextLatitude.getText().toString());
                position.setLatitude(latitude);
                Double longitude= Double.parseDouble(editTextLongitude.getText().toString());
                position.setLongitude(longitude);
                
                new AjouterPosition.RESTPositionAdd().execute(position);
            }
        });

    }

    /**
     *
     */
    @Override
    public void addInitialValueForGraphicalObjects() {
    }

    private class RESTPositionAdd extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;
        private PositionService positionSrv= MetierFactory.getPositionService();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Envoie des données ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (!erreur) {
                Toast.makeText(activityContext, "Position bien crée", Toast.LENGTH_LONG);
            } else if (result instanceof MalformedURLException) {
                throwMalformedURLException();
            } else if (result instanceof IOException) {
                throwIOException();
            }else{
                throwException();
            }
        }

        @Override
        protected Object doInBackground(Object... params) {
            Object ret = null;
            try {
                this.positionSrv.add(activityContext, (Position) params[0]);
            } catch (MalformedURLException ex) {
                erreur=true;
                ret = new MalformedURLException();
                Logger.getLogger(AjouterPosition.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                erreur=true;
                ret = new IOException();
                Logger.getLogger(AjouterPosition.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur=true;
                Logger.getLogger(AjouterPosition.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ret;
        }
    }
}
