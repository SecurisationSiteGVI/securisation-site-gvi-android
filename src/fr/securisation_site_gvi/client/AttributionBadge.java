package fr.securisation_site_gvi.client;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.AttributionUtilisateurBadgeService;
import metier.BadgeService;
import metier.MetierFactory;
import metier.UtilisateurService;
import metier.entitys.Badge;
import metier.entitys.Utilisateur;

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
    private int countUtilisateur=0;
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
        try {
            this.countUtilisateur = this.utilisateurSrv.count(this.activityContext);
        } catch (Exception ex) {
            Logger.getLogger(ListeUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.countBadge = this.badgeSrv.count(this.activityContext);
        } catch (Exception ex) {
            Logger.getLogger(ListeUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setThisActivityOn();
    }

    @Override
    public void initGraphicalObjects() {
        this.buttonBadge = (Button) findViewById(R.id.spinnerAttributionBadgeBadge);
        this.buttonUtilisateur = (Button) findViewById(R.id.spinnerAttributionBadgeUtilisateur);
        this.buttonAttribuer=(Button) findViewById(R.id.buttonAttributionBadgeValider);
    }

    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonBadge.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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
                        pos=position;
                        AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
                        builder.setTitle("utilisateur séléctionné.");
                        List<Utilisateur> badges = (List<Utilisateur>) u;
                        builder.setMessage("Voulez-vous séléctionner le badge n°" + badges.get(position).toString()+ " ?");
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
                if((utilisateurSelected==null)&& (badgeSelected==null)){
                    Toast.makeText(activityContext, "Le badge est l'utilisateur ne sont pas séléctionner", Toast.LENGTH_LONG).show();
                }else if( badgeSelected==null){
                    Toast.makeText(activityContext, "Le badge n'est pas séléctionner", Toast.LENGTH_LONG).show();
                }else if( utilisateurSelected==null){
                    Toast.makeText(activityContext, "Le badge n'est pas séléctionner", Toast.LENGTH_LONG).show();
                }else if( (utilisateurSelected!=null)&& (badgeSelected!=null)){
                    attributionUtilisateurBadgeSrv.attribuer(activityContext, utilisateurSelected, badgeSelected);
                    Toast.makeText(activityContext, "Badge bien attribuer.", Toast.LENGTH_LONG).show();
                    
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
            List<Utilisateur> utilisateurs = null;
            try {
                utilisateurs = this.attributionUtilisateurBadgeSrv.getUtilisateurNotAssign(this.activityContext, this.index, this.nbLinge);
                this.u = utilisateurs;
            } catch (Exception ex) {
                Logger.getLogger(ListeUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] listeStrings = new String[utilisateurs.size()];
            for (int i = 0; i < utilisateurs.size(); i++) {
                listeStrings[i] = utilisateurs.get(i).toString();
            }
            list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeStrings));
            this.textViewPage.setText("Page " + this.getPage() + "/" + this.getNbPages());
        }else{
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
                badges = this.attributionUtilisateurBadgeSrv.getBadgesNotAssign(this.activityContext,this.index, this.nbLinge);
                this.u = badges;
            } catch (Exception ex) {
                Logger.getLogger(ListeUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] listeStrings = new String[badges.size()];
            for (int i = 0; i < badges.size(); i++) {
                listeStrings[i] = badges.get(i).toString();
            }
            list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeStrings));
            this.textViewPage.setText("Page " + this.getPage() + "/" + this.getNbPages());
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
        int nbPages=0;
        if(isUtilisateur){
            nbPages = this.countUtilisateur/this.nbLinge;
        }else{
            nbPages = this.countBadge / this.nbLinge;
        }
        return nbPages + 1;
    }
}
