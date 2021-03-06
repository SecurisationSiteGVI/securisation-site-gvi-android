/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.client.fragments;

import fr.securisation_site_gvi.client.*;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import metier.BorneAccesService;
import metier.MetierFactory;
import metier.PositionService;
import metier.entitys.BorneAcces;
import metier.entitys.Position;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public class AjoutBorneAccesFragment extends TemplateFragment {

    private Button buttonCree;
    private Button buttonPosition;
    private EditText editTextNom;
    private ToggleButton toggleButtonEntre;
    private Position positionSelected;
    private int index;
    private ListView list;
    private List<Position> u;
    private Button precedent;
    private Button suivant;
    private int nbLinge = 8;
    private TextView textViewPage;
    private AlertDialog alertDialogInCurrent;
    private int pos;
    private int count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ajouter_borneacces, container, false);
        getActivity().setTitle("Ajouter borne d'accès");
        this.setThisActivityOn();
        return rootView;
    }

    /**
     *
     */
    @Override
    public void initGraphicalObjects() {
        this.editTextNom = (EditText) getActivity().findViewById(R.id.AjoutBorneAccesEditTextNom);
        this.toggleButtonEntre = (ToggleButton) getActivity().findViewById(R.id.AjoutBorneAccesToggleButtonEntre);
        this.buttonCree = (Button) getActivity().findViewById(R.id.AjoutBorneAccesbuttonCree);
        this.buttonPosition = (Button) getActivity().findViewById(R.id.AjoutBorneAccesButtonPosition);
    }

    /**
     *
     */
    @Override
    public void addActionListnerForAllGraphicalObjects() {
        this.buttonCree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (positionSelected != null) {
                    BorneAcces borneAcces = new BorneAcces();
                    if (toggleButtonEntre.isSelected()) {
                        borneAcces.setEntrer(Boolean.TRUE);
                    } else {
                        borneAcces.setEntrer(Boolean.FALSE);
                    }
                    borneAcces.setNom(editTextNom.getText().toString());
                    borneAcces.setPosition(positionSelected);
                    new AjoutBorneAccesFragment.RESTCameraAjout().execute(borneAcces);
                } else {
                    Toast.makeText(activityContext, "Votre séléction n'est pas correct.", Toast.LENGTH_LONG).show();
                }
            }
        });
        this.buttonPosition.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                index = 0;
                AlertDialog.Builder builder = new AlertDialog.Builder(activityContext, AlertDialog.THEME_HOLO_LIGHT);
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_custom_titile, null);
                TextView title = (TextView) view.findViewById(R.id.myTitle);
                title.setText("Sélectonner une position.");
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
                        builder.setTitle("position séléctionné.");
                        List<Position> badges = (List<Position>) u;
                        builder.setMessage("Voulez-vous séléctionner la position : " + badges.get(pos).toString() + " ?");
                        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                positionSelected = (Position) u.get(pos);
                                buttonPosition.setText(positionSelected.toString());
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

    /**
     *
     */
    @Override
    public void addInitialValueForGraphicalObjects() {
        this.buttonPosition.setText("Faites votre séléction");
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
        new AjoutBorneAccesFragment.RESTPositionGetAll().execute();
    }

    /**
     *
     */
    public void pagePrécédente() {
        if (this.index <= nbLinge - 1) {
            Toast.makeText(activityContext, "Vous éte déjà sur la premiere page.", Toast.LENGTH_SHORT).show();
        } else {
            this.index = this.index - nbLinge;
            this.remplirListView();
        }
    }

    /**
     *
     */
    public void pageSuivante() {
        if (this.getPage() >= this.getNbPages()) {
            Toast.makeText(this.activityContext, "Vous étes déjà sur la derniére page", Toast.LENGTH_LONG).show();
            this.suivant.setEnabled(false);
        }
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
        int nbPages = 0;
        nbPages = this.count / this.nbLinge;
        return nbPages + 1;
    }

    private class RESTPositionGetAll extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;
        private PositionService positionSrv = MetierFactory.getPositionService();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Récupération des données ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (!erreur) {
                List<Position> positions = (List<Position>) result;
                u = positions;
                String[] listeStrings = new String[positions.size()];
                for (int i = 0; i < positions.size(); i++) {
                    listeStrings[i] = positions.get(i).toString();
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
                ret = this.positionSrv.getAll(activityContext, index, nbLinge);
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

    private class RESTCameraAjout extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;
        private BorneAccesService borneAccesSrv = MetierFactory.getBorneAccesService();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Envoie des données ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (!erreur) {
                Toast.makeText(activityContext, "Borne d'acces bien ajouté.", Toast.LENGTH_LONG).show();
            } else if (result instanceof IOException) {
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
                this.borneAccesSrv.add(activityContext, (BorneAcces) params[0]);
            } catch (MalformedURLException ex) {
                erreur = true;
                ret = new MalformedURLException();
                Logger.getLogger(AjouterBadge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                erreur = true;
                ret = new IOException();
                Logger.getLogger(AjouterBadge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur = true;
                Logger.getLogger(AjouterBadge.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ret;
        }
    }
}
