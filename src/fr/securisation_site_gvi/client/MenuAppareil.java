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
public class MenuAppareil extends TemplateActivity{
    private Button buttonGererLesBornesAcces;
    private Button buttonGererLesCameras;
    private Button buttonGererLesDetecteursIntrusion;
    private Button buttonGererLesPositions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_appareil);
        this.setThisActivityOn();
    }
     @Override
     public void initGraphicalObjects() {
         this.buttonGererLesBornesAcces =(Button) findViewById(R.id.MenuAppareilButtonGererLesBornesDacces);
         this.buttonGererLesCameras = (Button) findViewById(R.id.MenuAppareilButtonGererLesCameras);
         this.buttonGererLesDetecteursIntrusion= (Button) findViewById(R.id.MenuAppareilButtonGererLesDetecteursIntrusion);
         this.buttonGererLesPositions =(Button) findViewById(R.id.MenuAppareilButtonGererLesPOsitions);
    }

     @Override
    public void addActionListnerForAllGraphicalObjects() {
         this.buttonGererLesBornesAcces.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {
                 throw new UnsupportedOperationException("Not supported yet.");
             }
         });
         this.buttonGererLesCameras.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {
                 Intent intent = new Intent(activityContext, MenuCamera.class);
                 startActivity(intent);
             }
         });
         this.buttonGererLesDetecteursIntrusion.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {
                 Intent intent = new Intent(activityContext, MenuDetecteurIntrusion.class);
                 startActivity(intent);
             }
         });
         this.buttonGererLesPositions.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {
                 Intent intent = new Intent(activityContext, MenuPosition.class);
                 startActivity(intent);
             }
         });
    }

     @Override
    public void addInitialValueForGraphicalObjects() {
    }
}
