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
import metier.entitys.Photo;

/**
 *
 * @author damien
 */
public class HistoriquePhoto extends TemplateActivity{
    private Long id;
    private EvenementService evenementSrv = MetierFactory.getEvenementSrv();
    private Photo photo;
    private TextView textViewPhoto;
    private TextView textViewDate;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_historique_photo);
        Bundle extras = getIntent().getExtras();
        this.id = extras.getLong("id");
        try {
            this.photo = (Photo)this.evenementSrv.getById(this.activityContext, this.id);
        } catch (Exception ex) {
            Logger.getLogger(HistoriqueAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setThisActivityOn();
        
    }
    @Override
    public void initGraphicalObjects() {
        this.textViewDate = (TextView)findViewById(R.id.textViewPhotoDate);
        this.textViewPhoto =(TextView)findViewById(R.id.textViewPhoto);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
    }

    @Override
    public void addInitialValueForGraphicalObjects() {
        this.textViewDate.setText(this.photo.getDateEvt().toLocaleString());
        this.textViewPhoto.setText("Connectez vous sur le site pour visionner la photo");
    }
}
