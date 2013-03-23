/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import metier.entitys.Evenement;

/**
 *
 * @author damien
 */
public class VoirHistorique extends TemplateActivity {

    private Button buttonPrecendent;
    private Button buttonSuivant;
    private TextView textViewPage;
    private ListView listeHistorique;
    private int pos;
    private int index;
    private int nbLinge = 10;
    private List<Evenement> u;
    private int count;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.ajouter_utilisateur);
        this.setThisActivityOn(VoirHistorique.this);
    }

    @Override
    public void initGraphicalObjects() {
        this.listeHistorique = (ListView) findViewById(R.id.listeHistorique);
        this.buttonPrecendent = (Button) findViewById(R.id.boutonHistoriquePrecedent);
        this.buttonSuivant = (Button) findViewById(R.id.boutonHistoriqueSuivant);
        this.textViewPage = (TextView) findViewById(R.id.listeHistoriqueAffichagePage);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.listeHistorique.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
                builder.setTitle("Utilisateur séléctionné.");
                builder.setMessage(u.get(position).toString() + " à été séléctionné voulez vous le modifier ?");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(activityContext, ModifierUtilisateur.class);
                        i.putExtra("id", u.get(pos).getId());
                        startActivityForResult(i, 0);
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
        this.buttonPrecendent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pagePrécédente();
            }
        });
        this.buttonSuivant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pageSuivante();
            }
        });
    }

    private void remplirListView() {
        if (index == 0) {
            this.buttonPrecendent.setEnabled(false);
        } else {
            this.buttonPrecendent.setEnabled(true);
        }
        if (this.getPage() + 1 == this.getNbPages()) {
            this.buttonSuivant.setEnabled(false);
        } else {
            this.buttonSuivant.setEnabled(true);
        }
        List<Evenement> utilisateurs = null;
        try {
            //utilisateurs = this.utilisateurSrv.getAll(this.index, this.nbLinge, this.activityContext);
            this.u = utilisateurs;
        } catch (Exception ex) {
            Logger.getLogger(VoirHistorique.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] listeStrings = new String[utilisateurs.size()];
        for (int i = 0; i < utilisateurs.size(); i++) {
            listeStrings[i] = utilisateurs.get(i).toString();
        }
        this.listeHistorique.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeStrings));
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
        return nbPages;
    }
}
