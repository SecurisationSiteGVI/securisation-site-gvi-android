/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.EvenementService;
import metier.MetierFactory;
import metier.entitys.Acces;
import metier.entitys.Intrusion;

/**
 *
 * @author damien
 */
public class HistoriqueIntrusion extends TemplateActivity {

    private Long id;
    private TextView textViewDate;
    private TextView textViewDetecteurIntrusion;
    private Intrusion intrusion;
    private Button buttonretour;
    private EvenementService evenementSrv = MetierFactory.getEvenementSrv();

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_historique_intrusion);
        Bundle extras = getIntent().getExtras();
        this.id = extras.getLong("id");
        try {
            this.intrusion = (Intrusion) this.evenementSrv.getById(this.activityContext, this.id);
        } catch (Exception ex) {
            Logger.getLogger(HistoriqueAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setThisActivityOn();

    }

    @Override
    public void initGraphicalObjects() {
        this.buttonretour = (Button) findViewById(R.id.buttonIntrusionRetour);
        this.textViewDate = (TextView) findViewById(R.id.textViewIntrusionDate);
        this.textViewDetecteurIntrusion = (TextView) findViewById(R.id.textViewIntrusionDetecteurIntrusion);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonretour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void addInitialValueForGraphicalObjects() {
        this.textViewDate.setText(this.intrusion.getDateEvt().toLocaleString());
        this.textViewDetecteurIntrusion.setText(this.intrusion.getDetecteurIntrusion().toString());

    }
}
