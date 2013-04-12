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
import android.view.LayoutInflater;
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
import metier.AttributionSecteurCameraService;
import metier.BorneAccesService;
import metier.MetierFactory;
import metier.SecteurService;
import metier.entitys.AttributionSecteurCamera;
import metier.entitys.BorneAcces;
import metier.entitys.Camera;
import metier.entitys.Secteur;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public class AttributionSecteur extends TemplateActivity {

    private int nbLinge = 8;
    private Button buttonSelectSecteur;
    private ListView listViewSecteur;
    private Button buttonAjouterBorneAcces;
    private Button buttonAjouterCamera;
    private Button buttonAjouterDetecteurIntrusion;
    private ListView list;
    private Button precedent;
    private Button suivant;
    private TextView textViewPage;
    private int index;
    private List<Secteur> secteurs;
    private List<BorneAcces> borneAcceses;
    private int pos;
    private Secteur secteurSelected;
    private AlertDialog alertDialogInCurrent;
    private int countSecteurs;
    private String switchs;
    private SecteurService secteurSrv = MetierFactory.getSecteurServ();
    private BorneAccesService borneAccesSrv = MetierFactory.getBorneAccesService();
    private AttributionSecteurCameraService attributionSecteurCameraSrv = MetierFactory.getAttributionSecteurCameraSrv();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attribution_secteur);
        if (this.isTablette7()) {
            this.nbLinge = 14;
        }
        this.index = 0;
