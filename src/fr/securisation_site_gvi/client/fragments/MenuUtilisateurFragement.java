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
public class MenuUtilisateurFragement extends TemplateFragment {

    private Button listeDesUtilisateurs;
    private Button ajouterUnUtilisateur;
    private Button ajouterUnTechnicien;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_menu_utilisateur, container, false);
        getActivity().setTitle("Menu utilisateurs");
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
        this.listeDesUtilisateurs = (Button) this.getActivity().findViewById(R.id.listeUtilisateurs);
        this.ajouterUnUtilisateur = (Button) this.getActivity().findViewById(R.id.ajouterUtilisateur);
        this.ajouterUnTechnicien = (Button) this.getActivity().findViewById(R.id.ajouterTechnicien);
    }

    /**
     *
     */
    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.listeDesUtilisateurs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(activityContext, ListeUtilisateur.class);
                startActivity(i);
            }
        });
        this.ajouterUnUtilisateur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(activityContext, AjouterUtilisateur.class);
                startActivity(i);
            }
        });
        this.ajouterUnTechnicien.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(activityContext, AjouterTechnicien.class);
                startActivity(i);
            }
        });
    }
}
