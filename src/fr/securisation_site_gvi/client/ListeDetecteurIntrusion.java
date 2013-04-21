/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import metier.DetecteurIntrusionService;
import metier.MetierFactory;
import metier.entitys.DetecteurIntrusion;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public class ListeDetecteurIntrusion extends TemplateActivity {

    private ListView list;
    private Button precedent;
    private Button suivant;
    private int index;
    private int nbLinge = 9;
    private TextView textViewPage;
    private List<DetecteurIntrusion> u;
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
        new ListeDetecteurIntrusion.RESTDecteurIntrusionCount().execute();
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
        new ListeDetecteurIntrusion.RESTDetecteurIntrusionGetAllByRAnge().execute();
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
        this.list = (ListView) findViewById(R.id.listViewBadges);
        this.precedent = (Button) findViewById(R.id.buttonBadgesPrecedent);
        this.suivant = (Button) findViewById(R.id.buttonBadgesSuivant);
        this.textViewPage = (TextView) findViewById(R.id.textviewbadgePage);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
                builder.setTitle("DétecteurIntrusion séléctionné.");
                builder.setMessage("Voulez-vous supprimer le détecteur :" + u.get(pos).toString() + " ?");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DetecteurIntrusion positioncliked = u.get(pos);
                        dialog.cancel();
                        new ListeDetecteurIntrusion.RESTDetecteurIntrusionRemove().execute(positioncliked);

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

    private class RESTDetecteurIntrusionGetAllByRAnge extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;
        private DetecteurIntrusionService detecteurIntrusionSrv = MetierFactory.getDetecteurIntrusionService();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Récupération de l'information ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (!erreur) {
                List<DetecteurIntrusion> positions = (List<DetecteurIntrusion>) result;
                u = positions;
                String[] listeStrings = new String[positions.size()];
                for (int i = 0; i < positions.size(); i++) {
                    listeStrings[i] = positions.get(i).toString();
                }
                list.setAdapter(new ArrayAdapter<String>(activityContext, android.R.layout.simple_list_item_1, listeStrings));
                textViewPage.setText("Page " + getPage() + "/" + getNbPages());
            } else if (result instanceof SAXException) {
                throwSAXException();
            } else if (result instanceof ParserConfigurationException) {
                throwParserConfigurationException();
            } else if (result instanceof MalformedURLException) {
                throwMalformedURLException();
            } else if (result instanceof IOException) {
                throwIOException();
            } else if (result instanceof SAXException) {
                throwSAXException();
            } else {
                throwException();
            }
        }

        @Override
        protected Object doInBackground(Object... params) {
            Object ret = null;
            try {
                ret = this.detecteurIntrusionSrv.getAll(activityContext, index, nbLinge);
            } catch (SAXException ex) {
                erreur = true;
                ret = new SAXException();
                Logger.getLogger(ListeDetecteurIntrusion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                erreur = true;
                ret = new ParserConfigurationException();
                Logger.getLogger(ListeDetecteurIntrusion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                erreur = true;
                ret = new MalformedURLException();
                Logger.getLogger(ListeDetecteurIntrusion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                erreur = true;
                ret = new IOException();
                Logger.getLogger(ListeDetecteurIntrusion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur = true;
                Logger.getLogger(ListeDetecteurIntrusion.class.getName()).log(Level.SEVERE, null, ex);
            }

            return ret;
        }
    }

    private class RESTDecteurIntrusionCount extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;
        private DetecteurIntrusionService detecteurIntrusionSrv = MetierFactory.getDetecteurIntrusionService();

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
                remplirListView();
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
            Object ret = null;
            try {
                ret = this.detecteurIntrusionSrv.count(activityContext);
            } catch (MalformedURLException ex) {
                erreur = true;
                ret = new MalformedURLException();
                Logger.getLogger(ListeBadges.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                erreur = true;
                ret = new IOException();
                Logger.getLogger(ListeBadges.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur = true;
                Logger.getLogger(ListeBadges.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ret;
        }
    }

    private class RESTDetecteurIntrusionRemove extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;
        private DetecteurIntrusionService detecteurIntrusionSrv = MetierFactory.getDetecteurIntrusionService();

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Suppression du détecteur d'intrusion...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
         this.progressDialog.cancel();
            if (!erreur) {remplirListView();
                    Toast.makeText(activityContext, "Détecteur intrusion bien supprimé.", Toast.LENGTH_LONG).show();
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
            Object ret = null;
            try {
                detecteurIntrusionSrv.remove(activityContext, (DetecteurIntrusion) params[0]);
            } catch (MalformedURLException ex) {
                erreur = true;
                ret = new MalformedURLException();
                Logger.getLogger(ListeBadges.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                erreur = true;
                ret = new IOException();
                Logger.getLogger(ListeBadges.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur = true;
                Logger.getLogger(ListeBadges.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return ret;
        }
    }
}
