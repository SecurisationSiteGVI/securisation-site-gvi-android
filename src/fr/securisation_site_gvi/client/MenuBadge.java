/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import android.os.Bundle;
import android.widget.Button;

/**
 *
 * @author damien
 */
public class MenuBadge extends TemplateActivity{
    
    private Button buttonAttributionbadges;
    private Button buttonAjouterBadge;
    private Button buttonListeBadge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_badge);
        this.setThisActivityOn();
        
    }
    public void initGraphicalObjects() {
        this.buttonAttributionbadges = (Button) findViewById(R.id.attributionbadges);
        this.buttonAjouterBadge = (Button) findViewById(R.id.ajouterBadge);
        this.buttonListeBadge = (Button) findViewById(R.id.listeBadge);
    }

    public void addActionListnerForAllGraphicalObjects() {
    }

    public void addInitialValueForGraphicalObjects() {
    }
}
