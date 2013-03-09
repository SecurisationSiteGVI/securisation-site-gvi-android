package fr.securisation_site_gvi.client;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuUtilisateur extends Activity {

    
    private Button listeDesUtilisateurs;
    private Button ajouterUnUtilisateur;
    private Button ajouterUnTechnicien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utilisateur);
        this.initGraphicalObjects();
    }
    private void initGraphicalObjects() {
        this.listeDesUtilisateurs = (Button) findViewById(R.id.listeUtilisateurs);
        this.ajouterUnUtilisateur = (Button) findViewById(R.id.ajouterUtilisateur);
        this.ajouterUnTechnicien = (Button) findViewById(R.id.ajouterTechnicien);
        this.addActionListnerForAllGraphicalObjects();
    }

    private void addActionListnerForAllGraphicalObjects() {
        this.listeDesUtilisateurs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MenuUtilisateur.this,ListeUtilisateur.class);
                startActivity(i);
            }
        });
        this.ajouterUnUtilisateur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 Toast.makeText(MenuUtilisateur.this, "LA PAGE PAS ENCORE CREE.", Toast.LENGTH_SHORT).show();
            }
        });
        this.ajouterUnTechnicien.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MenuUtilisateur.this, "LA PAGE PAS ENCORE CREE.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_accueil, menu);
        return true;
    }
}
