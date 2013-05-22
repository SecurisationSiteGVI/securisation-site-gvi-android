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
public class MenuBorneAcces extends TemplateActivity{
    private Button buttonAjouterUneBorneAcces;
    private Button buttonListerLesBorneDacces;
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
         this.buttonAjouterUneBorneAcces =(Button) findViewById(R.id.MenuPositionButtonAjouterUnePosition);
         this.buttonListerLesBorneDacces = (Button) findViewById(R.id.MenuPositionButtonListerLesPositions);
         
    }

     /**
     *
     */
    @Override
    public void addActionListnerForAllGraphicalObjects() {
         this.buttonAjouterUneBorneAcces.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {
                 Intent i = new Intent(activityContext, AjoutBorneAcces.class);
                 startActivity(i);
             }
         });
         this.buttonListerLesBorneDacces.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {
                 Intent i = new Intent(activityContext, ListeDesBorneAcces.class);
                 startActivity(i); 
                 
             }
         });
    }

     /**
     *
     */
    @Override
    public void addInitialValueForGraphicalObjects() {
         this.buttonAjouterUneBorneAcces.setText("Ajouter une borne d'acces");
         this.buttonListerLesBorneDacces.setText("Lister les bornes d'acces");
    }
}
