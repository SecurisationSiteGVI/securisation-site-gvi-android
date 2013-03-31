/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 *
 * @author damien
 */
public class MenuBadge extends TemplateActivity {

    private Button buttonAttributionbadges;
    private Button buttonAjouterBadge;
    private Button buttonListeBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_badge);
        this.setThisActivityOn();

    }

    @Override
    public void initGraphicalObjects() {
        this.buttonAttributionbadges = (Button) findViewById(R.id.attributionbadges);
        this.buttonAjouterBadge = (Button) findViewById(R.id.ajouterBadge);
        this.buttonListeBadge = (Button) findViewById(R.id.listeBadge);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonAttributionbadges.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(activityContext, "LA PAGE PAS ENCORE CREE.", Toast.LENGTH_SHORT).show();
            }
        });
        this.buttonAjouterBadge.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(activityContext, "LA PAGE PAS ENCORE CREE.", Toast.LENGTH_SHORT).show();
            }
        });
        this.buttonListeBadge.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activityContext,ListeBadges.class);
                startActivity(intent);
            }
        });
    }
}
