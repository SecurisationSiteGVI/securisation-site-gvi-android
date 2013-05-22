/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import metier.entitys.Technicien;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import metier.entitys.Utilisateur;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;

/**
 *
 * @author damien
 */
public class UtilisateurServiceWebImpl implements UtilisateurServiceWeb {

    private static String getTagValue(String sTag, Element eElement) {
        String ret = null;

        try {
            NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
            Node nValue = (Node) nlList.item(0);
            ret = nValue.getNodeValue();
        } catch (NullPointerException e) {
            // System.out.println("Null pour l'item : " + sTag);
        }
        return ret;
    }

    @Override
    public List<Utilisateur> getAll(Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTGetAll().execute(context);
        List<Utilisateur> retour = (List<Utilisateur>) ret.get();
        return retour;
    }

    @Override
    public boolean add(Utilisateur utilisateur, Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTAdd().execute(utilisateur, context);
        Boolean retour = (Boolean) ret.get();
        return retour;
    }

    @Override
    public boolean remove(Utilisateur utilisateur, Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTRemove().execute(utilisateur, context);
        Boolean retour = (Boolean) ret.get();
        return retour;
    }

    @Override
    public boolean update(Utilisateur utilisateur, Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTUpdate().execute(utilisateur, context);
        Boolean retour = (Boolean) ret.get();
        return retour;
    }

    @Override
    public boolean loginIsUse(String loginn, Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTLoginIsUse().execute(loginn, context);
        Boolean retour = (Boolean) ret.get();
        return retour;
    }

    @Override
    public Technicien verificationConnexion(Technicien utilisateur, Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTVerifConnexionBackGround(context).execute(utilisateur, context);
        Technicien technicien = (Technicien) ret.get();
        return technicien;
    }

    @Override
    public List<Utilisateur> getAll(int from, int nbResut, Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTGetAllByRange().execute(from, nbResut, context);
        List<Utilisateur> utilisateurs = (List<Utilisateur>) ret.get();
        return utilisateurs;

    }

    public Utilisateur getById(Long id, Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTGetById().execute(id, context);
        List<Utilisateur> utilisateurs = (List<Utilisateur>) ret.get();
        return utilisateurs.get(0);
    }

