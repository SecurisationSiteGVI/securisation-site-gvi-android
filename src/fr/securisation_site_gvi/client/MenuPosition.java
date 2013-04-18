/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 *
 * @author damien
 */
public class MenuPosition extends TemplateActivity{
     private Button buttonAjouterUnePosition;
    private Button buttonListerLesPositions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_position);
        this.setThisActivityOn();
    }
     @Override
     public void initGraphicalObjects() {
         this.buttonAjouterUnePosition =(Button) findViewById(R.id.MenuPositionButtonAjouterUnePosition);
         this.buttonListerLesPositions = (Button) findViewById(R.id.MenuPositionButtonListerLesPositions);
         
    }

     @Override
    public void addActionListnerForAllGraphicalObjects() {
         this.buttonAjouterUnePosition.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {
                 throw new UnsupportedOperationException("Not supported yet.");
             }
         });
         this.buttonListerLesPositions.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {
                 throw new UnsupportedOperationException("Not supported yet."); 
                 
             }
         });
    }

     @Override
    public void addInitialValueForGraphicalObjects() {
    }
}
