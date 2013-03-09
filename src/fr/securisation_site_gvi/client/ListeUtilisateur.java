package fr.securisation_site_gvi.client;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import clientServiceWeb.ClientServiceWebFactory;
import clientServiceWeb.UtilisateurServiceWeb;
import entitys.Utilisateur;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListeUtilisateur extends Activity {

    private ListView listUtilisateurs;
    private Button precedent;
    private Button suivant;
    private UtilisateurServiceWeb utilisateurSrv = ClientServiceWebFactory.getPersonneClientServiceWeb();
    private int index;
    private int nbLinge = 10;
    private TextView textViewPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_utilisateur);
        this.initGraphicalObjects();
        this.index = 0;
        this.remplirListView();
    }

    public void pageSuivante() {
        this.index = this.index + nbLinge;
        this.remplirListView();
    }
    private void remplirListView(){
        List<Utilisateur> utilisateurs = null;
        try {
            utilisateurs = this.utilisateurSrv.getAll(this.index, this.nbLinge);
        } catch (Exception ex) {
            Logger.getLogger(ListeUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] listeStrings = new String[utilisateurs.size()];
        for (int i = 0; i < utilisateurs.size(); i++) {
            listeStrings[i] = utilisateurs.get(i).toString();
        }
        listUtilisateurs.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeStrings));
        this.textViewPage.setText("Page "+this.getPage());
    }

    public void pagePrécédente() {
        if (this.index <= nbLinge - 1) {
            Toast.makeText(ListeUtilisateur.this, "Vous éte déjà sur la premiere page.", Toast.LENGTH_SHORT).show();
        } else {
            this.index = this.index - nbLinge;
            this.remplirListView();
        }
    }

    public int getPage() {
        int page = index / nbLinge;
        page = page + 1;
        return page;

    }

    private void initGraphicalObjects() {
        this.listUtilisateurs = (ListView) findViewById(R.id.listeUtilisateurs);
        this.precedent = (Button) findViewById(R.id.boutonUtilisateurPrecedent);
        this.suivant = (Button) findViewById(R.id.boutonUtilisateurSuivant);
        this.textViewPage =(TextView) findViewById(R.id.listeUtilisateurAffichagePage);
        this.addActionListnerForAllGraphicalObjects();
    }

    private void addActionListnerForAllGraphicalObjects() {
//        this.listeDesUtilisateurs.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent i = new Intent(MenuUtilisateur.this,ListeUtilisateur.class);
//                startActivity(i);
//            }
//        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_accueil, menu);
        return true;
    }
}