    public int count(Context c) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTCount().execute(c);
        Integer count = (Integer) ret.get();
        return count;
    }

    public boolean addTechnicien(Technicien technicien,Context context) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTAddTechniecien().execute(technicien, context);
        Boolean retour = (Boolean) ret.get();
        return retour;
    }

    private class RESTGetById extends AsyncTask<Object, Void, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            Context c = (Context) params[1];
            RessourcesServiceDataIn r = PhysiqueDataInFactory.getRessourceSrv(c);
            Ressource ressource = null;
            try {
                ressource = r.getRessource();
            } catch (Exception ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
            try {
                Long id = (Long) params[0];
                InputStream fluxLecture = null;
                URL url = new URL(ressource.getPathToAccesWebService() + "utilisateur/" + id);
                fluxLecture = url.openStream();
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fluxLecture);
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("utilisateur");
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Utilisateur utilisateur = new Utilisateur();
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        utilisateur.setEmail(getTagValue("email", eElement));
                        utilisateur.setId(Long.parseLong(getTagValue("id", eElement)));
                        utilisateur.setVille(getTagValue("ville", eElement));
                        utilisateur.setNom(getTagValue("nom", eElement));
                        utilisateur.setPrenom(getTagValue("prenom", eElement));
                        if (getTagValue("dateDeNaissance", eElement) != null) {
                            String dateStr = getTagValue("dateDeNaissance", eElement);
                            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            Date d = new Date();
                            try {
                                d = dateFormat.parse(dateStr);
                            } catch (ParseException ex) {
                                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            utilisateur.setDateDeNaissance(d);

                        }

                        utilisateur.setCodePostale(Integer.parseInt(getTagValue("codePostale", eElement)));
                        utilisateur.setAdresse(getTagValue("adresse", eElement));
                        utilisateur.setTelephonePortable(getTagValue("telephonePortable", eElement));
                        utilisateur.setTelephoneFixe(getTagValue("telephoneFixe", eElement));
                        utilisateur.setHomme(Boolean.parseBoolean(getTagValue("homme", eElement)));
                        utilisateurs.add(utilisateur);
                    }
                }

            } catch (SAXException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return utilisateurs;
        }
    }

    private class RESTGetAll extends AsyncTask<Object, Void, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            Context c = (Context) params[0];
            RessourcesServiceDataIn r = PhysiqueDataInFactory.getRessourceSrv(c);
            Ressource ressource = null;
            try {
                ressource = r.getRessource();
            } catch (Exception ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
            try {
                InputStream fluxLecture = null;
                URL url = new URL(ressource.getPathToAccesWebService() + "utilisateur");
                fluxLecture = url.openStream();
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fluxLecture);
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("utilisateur");
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Utilisateur utilisateur = new Utilisateur();
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        utilisateur.setEmail(getTagValue("email", eElement));
                        utilisateur.setId(Long.parseLong(getTagValue("id", eElement)));
                        utilisateur.setVille(getTagValue("ville", eElement));
                        utilisateur.setNom(getTagValue("nom", eElement));
                        utilisateur.setPrenom(getTagValue("prenom", eElement));
                        utilisateur.setCodePostale(Integer.parseInt(getTagValue("codePostale", eElement)));
                        utilisateur.setAdresse(getTagValue("adresse", eElement));
                        utilisateur.setTelephonePortable(getTagValue("telephonePortable", eElement));
                        utilisateur.setTelephoneFixe(getTagValue("telephoneFixe", eElement));
                        utilisateur.setHomme(Boolean.parseBoolean(getTagValue("homme", eElement)));
                        if (getTagValue("dateDeNaissance", eElement) != null) {
                            String dateStr = getTagValue("dateDeNaissance", eElement);
                            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            Date d = new Date();
                            try {
                                d = dateFormat.parse(dateStr);
                            } catch (ParseException ex) {
                                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            utilisateur.setDateDeNaissance(d);

                        }
                        utilisateurs.add(utilisateur);
                    }
                }
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return utilisateurs;
        }
    }

    private class RESTAdd extends AsyncTask<Object, Void, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            Context c = (Context) params[1];
            RessourcesServiceDataIn r = PhysiqueDataInFactory.getRessourceSrv(c);
            Ressource ressource = null;
            try {
                ressource = r.getRessource();
            } catch (Exception ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            Boolean retour = true;
            try {
                Utilisateur utilisateur = (Utilisateur) params[0];
                String prenom = utilisateur.getPrenom();
                String nom = utilisateur.getNom();
                String email = utilisateur.getEmail();
                String telephoneFixe = utilisateur.getTelephoneFixe();
                String telephonePortable = utilisateur.getTelephonePortable();
                String ville = utilisateur.getVille();
                int codePostale = utilisateur.getCodePostale();
                String adresse = utilisateur.getAdresse();
                boolean homme = utilisateur.isHomme();
                Date dateDeNaissance = utilisateur.getDateDeNaissance();
                URL url = new URL(ressource.getPathToAccesWebService() + "utilisateur");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                String input = "{\"id\":0,\"prenom\":\"" + prenom + "\",\"nom\":\"" + nom + "\",\"email\":\"" + email + "\",\"telephoneFixe\":\"" + telephoneFixe + "\",\"telephonePortable\":\"" + telephonePortable + "\",\"ville\":\"" + ville + "\",\"codePostale\":" + codePostale + ",\"adresse\":\"" + adresse + "\",\"homme\":\"" + homme + "\",\"dateDeNaissance\":\"" + dateDeNaissance + "\"}";
                OutputStream os = conn.getOutputStream();
                System.out.println(input);
                os.write(input.getBytes());
                os.flush();
                if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                    if (conn.getResponseCode() != 204) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + conn.getResponseCode());
                    } else {
                        System.out.println("Requete envoyé mais pas de réponse du serveur.");
                    }
                }
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }
                conn.disconnect();

            } catch (IOException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return retour;
        }
    }

    private class RESTRemove extends AsyncTask<Object, Void, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            Boolean retour = true;
            try {
                Utilisateur utilisateur = (Utilisateur) params[0];
                Context c = (Context) params[1];
                RessourcesServiceDataIn r = PhysiqueDataInFactory.getRessourceSrv(c);
                Ressource ressource = null;
                try {
                    ressource = r.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                URL url = new URL(ressource.getPathToAccesWebService() + "utilisateur/" + utilisateur.getId());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.addRequestProperty("delete", "true");
                conn.setRequestMethod("PUT");
                conn.setDoOutput(true);


                conn.setRequestProperty("Content-Type", "application/json");
                String input = "{\"id\":" + utilisateur.getId() + "}";
                if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                    if (conn.getResponseCode() != 204) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + conn.getResponseCode());
                    } else {
                        System.out.println("Requete envoyé mais pas de réponse du serveur.");
                    }
                }
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                String output;
                //System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }
                conn.disconnect();

            } catch (MalformedURLException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return retour;
        }
    }

    private class RESTUpdate extends AsyncTask<Object, Void, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            Boolean retour = true;
            try {
                Context c = (Context) params[1];
                RessourcesServiceDataIn r = PhysiqueDataInFactory.getRessourceSrv(c);
                Ressource ressource = null;
                try {
                    ressource = r.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                }final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Utilisateur utilisateur = (Utilisateur) params[0];
                Long id = utilisateur.getId();
                String prenom = utilisateur.getPrenom();
                String nom = utilisateur.getNom();
                String email = utilisateur.getEmail();
                String telephoneFixe = utilisateur.getTelephoneFixe();
                String telephonePortable = utilisateur.getTelephonePortable();
                String ville = utilisateur.getVille();
                int codePostale = utilisateur.getCodePostale();
                String adresse = utilisateur.getAdresse();
                boolean homme = utilisateur.isHomme();
                Date dateDeNaissance = utilisateur.getDateDeNaissance();
                String formattedDate = null;
                if (dateDeNaissance != null) {
                    formattedDate = dateFormat.format(dateDeNaissance);
                } else {
                    Date d = new Date(0l);
                    formattedDate = dateFormat.format(d);

                }

                String surl = ressource.getPathToAccesWebService() + "utilisateur";
                URL url = new URL(surl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("PUT");
                conn.setRequestProperty("Content-Type", "application/json");
                String input = "{\"id\":" + id + ",\"prenom\":\"" + prenom + "\",\"nom\":\"" + nom + "\",\"email\":\"" + email + "\",\"telephoneFixe\":\"" + telephoneFixe + "\",\"telephonePortable\":\"" + telephonePortable + "\",\"ville\":\"" + ville + "\",\"codePostale\":" + codePostale + ",\"adresse\":\"" + adresse + "\",\"homme\":\"" + homme + "\",\"dateDeNaissance\":\"" + formattedDate + "\"}";
                System.out.println(input);
                OutputStream os = conn.getOutputStream();
                os.write(input.getBytes());
                os.flush();
                if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                    if (conn.getResponseCode() != 204) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + conn.getResponseCode());
                    } else {
                        System.out.println("Requete envoyé mais pas de réponse du serveur.");
                    }
                }
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                String output;

                while ((output = br.readLine()) != null) {
                    //            System.out.println("Réponse du serveur ... \n");
                    System.out.println(output);
                }
                conn.disconnect();

            } catch (MalformedURLException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return retour;
        }
    }

    private class RESTLoginIsUse extends AsyncTask<Object, Void, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            BufferedReader br = null;
            Boolean ret = false;
            try {
                Context c = (Context) params[1];
                RessourcesServiceDataIn r = PhysiqueDataInFactory.getRessourceSrv(c);
                Ressource ressource = null;
                try {
                    ressource = r.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                Technicien utilisateur = new Technicien();
                Long id = utilisateur.getId();
                String prenom = utilisateur.getPrenom();
                String nom = utilisateur.getNom();
                String email = utilisateur.getEmail();
                String telephoneFixe = utilisateur.getTelephoneFixe();
                String telephonePortable = utilisateur.getTelephonePortable();
                String ville = utilisateur.getVille();
                int codePostale = utilisateur.getCodePostale();
                String adresse = utilisateur.getAdresse();
                boolean homme = utilisateur.isHomme();
                String login = (String) params[0];
                String password = utilisateur.getPassword();
                Date dateDeNaissance = utilisateur.getDateDeNaissance();
                URL url = new URL(ressource.getPathToAccesWebService() + "utilisateur/loginIsUse");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                String input = "{\"id\":" + id + ",\"prenom\":\"" + prenom + "\",\"nom\":\"" + nom + "\",\"email\":\"" + email + "\",\"telephoneFixe\":\"" + telephoneFixe + "\","
                        + "\"telephonePortable\":\"" + telephonePortable + "\",\"ville\":\"" + ville + "\",\"codePostale\":" + codePostale + ","
                        + "\"adresse\":\"" + adresse + "\",\"homme\":\"" + homme + "\",\"dateDeNaissance\":\"" + dateDeNaissance + "\",\"login\":\"" + login + "\",\"password\":\"" + password + "\"}";
                OutputStream os = conn.getOutputStream();
                os.write(input.getBytes());
                if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                    if (conn.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + conn.getResponseCode());
                    } else {
                        System.out.println("Requete envoyé mais pas de réponse du serveur.");
                    }
                }
                br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                String output;

                while ((output = br.readLine()) != null) {
                    System.out.println("'" + output + "'");
                }
                if (output.equals("true")) {
                    ret = true;
                }
                conn.disconnect();

            } catch (IOException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return ret;
            }
        }
    }

    private class RESTGetAllByRange extends AsyncTask<Object, Void, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
            try {
                Integer from = (Integer) params[0];
                Integer nbResut = (Integer) params[1];
                Context c = (Context) params[2];
                RessourcesServiceDataIn r = PhysiqueDataInFactory.getRessourceSrv(c);
                Ressource ressource = null;
                try {
                    ressource = r.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                InputStream fluxLecture = null;
                URL url = new URL(ressource.getPathToAccesWebService() + "utilisateur/" + from + "/" + nbResut);
                fluxLecture = url.openStream();
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fluxLecture);
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("utilisateur");
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Utilisateur utilisateur = new Utilisateur();
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        utilisateur.setEmail(getTagValue("email", eElement));
                        utilisateur.setId(Long.parseLong(getTagValue("id", eElement)));
                        utilisateur.setVille(getTagValue("ville", eElement));
                        utilisateur.setNom(getTagValue("nom", eElement));
                        utilisateur.setPrenom(getTagValue("prenom", eElement));
                        utilisateur.setCodePostale(Integer.parseInt(getTagValue("codePostale", eElement)));
                        utilisateur.setAdresse(getTagValue("adresse", eElement));
                        utilisateur.setTelephonePortable(getTagValue("telephonePortable", eElement));
                        utilisateur.setTelephoneFixe(getTagValue("telephoneFixe", eElement));
                        utilisateur.setHomme(Boolean.parseBoolean(getTagValue("homme", eElement)));
                        if (getTagValue("dateDeNaissance", eElement) != null) {
                            String dateStr = getTagValue("dateDeNaissance", eElement);
                            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            Date d = new Date();
                            try {
                                d = dateFormat.parse(dateStr);
                            } catch (ParseException ex) {
                                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            utilisateur.setDateDeNaissance(d);

                        }
                        utilisateurs.add(utilisateur);
                    }
                }

            } catch (SAXException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return utilisateurs;
        }
    }

    private class RESTVerifConnexionBackGround extends AsyncTask<Object, Void, Object> {

        private Context context;
        private ProgressDialog progressDialog;

        private Context getApplicationContext() {
            return context;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(this.getApplicationContext(), "", "Vérification ...", true);
        }

        public RESTVerifConnexionBackGround(Context c) {
            this.context = c;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            this.progressDialog.cancel();
        }

        @Override
        protected Object doInBackground(Object... params) {
            Context c = (Context) params[1];
            Technicien technicien = new Technicien();
            try {
                Technicien utilisateur = (Technicien) params[0];

                RessourcesServiceDataIn r = PhysiqueDataInFactory.getRessourceSrv(c);
                Ressource ressource = null;
                try {
                    ressource = r.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                Long id = utilisateur.getId();
                String prenom = utilisateur.getPrenom();
                String nom = utilisateur.getNom();
                String email = utilisateur.getEmail();
                String telephoneFixe = utilisateur.getTelephoneFixe();
                String telephonePortable = utilisateur.getTelephonePortable();
                String ville = utilisateur.getVille();
                int codePostale = utilisateur.getCodePostale();
                String adresse = utilisateur.getAdresse();
                boolean homme = utilisateur.isHomme();
                String login = utilisateur.getLogin();
                String password = utilisateur.getPassword();
                Date dateDeNaissance = utilisateur.getDateDeNaissance();
                URL url = new URL(ressource.getPathToAccesWebService() + "utilisateur/verificationConnexion");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                String input = "{\"id\":" + id + ",\"prenom\":\"" + prenom + "\",\"nom\":\"" + nom + "\",\"email\":\"" + email + "\",\"telephoneFixe\":\"" + telephoneFixe + "\","
                        + "\"telephonePortable\":\"" + telephonePortable + "\",\"ville\":\"" + ville + "\",\"codePostale\":" + codePostale + ","
                        + "\"adresse\":\"" + adresse + "\",\"homme\":\"" + homme + "\",\"dateDeNaissance\":\"" + dateDeNaissance + "\",\"login\":\"" + login + "\",\"password\":\"" + password + "\"}";
                OutputStream os = conn.getOutputStream();
                os.write(input.getBytes());
                os.flush();
                InputStream fluxLecture = null;
                fluxLecture = conn.getInputStream();
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fluxLecture);
                doc.getDocumentElement().normalize();
                try {
                    NodeList nList = doc.getElementsByTagName("administrateur");
                    Node nNode = nList.item(0);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        technicien.setEmail(getTagValue("email", eElement));
                        technicien.setId(Long.parseLong(getTagValue("id", eElement)));
                        technicien.setLogin(getTagValue("login", eElement));
                        technicien.setNom(getTagValue("nom", eElement));
                        technicien.setPassword(getTagValue("password", eElement));
                        technicien.setPrenom(getTagValue("prenom", eElement));
                        technicien.setVille(getTagValue("ville", eElement));
                        technicien.setCodePostale(Integer.parseInt(getTagValue("codePostale", eElement)));
                        technicien.setAdresse(getTagValue("adresse", eElement));
                        technicien.setTelephonePortable(getTagValue("telephonePortable", eElement));
                        technicien.setTelephoneFixe(getTagValue("telephoneFixe", eElement));
                        technicien.setHomme(Boolean.parseBoolean(getTagValue("homme", eElement)));
                        if (getTagValue("dateDeNaissance", eElement) != null) {
                            long parse = Date.parse(getTagValue("dateDeNaissance", eElement));
                            Date d = new Date(parse);
                            technicien.setDateDeNaissance(d);
                        }
                    }
                } catch (Exception e) {
                    NodeList nList = doc.getElementsByTagName("technicien");
                    Node nNode = nList.item(0);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        technicien.setEmail(getTagValue("email", eElement));
                        technicien.setId(Long.parseLong(getTagValue("id", eElement)));
                        technicien.setLogin(getTagValue("login", eElement));
                        technicien.setNom(getTagValue("nom", eElement));
                        technicien.setPassword(getTagValue("password", eElement));
                        technicien.setPrenom(getTagValue("prenom", eElement));
                        technicien.setVille(getTagValue("ville", eElement));
                        technicien.setCodePostale(Integer.parseInt(getTagValue("codePostale", eElement)));
                        technicien.setAdresse(getTagValue("adresse", eElement));
                        technicien.setTelephonePortable(getTagValue("telephonePortable", eElement));
                        technicien.setTelephoneFixe(getTagValue("telephoneFixe", eElement));
                        technicien.setHomme(Boolean.parseBoolean(getTagValue("homme", eElement)));
                        if (getTagValue("dateDeNaissance", eElement) != null) {
                            long parse = Date.parse(getTagValue("dateDeNaissance", eElement));
                            Date d = new Date(parse);
                            technicien.setDateDeNaissance(d);
                        }
                    }
                }
                conn.disconnect();
            }catch (ParserConfigurationException ex) {
                technicien = null;
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                technicien = null;
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ConnectException e) {
                technicien = null;
            } catch (SSLPeerUnverifiedException e) {
                technicien = null;
            } catch (IOException ex) {
                technicien = null;
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }catch(Throwable e){
                System.out.println("Throwable");
            }
            return technicien;
        }
    }

    private class RESTCount extends AsyncTask<Object, Void, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            int ret = 0;
            try {
                Context c = (Context) params[0];
                RessourcesServiceDataIn r = PhysiqueDataInFactory.getRessourceSrv(c);
                Ressource ressource = null;
                try {
                    ressource = r.getRessource();
                } catch (Exception ex) {
                    Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                BufferedReader br = null;
                URL url = new URL(ressource.getPathToAccesWebService() + "utilisateur/count");
                InputStream fluxLecture = url.openStream();
                br = new BufferedReader(new InputStreamReader((fluxLecture)));
                String output;
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                    ret = Integer.parseInt(output);
                }
                br.close();
            } catch (MalformedURLException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ret;
        }
    }
    private class RESTAddTechniecien extends AsyncTask<Object, Void, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            Context c = (Context) params[1];
            RessourcesServiceDataIn r = PhysiqueDataInFactory.getRessourceSrv(c);
            Ressource ressource = null;
            try {
                ressource = r.getRessource();
            } catch (Exception ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            Boolean retour = true;
            try {
                Technicien utilisateur = (Technicien) params[0];
                utilisateur.encode(true);
                String prenom = utilisateur.getPrenom();
                String nom = utilisateur.getNom();
                String email = utilisateur.getEmail();
                String login = utilisateur.getLogin();
                String password = utilisateur.getPassword();
                String telephoneFixe = utilisateur.getTelephoneFixe();
                String telephonePortable = utilisateur.getTelephonePortable();
                String ville = utilisateur.getVille();
                int codePostale = utilisateur.getCodePostale();
                String adresse = utilisateur.getAdresse();
                boolean homme = utilisateur.isHomme();
                Date dateDeNaissance = utilisateur.getDateDeNaissance();
                URL url = new URL(ressource.getPathToAccesWebService() + "technicien");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                String input = "{\"id\":0,\"prenom\":\"" + prenom + "\",\"nom\":\"" + nom + "\",\"email\":\"" + email + "\",\"telephone"
                        + "Fixe\":\"" + telephoneFixe + "\",\"telephonePortable\":\"" + telephonePortable + "\",\"ville\":\"" + ville + "\",\"cod"
                        + "ePostale\":" + codePostale + ",\"adresse\":\"" + adresse + "\",\"homme\":\"" + homme + "\",\""
                        + "dateDeNaissance\":\"" + dateDeNaissance + "\",\"login\":\"" + login + "\",\"password\":\"" + password + "\"}";
                OutputStream os = conn.getOutputStream();
                System.out.println(input);
                os.write(input.getBytes());
                os.flush();
                if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                    if (conn.getResponseCode() != 204) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + conn.getResponseCode());
                    } else {
                        System.out.println("Requete envoyé mais pas de réponse du serveur.");
                    }
                }
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }
                conn.disconnect();

            } catch (IOException ex) {
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return retour;
        }
    }
}
