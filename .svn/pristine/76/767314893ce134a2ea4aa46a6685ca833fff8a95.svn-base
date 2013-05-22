package fr.securisation_site_gvi.client;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import metier.MetierFactory;
import metier.UtilisateurService;
import metier.entitys.Utilisateur;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public class ListeUtilisateur extends TemplateActivity {

    private ListView listUtilisateurs;
    private Button precedent;
    private Button suivant;
    private UtilisateurService utilisateurSrv = MetierFactory.getUtilisateurSrv();
    private int index;
    private int nbLinge = 10;
    private TextView textViewPage;
    private List<Utilisateur> u;
    private int pos;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_utilisateur);
        this.setThisActivityOn();
        this.index = 0;
        if (this.isTablette7()) {
            this.nbLinge = 15;
        }
        AsyncTask<Object, Void, Object> ret = new RESTUtilisateurCont().execute();
        this.remplirListView();
    }

    private void remplirListView() {
        if (index == 0) {
            this.precedent.setEnabled(false);
        } else {
            this.precedent.setEnabled(true);
        }
        if (this.getPage() + 1 == this.getNbPages()) {
            this.suivant.setEnabled(false);
        } else {
            this.suivant.setEnabled(true);
        }
        AsyncTask<Object, Void, Object> ret = new RESTUtilisateurGetAllByRange().execute();
    }

    /**
     *
     */
    public void setTextViewPageText() {
        this.textViewPage.setText("Page " + this.getPage() + "/" + this.getNbPages());
    }

    /**
     *
     */
    public void pagePrécédente() {
        if (this.index <= nbLinge - 1) {
            Toast.makeText(ListeUtilisateur.this, "Vous éte déjà sur la premiere page.", Toast.LENGTH_SHORT).show();
        } else {
            this.index = this.index - nbLinge;
            this.remplirListView();
        }
    }

    /**
     *
     */
    public void pageSuivante() {
        this.index = this.index + nbLinge;
        this.remplirListView();
    }

    /**
     *
     * @return
     */
    public int getPage() {
        int page = index / nbLinge;
        page = page + 1;
        return page;

    }

    private int getNbPages() {
        int nbPages = this.count / this.nbLinge;
        return nbPages;
    }

    /**
     *
     */
    @Override
    public void initGraphicalObjects() {
        this.listUtilisateurs = (ListView) findViewById(R.id.listeUtilisateurs);
        this.precedent = (Button) findViewById(R.id.boutonUtilisateurPrecedent);
        this.suivant = (Button) findViewById(R.id.boutonUtilisateurSuivant);
        this.textViewPage = (TextView) findViewById(R.id.listeUtilisateurAffichagePage);
    }

    /**
     *
     */
    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.listUtilisateurs.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(ListeUtilisateur.this);
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

    private class RESTUtilisateurCont extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Récupération de l'information ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (!erreur) {
                count = (Integer) result;
                setTextViewPageText();
            } else if (result instanceof MalformedURLException) {
                throwMalformedURLException();
            } else if (result instanceof IOException) {
                throwIOException();
            } else {
                throwException();
            }
        }

        @Override
        protected Object doInBackground(Object... params) {
            Object count = null;
            try {
                count = utilisateurSrv.count(activityContext);
            } catch (MalformedURLException ex) {
                erreur = true;
                count = new MalformedURLException();
                Logger.getLogger(ListeUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                erreur = true;
                count = new IOException();
                Logger.getLogger(ListeUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur = true;
                Logger.getLogger(ListeUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
            return count;
        }
    }

    private class RESTUtilisateurGetAllByRange extends AsyncTask<Object, Void, Object> {
        private ProgressDialog progressDialog;
        private boolean erreur = false;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Récupération de l'information ...", true);
        }
        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (!erreur) {
                u = (List<Utilisateur>) result;
                String[] listeStrings = new String[u.size()];
                for (int i = 0; i < u.size(); i++) {
                    listeStrings[i] = u.get(i).toString();
                }
                listUtilisateurs.setAdapter(new ArrayAdapter<String>(activityContext, android.R.layout.simple_list_item_1, listeStrings));
                setTextViewPageText();
            } else if (result instanceof MalformedURLException) {
                throwMalformedURLException();
            } else if (result instanceof SAXException) {
                throwSAXException();
            }else if (result instanceof ParserConfigurationException) {
                throwParserConfigurationException();
            }else if (result instanceof IOException) {
                throwIOException();
            }else{
                throwException();
            }
        }
        @Override
        protected Object doInBackground(Object... params) {
            Object list = null;
            try {          
                list = utilisateurSrv.getAll(index, nbLinge, activityContext);
            } catch (SAXException ex) {
                erreur=true;
                list = new SAXException();
                Logger.getLogger(ListeUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                erreur=true;
                list = new ParserConfigurationException();
                Logger.getLogger(ListeUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                erreur=true;
                list = new MalformedURLException();
                Logger.getLogger(ListeUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                erreur=true;
                list = new IOException();
                Logger.getLogger(ListeUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur=true;
                Logger.getLogger(ListeUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
            return list;
        }
    }
}