//        new AttributionBadge.RESTUtilisateurCount().execute();
//        new AttributionBadge.RESTBadgeCount().execute();
        this.setThisActivityOn();
    }

    @Override
    public void initGraphicalObjects() {
        this.buttonSelectSecteur = (Button) findViewById(R.id.AttributionSecteurButtonSelectsecteur);
        this.listViewSecteur = (ListView) findViewById(R.id.attributionSecteurListSecteur);
        this.buttonAjouterBorneAcces = (Button) findViewById(R.id.attributionSecteurButtonAjouterBorne);
        this.buttonAjouterCamera = (Button) findViewById(R.id.attributionSecteurButtonAjouterCamera);
        this.buttonAjouterDetecteurIntrusion = (Button) findViewById(R.id.attributionSecteurButtonAjouterDetecteurIntrusion);
    }

    public void listerListe() {
        new AttributionSecteur.RESTAttributionSecteurCameraGetBySecteur().execute();
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonSelectSecteur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switchs = "Secteur";
                index = 0;
                AlertDialog.Builder builder = new AlertDialog.Builder(activityContext, AlertDialog.THEME_HOLO_LIGHT);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_custom_titile, null);
                TextView title = (TextView) view.findViewById(R.id.myTitle);
                title.setText("Sélectonner un secteur.");
                builder.setCustomTitle(view);
                builder.setView(view);
                builder.setCancelable(true);
                View content = View.inflate(activityContext, R.layout.activity_liste_secteur, null);
                list = (ListView) content.findViewById(R.id.SecteurListView);
                precedent = (Button) content.findViewById(R.id.SecteurButtonPrecedent);
                suivant = (Button) content.findViewById(R.id.SecteurButtonSuivant);
                textViewPage = (TextView) content.findViewById(R.id.SecteurTextviewPage);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
                        pos = position;
                        AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
                        builder.setTitle("Secteur séléctionné.");
                        builder.setMessage("Voulez-vous séléctionner le secteur " + secteurs.get(pos).getNom() + " ?");
                        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Secteur secteurCliked = (Secteur) secteurs.get(pos);
                                secteurSelected = secteurCliked;
                                listerListe();
                                buttonSelectSecteur.setText(secteurCliked.toString());
                                alertDialogInCurrent.cancel();
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
                precedent.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        pagePrécédente();
                    }
                });
                suivant.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        pageSuivante();
                    }
                });
                remplirListView();
                builder.setView(content);
                alertDialogInCurrent = builder.create();
                alertDialogInCurrent.show();
            }
        });

        this.buttonAjouterBorneAcces.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switchs = "BorneAcces";
                index = 0;
                AlertDialog.Builder builder = new AlertDialog.Builder(activityContext, AlertDialog.THEME_HOLO_LIGHT);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_custom_titile, null);
                TextView title = (TextView) view.findViewById(R.id.myTitle);
                title.setText("Sélectonner une borne d'acces.");
                builder.setCustomTitle(view);
                builder.setView(view);
                builder.setCancelable(true);
                View content = View.inflate(activityContext, R.layout.activity_listebadges, null);
                list = (ListView) content.findViewById(R.id.listViewBadges);
                precedent = (Button) content.findViewById(R.id.buttonBadgesPrecedent);
                suivant = (Button) content.findViewById(R.id.buttonBadgesSuivant);
                textViewPage = (TextView) content.findViewById(R.id.textviewbadgePage);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        pos = position;
                        AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
                        builder.setTitle("Borne acces séléctionné.");
                        List<BorneAcces> acceses = (List<BorneAcces>) borneAcceses;
                        builder.setMessage("Voulez-vous séléctionner la borne d'acces " + acceses.get(pos).getNom() + " ?");
                        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                BorneAcces secteurCliked = (BorneAcces) borneAcceses.get(pos);
                                // TO DO
                                alertDialogInCurrent.cancel();
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
                precedent.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        pagePrécédente();
                    }
                });
                suivant.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        pageSuivante();
                    }
                });
                remplirListView();
                builder.setView(content);
                alertDialogInCurrent = builder.create();
                alertDialogInCurrent.show();
            }
        });
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
        if (switchs.equals("Secteur")) {
            new AttributionSecteur.RESTSecteurGetAll().execute();
        } else if (switchs.equals("BorneAcces")) {
            new AttributionSecteur.RESTBorneAccesGetAll().execute();
        }
    }

    @Override
    public void addInitialValueForGraphicalObjects() {
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
        int nbPages = 0;
        nbPages = this.countSecteurs / this.nbLinge;
        return nbPages + 1;
    }

    private class RESTSecteurGetAll extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Récupération des données ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (!erreur) {
                secteurs = (List<Secteur>) result;
                String[] listeStrings = new String[secteurs.size()];
                for (int i = 0; i < secteurs.size(); i++) {
                    listeStrings[i] = secteurs.get(i).toString();
                }
                list.setAdapter(new ArrayAdapter<String>(activityContext, android.R.layout.simple_list_item_1, listeStrings));
                textViewPage.setText("Page " + getPage() + "/" + getNbPages());
            } else if (result instanceof ParserConfigurationException) {
                throwParserConfigurationException();
            } else if (result instanceof IOException) {
                throwIOException();
            } else if (result instanceof SAXException) {
                throwIOException();
            } else if (result instanceof MalformedURLException) {
                throwMalformedURLException();
            } else {
                throwException();
            }
        }

        @Override
        protected Object doInBackground(Object... params) {
            Object ret = null;
            try {
                ret = secteurSrv.getAll(activityContext, index, nbLinge);
            } catch (ParserConfigurationException ex) {
                erreur = true;
                ret = new ParserConfigurationException();
                Logger.getLogger(AttributionBadge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                erreur = true;
                ret = new MalformedURLException();
                Logger.getLogger(AttributionBadge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                erreur = true;
                ret = new SAXException();
                Logger.getLogger(AttributionBadge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                erreur = true;
                ret = new IOException();
                Logger.getLogger(AttributionBadge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur = true;
                Logger.getLogger(AttributionBadge.class.getName()).log(Level.SEVERE, null, ex);
            }

            return ret;
        }
    }

    private class RESTBorneAccesGetAll extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Récupération des données ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (!erreur) {
                List<BorneAcces> borneAccees = (List<BorneAcces>) result;
                borneAcceses = borneAccees;
                String[] listeStrings = new String[borneAcceses.size()];
                for (int i = 0; i < borneAcceses.size(); i++) {
                    listeStrings[i] = borneAcceses.get(i).toString();
                }
                list.setAdapter(new ArrayAdapter<String>(activityContext, android.R.layout.simple_list_item_1, listeStrings));
                textViewPage.setText("Page " + getPage() + "/" + getNbPages());

            } else if (result instanceof ParserConfigurationException) {
                throwParserConfigurationException();
            } else if (result instanceof IOException) {
                throwIOException();
            } else if (result instanceof SAXException) {
                throwIOException();
            } else if (result instanceof MalformedURLException) {
                throwMalformedURLException();
            } else {
                throwException();
            }
        }

        @Override
        protected Object doInBackground(Object... params) {
            Object ret = null;
            try {
                ret = borneAccesSrv.getAll(activityContext, index, nbLinge);
            } catch (ParserConfigurationException ex) {
                erreur = true;
                ret = new ParserConfigurationException();
                Logger.getLogger(AttributionBadge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                erreur = true;
                ret = new MalformedURLException();
                Logger.getLogger(AttributionBadge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                erreur = true;
                ret = new SAXException();
                Logger.getLogger(AttributionBadge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                erreur = true;
                ret = new IOException();
                Logger.getLogger(AttributionBadge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur = true;
                Logger.getLogger(AttributionBadge.class.getName()).log(Level.SEVERE, null, ex);
            }

            return ret;
        }
    }

    private class RESTAttributionSecteurCameraGetBySecteur extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Récupération des données ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (!erreur) {
                AttributionSecteurCamera attributionSecteurCameras = (AttributionSecteurCamera) result;
                List<Camera> cameras = attributionSecteurCameras.getCameras();

                String[] listeStrings = new String[cameras.size()];
                for (int i = 0; i < cameras.size(); i++) {
                    listeStrings[i] = cameras.get(i).toString();
                }
                listViewSecteur.setAdapter(new ArrayAdapter<String>(activityContext, android.R.layout.simple_list_item_1, listeStrings));
                textViewPage.setText("Page " + getPage() + "/" + getNbPages());

            } else if (result instanceof ParserConfigurationException) {
                throwParserConfigurationException();
            } else if (result instanceof IOException) {
                throwIOException();
            } else if (result instanceof SAXException) {
                throwSAXException();
            } else if (result instanceof MalformedURLException) {
                throwMalformedURLException();
            } else {
                throwException();
            }
        }

        @Override
        protected Object doInBackground(Object... params) {

            Object ret = null;
            try {
                ret = attributionSecteurCameraSrv.getBySecteur(activityContext, secteurSelected);
            } catch (ParserConfigurationException ex) {
                erreur = true;
                ret = new ParserConfigurationException();
                Logger.getLogger(AttributionBadge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                erreur = true;
                ret = new MalformedURLException();
                Logger.getLogger(AttributionBadge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                erreur = true;
                ret = new SAXException();
                Logger.getLogger(AttributionBadge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                erreur = true;
                ret = new IOException();
                Logger.getLogger(AttributionBadge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur = true;
                Logger.getLogger(AttributionBadge.class.getName()).log(Level.SEVERE, null, ex);
            }

            return ret;
        }
    }
}
