package fr.securisation_site_gvi.client.fragments;

import fr.securisation_site_gvi.client.*;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import metier.EvenementService;
import metier.MetierFactory;
import metier.entitys.Acces;
import metier.entitys.Evenement;
import metier.entitys.Intrusion;
import metier.entitys.Photo;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public class HistoriqueFragment extends TemplateFragment {

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
    private Evenement evenementReturn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_historique, container, false);
        getActivity().setTitle("Historique");
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState); 
         this.activityContext = getActivity();
        this.setThisActivityOn();
        this.index = 0;
        if (this.isTablette7()) {
            this.nbLinge = 15;
        }
        new RESTEvenementCount().execute();
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
        new RESTEvenementGetAllByRange().execute();
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
        int nbPages = this.count / this.nbLinge;
        return nbPages + 1;
    }

    /**
     *
     */
    @Override
    public void initGraphicalObjects() {
        this.listEvenements = (ListView) getActivity().findViewById(R.id.HistoriquelisteEvenements);
        this.precedent = (Button) getActivity().findViewById(R.id.HistoriqueboutonPrecedent);
        this.suivant = (Button) getActivity().findViewById(R.id.HistoriqueboutonSuivant);
        this.textViewPage = (TextView) getActivity().findViewById(R.id.HistoriquetextPage);
    }

    /**
     *
     */
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
                        new HistoriqueFragment.RESTEvenementGetById().execute(idCliked);
                        
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

    private class RESTEvenementGetAllByRange extends AsyncTask<Object, Void, Object> {

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
                List<Evenement> evenements = (List<Evenement>) result;
                u = evenements;
                String[] listeStrings = new String[evenements.size()];
                for (int i = 0; i < evenements.size(); i++) {
                    listeStrings[i] = evenements.get(i).toString();
                }
                listEvenements.setAdapter(new ArrayAdapter<String>(activityContext, android.R.layout.simple_list_item_1, listeStrings));
                textViewPage.setText("Page " + getPage() + "/" + getNbPages());
            } else if (result instanceof ParserConfigurationException) {
                throwParserConfigurationException();
            }else if (result instanceof SAXException) {
                throwSAXException();
            }else if (result instanceof IOException) {
                throwIOException();
            }
        }

        @Override
        protected Object doInBackground(Object... params) {
            Object ret = null;
            try {
                ret = evenementSrv.getAll(activityContext, index, nbLinge);
            } catch (ParserConfigurationException ex) {
                erreur =true;
                ret= new ParserConfigurationException();
                Logger.getLogger(HistoriqueFragment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                erreur =true;
                ret= new SAXException();
                Logger.getLogger(HistoriqueFragment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                erreur =true;
                ret= new IOException();
                Logger.getLogger(HistoriqueFragment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur =true;
                Logger.getLogger(HistoriqueFragment.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ret;
        }
    }
    private class RESTEvenementCount extends AsyncTask<Object, Void, Object> {

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
                count = (Integer) result;
                remplirListView();
            } else if (result instanceof IOException) {
                throwIOException();
            }else{
                throwException();
            }
        }

        @Override
        protected Object doInBackground(Object... params) {
            Object ret = null;
            try {
                ret = evenementSrv.count(activityContext);
            } catch (IOException ex) {
                erreur =true;
                ret=new IOException();
                Logger.getLogger(HistoriqueFragment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur =true;
                Logger.getLogger(HistoriqueFragment.class.getName()).log(Level.SEVERE, null, ex);
            } return ret;
        }
    }
    
    
    private class RESTEvenementGetById extends AsyncTask<Object, Void, Object> {

        private ProgressDialog progressDialog;
        private boolean erreur = false;
        private String POWER_SERVICE;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(activityContext, "", "Récupération des données ...", true);
        }

        @Override
        protected void onPostExecute(Object result) {
            this.progressDialog.cancel();
            if (!erreur) {
                Evenement evenement = (Evenement) result;
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
               
            } else if (result instanceof IOException) {
                throwIOException();
            }
             else if (result instanceof MalformedURLException) {
                throwMalformedURLException();
            }
              else if (result instanceof SAXException) {
                throwSAXException();
            }
               else if (result instanceof ParseException) {
                throwParseException();
            } else if (result instanceof ParserConfigurationException) {
                throwParserConfigurationException();
            }else{
                throwException();
            }
        }

        @Override
        protected Object doInBackground(Object... params) {
            Object ret = null;
            try {
                ret = evenementSrv.getById(activityContext,(Long) params[0]);
            } catch (MalformedURLException ex) {
                erreur =true;
                ret=new MalformedURLException();
                Logger.getLogger(HistoriqueFragment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                erreur =true;
                ret=new IOException();
                Logger.getLogger(HistoriqueFragment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                erreur =true;
                ret=new SAXException();
                Logger.getLogger(HistoriqueFragment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                erreur =true;
                ret=new ParseException(POWER_SERVICE, count);
                Logger.getLogger(HistoriqueFragment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                erreur =true;
                ret=new ParserConfigurationException();
                Logger.getLogger(HistoriqueFragment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                erreur =true;
                Logger.getLogger(HistoriqueFragment.class.getName()).log(Level.SEVERE, null, ex);
            }return ret;
        }
    }
}
