/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.BadgeService;
import metier.MetierFactory;
import metier.entitys.Badge;

/**
 *
 * @author damien
 */
public class AjouterBadge extends TemplateActivity {

    private EditText edittextNumero;
    private Button buttonValider;
    private BadgeService badgeSrv = MetierFactory.getBadgeSrv();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_badge);
        this.setThisActivityOn();
    }

    @Override
    public void initGraphicalObjects() {
        this.edittextNumero = (EditText) findViewById(R.id.editTextAjouterBadgeNumero);
        this.buttonValider = (Button) findViewById(R.id.buttonAjouterBadge);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonValider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String numero = edittextNumero.getText().toString();
                Badge b= new Badge();
                b.setNumero(numero);
                try {
                    badgeSrv.add(activityContext, b);
                } catch (Exception ex) {
                    Logger.getLogger(AjouterBadge.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
