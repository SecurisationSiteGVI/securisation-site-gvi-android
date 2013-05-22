package fr.securisation_site_gvi.client;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AccueilActivity extends TemplateActivity {

    private Button voirHistorique;
    private Button gererLesBadges;
    private Button gererLesUtilisateurs;
    private Button gererLesSecteurs;
    private Button gererLesAppareils;
    private Button gererLesNumeroPredefinis;
    private Button gererLesAuthorisationAcces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueilactivity);
        this.setThisActivityOn();
    }

    @Override
    public void initGraphicalObjects() {
        this.voirHistorique = (Button) findViewById(R.id.voirHistorique);
        this.gererLesBadges = (Button) findViewById(R.id.gererLesBadges);
        this.gererLesUtilisateurs = (Button) findViewById(R.id.gererLesUtilisateurs);
        this.gererLesSecteurs = (Button) findViewById(R.id.gererLesSecteur);
        this.gererLesAppareils = (Button) findViewById(R.id.gererLesAppareils);
        this.gererLesNumeroPredefinis = (Button) findViewById(R.id.gererLesNumerosPredéfinis);
        this.voirHistorique = (Button) findViewById(R.id.voirHistorique);
        this.gererLesAuthorisationAcces = (Button) findViewById(R.id.gererLesAuthorisationDacces);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.voirHistorique.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent  = new Intent(AccueilActivity.this, Historique.class);
                startActivity(intent);
            }
        });
        this.gererLesBadges.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(AccueilActivity.this, "LA PAGE PAS ENCORE CREE.", Toast.LENGTH_SHORT).show();
            }
        });
        this.gererLesUtilisateurs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AccueilActivity.this, MenuUtilisateur.class);
                startActivity(intent);
            }
        });
        this.gererLesSecteurs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(AccueilActivity.this, "LA PAGE PAS ENCORE CREE.", Toast.LENGTH_SHORT).show();
            }
        });
        this.gererLesAppareils.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(AccueilActivity.this, "LA PAGE PAS ENCORE CREE.", Toast.LENGTH_SHORT).show();
            }
        });
        this.gererLesNumeroPredefinis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(AccueilActivity.this, "LA PAGE PAS ENCORE CREE.", Toast.LENGTH_SHORT).show();
            }
        });
        this.gererLesAuthorisationAcces.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(AccueilActivity.this, "LA PAGE PAS ENCORE CREE.", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
