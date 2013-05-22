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
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import metier.EvenementService;
import metier.MetierFactory;
import metier.entitys.Acces;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public class HistoriqueAcces extends TemplateActivity {

    private Long id;
    private TextView textViewDate;
    private TextView textViewUtilisateur;
    private TextView textViewPassage;
    private TextView textViewBorneAcces;
    private Acces acces;
    private Button buttonretour;
    private EvenementService evenementSrv = MetierFactory.getEvenementSrv();

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_historique_acces);
        Bundle extras = getIntent().getExtras();
        this.id = extras.getLong("id");
        new HistoriqueAcces.RESTEvenementGetById().execute();
    }

    /**
     *
     */
    @Override
    public void initGraphicalObjects() {
        this.buttonretour = (Button) findViewById(R.id.buttonAccesRetour);
        this.textViewDate = (TextView) findViewById(R.id.textViewAccesDate);
        this.textViewBorneAcces = (TextView) findViewById(R.id.textViewAccesBorneAcces);
        this.textViewUtilisateur = (TextView) findViewById(R.id.textViewAccesUtilisateur);
        this.textViewPassage = (TextView) findViewById(R.id.textViewAccesPassage);
    }

    /**
     *
     */
    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonretour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     *
     */
    @Override
    public void addInitialValueForGraphicalObjects() {
        this.textViewDate.setText(this.acces.getDateEvt().toLocaleString());
        this.textViewUtilisateur.setText(this.acces.getUtilisateur().toString());
        if (this.acces.getPassage()) {
            this.textViewPassage.setText("Authorisé");
        } else {
            this.textViewPassage.setText("Refusé");
        }
        this.textViewBorneAcces.setText(this.acces.getBorneAcces().toString());

    }

    private class RESTEvenementGetById extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Récupération des informations ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (!erreur) {
                acces = (Acces) result;
                setThisActivityOn();
            } else if (result instanceof MalformedURLException) {
                throwMalformedURLException();
            } else if (result instanceof SAXException) {
                throwSAXException();
            } else if (result instanceof ParseException) {
                throwParseException();
            } else if (result instanceof ParserConfigurationException) {
                throwParseException();
            } else if (result instanceof IOException) {
                throwIOException();
            } else {
                throwException();
            }
        }

        @Override
        protected Object doInBackground(Object... params) {
            Object ret = null;
            try {
                ret = evenementSrv.getById(activityContext, id);
            } catch (MalformedURLException ex) {
                erreur = true;
                ret = new MalformedURLException();
                Logger.getLogger(HistoriqueAcces.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                erreur = true;
                ret = new IOException();
                Logger.getLogger(HistoriqueAcces.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                erreur = true;
                ret = new SAXException();
                Logger.getLogger(HistoriqueAcces.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                erreur = true;
                ret = new ParseException(" ", 1);
                Logger.getLogger(HistoriqueAcces.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                erreur = true;
                ret = new ParserConfigurationException();
                Logger.getLogger(HistoriqueAcces.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur = true;
                Logger.getLogger(HistoriqueAcces.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ret;
        }
    }
}
