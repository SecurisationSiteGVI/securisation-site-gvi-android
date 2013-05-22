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
import metier.EvenementService;
import metier.MetierFactory;
import metier.entitys.Acces;
import metier.entitys.Evenement;
import metier.entitys.Intrusion;
import metier.entitys.Photo;

public class Historique extends TemplateActivity {

    private ListView listEvenements;
    private Button precedent;
    private Button suivant;
    private EvenementService evenementSrv = MetierFactory.getEvenementSrv();
    private int index;
    private int nbLinge = 9;
    private TextView textViewPage;
    private List<Evenement> u;
    private int pos;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
        this.setThisActivityOn();
        this.index = 0;
        if (this.isTablette7()) {
            this.nbLinge = 15;
        }
        try {
            this.count = this.evenementSrv.count(this.activityContext);
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
        List<Evenement> utilisateurs = null;
        try {
            utilisateurs = this.evenementSrv.getAll(this.activityContext, this.index, this.nbLinge);
            this.u = utilisateurs;
        } catch (Exception ex) {
            Logger.getLogger(ListeUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] listeStrings = new String[utilisateurs.size()];
        for (int i = 0; i < utilisateurs.size(); i++) {
            listeStrings[i] = utilisateurs.get(i).toString();
        }
        listEvenements.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeStrings));
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
        this.listEvenements = (ListView) findViewById(R.id.HistoriquelisteEvenements);
        this.precedent = (Button) findViewById(R.id.HistoriqueboutonPrecedent);
        this.suivant = (Button) findViewById(R.id.HistoriqueboutonSuivant);
        this.textViewPage = (TextView) findViewById(R.id.HistoriquetextPage);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.listEvenements.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
                builder.setTitle("Evenement séléctionné.");
                builder.setMessage("Vous avez séléctionné un évement voulez vous avoir des détails ?");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Long idCliked = u.get(pos).getId();
                        Evenement evenement = null;
                        try {
                            evenement = evenementSrv.getById(activityContext, idCliked);
                        } catch (Exception ex) {
                            Logger.getLogger(Historique.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (evenement instanceof Acces) {
                            Intent i = new Intent(activityContext, HistoriqueAcces.class);
                            i.putExtra("id", u.get(pos).getId());
                            startActivityForResult(i, 0);
                        } else if (evenement instanceof Photo) {
                            Intent i = new Intent(activityContext, HistoriquePhoto.class);
                            i.putExtra("id", u.get(pos).getId());
                            startActivityForResult(i, 0);
                        } else if (evenement instanceof Intrusion) {
                            Intent i = new Intent(activityContext, HistoriqueIntrusion.class);
                            i.putExtra("id", u.get(pos).getId());
                            startActivityForResult(i, 0);
                        }
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
