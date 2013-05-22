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
public class MenuSecteur extends TemplateActivity{
    private Button buttonajouterSecteur;
    private Button buttonlisteDesSecteurs;
    private Button buttonattributionSecteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_secteur);
        this.setThisActivityOn();

    }

    /**
     *
     */
    @Override
    public void initGraphicalObjects() {
        this.buttonajouterSecteur = (Button) findViewById(R.id.ajouterSecteur);
        this.buttonlisteDesSecteurs = (Button) findViewById(R.id.listeDesSecteurs);
        this.buttonattributionSecteur = (Button) findViewById(R.id.attributionSecteur);
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