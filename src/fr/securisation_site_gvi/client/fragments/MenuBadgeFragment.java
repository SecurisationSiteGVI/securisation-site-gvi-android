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
public class MenuBadgeFragment extends TemplateFragment {

    private Button buttonAttributionbadges;
    private Button buttonAjouterBadge;
    private Button buttonListeBadge;

         @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_menu_badge, container, false);
        getActivity().setTitle("Menu appareils");
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
        this.buttonAttributionbadges = (Button) this.getActivity().findViewById(R.id.attributionbadges);
        this.buttonAjouterBadge = (Button) this.getActivity().findViewById(R.id.ajouterBadge);
        this.buttonListeBadge = (Button) this.getActivity().findViewById(R.id.listeBadge);
    }

    /**
     *
     */
    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonAttributionbadges.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activityContext, AttributionBadge.class);
                startActivity(intent);
            }
        });
        this.buttonAjouterBadge.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activityContext, AjouterBadge.class);
                startActivity(intent);
            }
        });
        this.buttonListeBadge.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activityContext, ListeBadges.class);
                startActivity(intent);
            }
        });
    }
}
