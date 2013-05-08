package fr.securisation_site_gvi.client;

import android.os.Bundle;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 *
 * @author damien
 */
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

    /**
     *
     */
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

    /**
     *
     */
    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.voirHistorique.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AccueilActivity.this, Historique.class);
                startActivity(intent);
            }
        });
        this.gererLesBadges.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activityContext, MenuBadge.class);
                startActivity(intent);
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
                Intent intent = new Intent(activityContext, MenuSecteur.class);
                startActivity(intent);
            }
        });
        this.gererLesAppareils.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activityContext, MenuAppareil.class);
                startActivity(intent);
            }
        });
        this.gererLesNumeroPredefinis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activityContext, MenuNumeropredefini.class);
                startActivity(intent);
            }
        });
        this.gererLesAuthorisationAcces.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activityContext, GererAuthorisationAcces.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                this.removeNotification(12);
                return true;
            case R.id.menu_settings:
                Intent intent = new Intent(this.activityContext, Parametres.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            removeNotification(12);
            this.finish();
        }
        return false;
    }
}
