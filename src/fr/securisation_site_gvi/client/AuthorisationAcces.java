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
import metier.MetierFactory;
import metier.SecteurService;
import metier.UtilisateurService;
import metier.entitys.Badge;
import metier.entitys.Secteur;
import metier.entitys.Utilisateur;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public class AuthorisationAcces extends TemplateActivity{
    private Button buttonUtilisateur,buttonSecteur,buttonCree,precedent,suivant;
    private ListView list;
    private List<Secteur> secteurs;
    private int index,nbLinge = 8,pos,countSecteur = 0,countUtilisateur = 0;
    private TextView textViewPage;
    private List u;
    private Secteur secteurSelected;
    private Utilisateur utilisateurSelected;
    private AlertDialog alertDialogInCurrent;
    private boolean isUtilisateur;
     @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorisation_acces);
        this.setThisActivityOn();
    }
    @Override
     public void initGraphicalObjects() {
        this.buttonUtilisateur=(Button) findViewById(R.id.AuthorisationAccesButtonUtilisateur);
        this.buttonSecteur = (Button) findViewById(R.id.AuthorisationAccesButtonSecteur);
        this.buttonCree = (Button) findViewById(R.id.AuthorisationAccesButtonCree);
    }
    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonSecteur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                index=0;
                isUtilisateur = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(activityContext, AlertDialog.THEME_HOLO_LIGHT);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_custom_titile, null);
                TextView title = (TextView) view.findViewById(R.id.myTitle);
                title.setText("Liste des secteurs.");
                builder.setCustomTitle(view);
                builder.setView(view);
                builder.setCancelable(true);
                View content = View.inflate(activityContext, R.layout.activity_listebadges, null);
                list = (ListView) content.findViewById(R.id.listViewBadges);
                precedent = (Button) content.findViewById(R.id.buttonBadgesPrecedent);
                suivant = (Button) content.findViewById(R.id.buttonBadgesSuivant);
                textViewPage = (TextView) content.findViewById(R.id.textviewbadgePage);
//                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view,
//                            int position, long id) {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
//                        builder.setTitle("Secteur séléctionné.");
//                        builder.setMessage("Voulez-vous séléctionner le secteur " + secteurs.get(pos).toString()+ " ?");
//                        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                Badge badgeCliked = (Badge) u.get(pos);
//                                badgeSelected = badgeCliked;
//                                buttonBadge.setText(badgeSelected.toString());
//                                alertDialogInCurrent.cancel();
//                                dialog.cancel();
//
//                            }
//                        });
//                        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//                        builder.show();
//                    }
//                });
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
        this.buttonUtilisateur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                index=0;
                isUtilisateur = true;
                AlertDialog.Builder builder = new AlertDialog.Builder(activityContext, AlertDialog.THEME_HOLO_LIGHT);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_custom_titile, null);
                TextView title = (TextView) view.findViewById(R.id.myTitle);
                title.setText("Sélectonner un utilisateur.");
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
                    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
                        pos = position;
                        AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
                        builder.setTitle("utilisateur séléctionné.");
                        List<Utilisateur> utilisateurs = (List<Utilisateur>) u;
                        builder.setMessage("Voulez-vous séléctionner le l'utilisateur " + utilisateurs.get(position).toString() + " ?");
                        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Utilisateur utilisateurCliked = (Utilisateur) u.get(pos);
                                utilisateurSelected = utilisateurCliked;
                                buttonUtilisateur.setText(utilisateurSelected.toString());
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
        if (isUtilisateur) {
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
            new AuthorisationAcces.RESTUtilisateurGetAll().execute();
        } else {
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
            new AuthorisationAcces.RESTSecteurGetAll().execute();
        }

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
        if (isUtilisateur) {
            nbPages = this.countUtilisateur / this.nbLinge;
        } else {
            nbPages = this.countSecteur / this.nbLinge;
        }
        return nbPages + 1;
    }

    @Override 
    public void addInitialValueForGraphicalObjects() {
        if (this.isTablette7()) {
            this.nbLinge = 14;
        }
        this.index = 0;
        this.buttonUtilisateur.setText("Séléctionnez l'utilisateur");
        this.buttonSecteur.setText("Gérer les secteurs accesible");
    }
    private class RESTUtilisateurGetAll extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;
        private UtilisateurService utilisateurSrv = MetierFactory.getUtilisateurSrv();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Récupération des données ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (!erreur) {
                List<Utilisateur> utilisateurs = (List<Utilisateur>) result;
                u = utilisateurs;
                String[] listeStrings = new String[utilisateurs.size()];
                for (int i = 0; i < utilisateurs.size(); i++) {
                    listeStrings[i] = utilisateurs.get(i).toString();
                }
                list.setAdapter(new ArrayAdapter<String>(activityContext, android.R.layout.simple_list_item_1, listeStrings));
                textViewPage.setText("Page " + getPage() + "/" + getNbPages());

            } else if (result instanceof ParserConfigurationException) {
                throwParserConfigurationException();
            } else if (result instanceof IOException) {
                throwIOException();
            } else if (result instanceof SAXException) {
                throwIOException();
            } else {
                throwException();
            }
        }

        @Override
        protected Object doInBackground(Object... params) {
            Object ret = null;
            try {
                ret = utilisateurSrv.getAll(index, nbLinge,activityContext);
            } catch (ParserConfigurationException ex) {
                erreur = true;
                ret = new ParserConfigurationException();
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
    private class RESTSecteurGetAll extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;
        private SecteurService secteurSrv = MetierFactory.getSecteurSrv();
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
}
