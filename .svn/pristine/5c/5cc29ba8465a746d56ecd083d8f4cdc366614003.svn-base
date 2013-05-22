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
public class MenuNumeropredefini extends TemplateActivity{
    private Button buttonAjouterUnNumero;
    private Button buttonListerLesNumeros;
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
         this.buttonAjouterUnNumero =(Button) findViewById(R.id.MenuPositionButtonAjouterUnePosition);
         this.buttonListerLesNumeros = (Button) findViewById(R.id.MenuPositionButtonListerLesPositions);
         
    }

     /**
     *
     */
    @Override
    public void addActionListnerForAllGraphicalObjects() {
         this.buttonAjouterUnNumero.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {
                 Intent i = new Intent(activityContext, AjouterNumero.class);
                 startActivity(i);
             }
         });
         this.buttonListerLesNumeros.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {
                 Intent i = new Intent(activityContext, ListeNumeroPredefini.class);
                 startActivity(i); 
                 
             }
         });
    }

     /**
     *
     */
    @Override
    public void addInitialValueForGraphicalObjects() {
         this.buttonAjouterUnNumero.setText("Ajouter un numéro");
         this.buttonListerLesNumeros.setText("Lister les numéros prédéfini");
    }
}
