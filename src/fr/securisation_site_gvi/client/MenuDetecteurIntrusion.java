/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 *
 * @author damien
 */
public class MenuDetecteurIntrusion extends TemplateActivity{
     private Button buttonAjouterUnDetecteur;
    private Button buttonListerLesDetecteurs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_position);
        this.setThisActivityOn();
    }
     /**
     *
     */
    @Override
     public void initGraphicalObjects() {
         this.buttonAjouterUnDetecteur =(Button) findViewById(R.id.MenuPositionButtonAjouterUnePosition);
         this.buttonListerLesDetecteurs = (Button) findViewById(R.id.MenuPositionButtonListerLesPositions);
         
    }

     /**
     *
     */
    @Override
    public void addActionListnerForAllGraphicalObjects() {
         this.buttonAjouterUnDetecteur.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {
                 Intent i = new Intent(activityContext, AjouterDetecteurIntrusion.class);
                 startActivity(i);
             }
         });
         this.buttonListerLesDetecteurs.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {
                 Intent i = new Intent(activityContext, ListeDetecteurIntrusion.class);
                 startActivity(i); 
                 
             }
         });
    }

     /**
     *
     */
    @Override
    public void addInitialValueForGraphicalObjects() {
         this.buttonAjouterUnDetecteur.setText("Ajouter un détécteur");
         this.buttonListerLesDetecteurs.setText("Lister les détécteurs");
    }
}
