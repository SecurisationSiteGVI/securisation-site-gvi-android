/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client.fragments;

import fr.securisation_site_gvi.client.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 *
 * @author damien
 */
public class MenuNumeropredefiniFragment extends TemplateFragment{
    private Button buttonAjouterUnNumero;
    private Button buttonListerLesNumeros;
   
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_menu_position, container, false);
        getActivity().setTitle("Menu numéros prédéfini");
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState); 
         this.activityContext = getActivity();
        this.setThisActivityOn();
      
    }
     /**
     *
     */
    @Override
     public void initGraphicalObjects() {
         this.buttonAjouterUnNumero =(Button) this.getActivity().findViewById(R.id.MenuPositionButtonAjouterUnePosition);
         this.buttonListerLesNumeros = (Button) this.getActivity().findViewById(R.id.MenuPositionButtonListerLesPositions);
         
    }

     /**
     *
     */
    @Override
    public void addActionListnerForAllGraphicalObjects() {
         this.buttonAjouterUnNumero.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {
                 Intent i = new Intent(activityContext, fr.securisation_site_gvi.client.AjouterNumero.class);
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
