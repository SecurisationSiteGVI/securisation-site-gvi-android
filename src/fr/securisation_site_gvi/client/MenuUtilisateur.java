package fr.securisation_site_gvi.client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuUtilisateur extends TemplateActivity {

    private Button listeDesUtilisateurs;
    private Button ajouterUnUtilisateur;
    private Button ajouterUnTechnicien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utilisateur);
        this.setThisActivityOn(MenuUtilisateur.this);
    }

    @Override
    public void initGraphicalObjects() {
        this.listeDesUtilisateurs = (Button) findViewById(R.id.listeUtilisateurs);
        this.ajouterUnUtilisateur = (Button) findViewById(R.id.ajouterUtilisateur);
        this.ajouterUnTechnicien = (Button) findViewById(R.id.ajouterTechnicien);
        this.addActionListnerForAllGraphicalObjects();
    }

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
                Toast.makeText(activityContext, "LA PAGE PAS ENCORE CREE.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
