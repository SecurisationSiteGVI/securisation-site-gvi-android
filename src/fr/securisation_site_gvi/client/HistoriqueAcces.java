/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import android.os.Bundle;
import android.widget.TextView;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.EvenementService;
import metier.MetierFactory;
import metier.entitys.Acces;

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
    private EvenementService evenementSrv = MetierFactory.getEvenementSrv();
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_historique_acces);
        Bundle extras = getIntent().getExtras();
        this.id = extras.getLong("id");
        try {
            this.acces = (Acces)this.evenementSrv.getById(this.activityContext, this.id);
        } catch (Exception ex) {
            Logger.getLogger(HistoriqueAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setThisActivityOn();
        
    }

    @Override
    public void initGraphicalObjects() {
        this.textViewDate  = (TextView) findViewById(R.id.textViewAccesDate);
        this.textViewBorneAcces  = (TextView) findViewById(R.id.textViewAccesBorneAcces);
        this.textViewUtilisateur = (TextView) findViewById(R.id.textViewAccesUtilisateur);
        this.textViewPassage = (TextView) findViewById(R.id.textViewAccesPassage);
    }

    
    @Override
    public void addInitialValueForGraphicalObjects() {
        this.textViewDate.setText(this.acces.getDateEvt().toLocaleString());
        this.textViewUtilisateur.setText(this.acces.getUtilisateur().toString());
        if(this.acces.getPassage()){
            this.textViewPassage.setText("Authorisé");
        }else{
            this.textViewPassage.setText("Refusé");
        }
        this.textViewBorneAcces.setText(this.acces.getBorneAcces().toString());
        
    }
}
