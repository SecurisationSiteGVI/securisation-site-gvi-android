/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.BadgeService;
import metier.MetierFactory;
import metier.entitys.Badge;

/**
 *
 * @author damien
 */
public class ListeBadges extends TemplateActivity {

    private ListView listBadges;
    private Button precedent;
    private Button suivant;
    private BadgeService badgeSrv = MetierFactory.getBadgeSrv();
    private int index;
    private int nbLinge = 9;
    private TextView textViewPage;
    private List<Badge> u;
    private int pos;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listebadges);
        this.setThisActivityOn();
        this.index = 0;
        if (this.isTablette7()) {
            this.nbLinge = 15;
        }
        try {
            this.count = this.badgeSrv.count(this.activityContext);
        } catch (Exception ex) {
            Logger.getLogger(ListeUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.remplirListView();
    }

    private void remplirListView() {
        if (index == 0) {
            this.precedent.setEnabled(false);
        } else {
            this.precedent.setEnabled(true);
        }
        if (this.getPage() + 1 == this.getNbPages() + 1) {
            this.suivant.setEnabled(false);
        } else {
            this.suivant.setEnabled(true);
        }
        List<Badge> badges = null;
        try {
            badges = this.badgeSrv.getAll(this.activityContext, this.index, this.nbLinge);
            this.u = badges;
        } catch (Exception ex) {
            Logger.getLogger(ListeUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] listeStrings = new String[badges.size()];
        for (int i = 0; i < badges.size(); i++) {
            listeStrings[i] = badges.get(i).toString();
        }
        listBadges.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeStrings));
        this.textViewPage.setText("Page " + this.getPage() + "/" + this.getNbPages());
    }

    public void pagePrécédente() {
        if (this.index <= nbLinge - 1) {
            Toast.makeText(activityContext, "Vous éte déjà sur la premiere page.", Toast.LENGTH_SHORT).show();
        } else {
            this.index = this.index - nbLinge;
            this.remplirListView();
        }
    }

    public void pageSuivante() {
        if (this.getPage() >= this.getNbPages()) {
            Toast.makeText(this.activityContext, "Vous étes déjà sur la derniére page", Toast.LENGTH_LONG).show();
            this.suivant.setEnabled(false);
        }
        this.index = this.index + nbLinge;
        this.remplirListView();
    }

    public int getPage() {
        int page = index / nbLinge;
        page = page + 1;
        return page;
    }

    private int getNbPages() {
        int nbPages = this.count / this.nbLinge;
        return nbPages + 1;
    }

    @Override
    public void initGraphicalObjects() {
        this.listBadges = (ListView) findViewById(R.id.listViewBadges);
        this.precedent = (Button) findViewById(R.id.buttonBadgesPrecedent);
        this.suivant = (Button) findViewById(R.id.buttonBadgesSuivant);
        this.textViewPage = (TextView) findViewById(R.id.textviewbadgePage);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.listBadges.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
                builder.setTitle("badge séléctionné.");
                builder.setMessage("Voulez-vous supprimer le badge n°" + u.get(pos).getNumero() + " ?");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Badge badgeCliked = u.get(pos);
                        try {
                            badgeSrv.remove(activityContext, badgeCliked);
                        } catch (Exception ex) {
                            Logger.getLogger(ListeBadges.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        remplirListView();
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        this.precedent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pagePrécédente();
            }
        });
        this.suivant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pageSuivante();
            }
        });
    }
}
