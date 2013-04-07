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
import metier.AttributionUtilisateurBadgeService;
import metier.BadgeService;
import metier.MetierFactory;
import metier.UtilisateurService;
import metier.entitys.Badge;
import metier.entitys.Utilisateur;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public class AttributionBadge extends TemplateActivity {

    private Button buttonUtilisateur;
    private Button buttonBadge;
    private Button buttonAttribuer;
    private ListView list;
    private Button precedent;
    private Button suivant;
    private AttributionUtilisateurBadgeService attributionUtilisateurBadgeSrv = MetierFactory.getAttributionUtilisateurBadgeService();
    private BadgeService badgeSrv = MetierFactory.getBadgeSrv();
    private UtilisateurService utilisateurSrv = MetierFactory.getUtilisateurSrv();
    private int index;
    private int nbLinge = 8;
    private TextView textViewPage;
    private List u;
    private int pos;
    private int countBadge = 0;
    private int countUtilisateur = 0;
    private Badge badgeSelected;
    private Utilisateur utilisateurSelected;
    private AlertDialog alertDialogInCurrent;
    private boolean isUtilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attribution_badge);
        if (this.isTablette7()) {
            this.nbLinge = 14;
        }
        new AttributionBadge.RESTUtilisateurCount().execute();
        new AttributionBadge.RESTBadgeCount().execute();
        this.setThisActivityOn();
    }

    @Override
    public void initGraphicalObjects() {
        this.buttonBadge = (Button) findViewById(R.id.spinnerAttributionBadgeBadge);
        this.buttonUtilisateur = (Button) findViewById(R.id.spinnerAttributionBadgeUtilisateur);
        this.buttonAttribuer = (Button) findViewById(R.id.buttonAttributionBadgeValider);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonBadge.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                index=0;
                isUtilisateur = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(activityContext, AlertDialog.THEME_HOLO_LIGHT);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_custom_titile, null);
                TextView title = (TextView) view.findViewById(R.id.myTitle);
                title.setText("Sélectonner un badge.");
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
                        builder.setTitle("badge séléctionné.");
                        List<Badge> badges = (List<Badge>) u;
                        builder.setMessage("Voulez-vous séléctionner le badge n°" + badges.get(pos).getNumero() + " ?");
                        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Badge badgeCliked = (Badge) u.get(pos);
                                badgeSelected = badgeCliked;
                                buttonBadge.setText(badgeSelected.toString());
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
                View content = View.inflate(activityContext, R.layout.activity_liste_utilisateur, null);
                list = (ListView) content.findViewById(R.id.listeUtilisateurs);
                precedent = (Button) content.findViewById(R.id.boutonUtilisateurPrecedent);
                suivant = (Button) content.findViewById(R.id.boutonUtilisateurSuivant);
                textViewPage = (TextView) content.findViewById(R.id.listeUtilisateurAffichagePage);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
                        pos = position;
                        AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
                        builder.setTitle("utilisateur séléctionné.");
                        List<Utilisateur> badges = (List<Utilisateur>) u;
                        builder.setMessage("Voulez-vous séléctionner le badge n°" + badges.get(position).toString() + " ?");
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
        this.buttonAttribuer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((utilisateurSelected == null) && (badgeSelected == null)) {
                    Toast.makeText(activityContext, "Le badge est l'utilisateur ne sont pas séléctionner", Toast.LENGTH_LONG).show();
                } else if (badgeSelected == null) {
                    Toast.makeText(activityContext, "Le badge n'est pas séléctionner", Toast.LENGTH_LONG).show();
                } else if (utilisateurSelected == null) {
                    Toast.makeText(activityContext, "Le badge n'est pas séléctionner", Toast.LENGTH_LONG).show();
                } else if ((utilisateurSelected != null) && (badgeSelected != null)) {
                    new AttributionBadge.RESTAttributionUtilisateurBadgeAttribuer().execute();
                }
            }
        });
    }

    @Override
    public void addInitialValueForGraphicalObjects() {
        this.buttonBadge.setText("Selectionner un badge");
        this.buttonUtilisateur.setText("Selectionner un utilisateur");
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
            new AttributionBadge.RESTAttributionUtilisateurGetUtilisateurNotAssign().execute();
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
            new AttributionBadge.RESTAttributionUtilisateurGetBadgesNotAssign().execute();
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
            nbPages = this.countBadge / this.nbLinge;
        }
        return nbPages + 1;
    }

    private class RESTAttributionUtilisateurGetUtilisateurNotAssign extends AsyncTask<Object, Void, Object> {

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
                ret = attributionUtilisateurBadgeSrv.getUtilisateurNotAssign(activityContext, index, nbLinge);
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

    private class RESTAttributionUtilisateurGetBadgesNotAssign extends AsyncTask<Object, Void, Object> {

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
                List<Badge> badges = (List<Badge>) result;
                u = badges;
                String[] listeStrings = new String[badges.size()];
                for (int i = 0; i < badges.size(); i++) {
                    listeStrings[i] = badges.get(i).toString();
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
                ret = attributionUtilisateurBadgeSrv.getBadgesNotAssign(activityContext, index, nbLinge);
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

    private class RESTAttributionUtilisateurBadgeAttribuer extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Envoie des données ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (!erreur) {
                Toast.makeText(activityContext, "Badge bien attribuer.", Toast.LENGTH_LONG).show();
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
                attributionUtilisateurBadgeSrv.attribuer(activityContext, utilisateurSelected, badgeSelected);
            } catch (MalformedURLException ex) {
                erreur = true;
                ret = new MalformedURLException();
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

    private class RESTBadgeCount extends AsyncTask<Object, Void, Object> {

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
                countBadge = (Integer) result;
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
                ret = badgeSrv.count(activityContext);
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

    private class RESTUtilisateurCount extends AsyncTask<Object, Void, Object> {

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
                countUtilisateur = (Integer) result;
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
                ret = utilisateurSrv.count(activityContext);
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
