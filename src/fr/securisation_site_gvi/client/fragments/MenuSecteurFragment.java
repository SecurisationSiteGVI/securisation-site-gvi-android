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
public class MenuSecteurFragment extends TemplateFragment{
    private Button buttonajouterSecteur;
    private Button buttonlisteDesSecteurs;
    private Button buttonattributionSecteur;

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_menu_secteur, container, false);
        getActivity().setTitle("Menu secteurs");
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
        this.buttonajouterSecteur = (Button) this.getActivity().findViewById(R.id.ajouterSecteur);
        this.buttonlisteDesSecteurs = (Button) this.getActivity().findViewById(R.id.listeDesSecteurs);
        this.buttonattributionSecteur = (Button) this.getActivity().findViewById(R.id.attributionSecteur);
    }

    /**
     *
     */
    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonajouterSecteur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activityContext, AjoutSecteur.class);
                startActivity(intent);
            }
        });
        this.buttonlisteDesSecteurs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activityContext, ListeSecteur.class);
                startActivity(intent);
            }
        });
        this.buttonattributionSecteur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activityContext, AttributionSecteur.class);
                startActivity(intent);
            }
        });
    }
}