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
public class MenuAppareilFragment extends TemplateFragment{
    private Button buttonGererLesBornesAcces;
    private Button buttonGererLesCameras;
    private Button buttonGererLesDetecteursIntrusion;
    private Button buttonGererLesPositions;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_menu_appareil, container, false);
        getActivity().setTitle("Menu appareils");
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState); 
         this.activityContext = getActivity();
        this.setThisActivityOn();
      
    }
  
    @Override
     public void initGraphicalObjects() {
         this.buttonGererLesBornesAcces =(Button) this.getActivity().findViewById(R.id.MenuAppareilButtonGererLesBornesDacces);
         this.buttonGererLesCameras = (Button) this.getActivity().findViewById(R.id.MenuAppareilButtonGererLesCameras);
         this.buttonGererLesDetecteursIntrusion= (Button) this.getActivity().findViewById(R.id.MenuAppareilButtonGererLesDetecteursIntrusion);
         this.buttonGererLesPositions =(Button) this.getActivity().findViewById(R.id.MenuAppareilButtonGererLesPOsitions);
    }

     /**
     *
     */
    @Override
    public void addActionListnerForAllGraphicalObjects() {
         this.buttonGererLesBornesAcces.setOnClickListener(new View.OnClickListener() {

             public void onClick(View v) {
                 Intent intent = new Intent(activityContext, MenuBorneAcces.class);
                 startActivity(intent);
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

     /**
     *
     */
    @Override
    public void addInitialValueForGraphicalObjects() {
    }
}
