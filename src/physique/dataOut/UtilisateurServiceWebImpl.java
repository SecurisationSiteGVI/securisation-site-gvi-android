/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;
import fr.securisation_site_gvi.client.MainActivity;
import metier.entitys.Technicien;
import java.io.*;
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

import java.net.ProtocolException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import ressources.Ressources;

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
    public List<Utilisateur> getAll() throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTGetAll().execute();
        List<Utilisateur> retour = (List<Utilisateur>) ret.get();
        return retour;
    }

    @Override
    public boolean add(Utilisateur utilisateur) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTAdd().execute(utilisateur);
        Boolean retour = (Boolean) ret.get();
        return retour;
    }

    @Override
    public boolean remove(Utilisateur utilisateur) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTRemove().execute(utilisateur);
        Boolean retour = (Boolean) ret.get();
        return retour;
    }

    @Override
    public boolean update(Utilisateur utilisateur) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTUpdate().execute(utilisateur);
        Boolean retour = (Boolean) ret.get();
        return retour;
    }

    @Override
    public boolean loginIsUse(String loginn) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTLoginIsUse().execute(loginn);
        Boolean retour = (Boolean) ret.get();
        return retour;
    }

    @Override
    public Technicien verificationConnexion(Technicien utilisateur) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTVerifConnexionBackGround().execute(utilisateur);
        Technicien technicien = (Technicien) ret.get();
        return technicien;
    }

    @Override
    public List<Utilisateur> getAll(int from, int nbResut) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTGetAllByRange().execute(from, nbResut);
        List<Utilisateur> utilisateurs = (List<Utilisateur>) ret.get();
        return utilisateurs;

    }

    public Utilisateur getById(Long id) throws Exception {
        AsyncTask<Object, Void, Object> ret = new RESTGetById().execute(id);
        List<Utilisateur> utilisateurs = (List<Utilisateur>) ret.get();
        return utilisateurs.get(0);
    }
    private class RESTGetById extends AsyncTask<Object, Void, Object> {

        @Override
        protected Object doInBackground(Object... params) {
            List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
            try {System.out.println("PASSAAAAAAAAAAAAAAGEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE !!!!!!!!!!!!!!!\n\n\n\n");
                Long id = (Long) params[0];
                
                InputStream fluxLecture = null;
                URL url = new URL(Ressources.getPathToAccesWebService() + "utilisateur/" + id );
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
            List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
            try {
                InputStream fluxLecture = null;
                URL url = new URL(Ressources.getPathToAccesWebService() + "utilisateur");
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
                URL url = new URL(Ressources.getPathToAccesWebService() + "utilisateur");
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
                URL url = new URL(Ressources.getPathToAccesWebService() + "utilisateur/" + utilisateur.getId());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("DELETE");
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
                String surl = Ressources.getPathToAccesWebService() + "utilisateur";
                URL url = new URL(surl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("PUT");
                conn.setRequestProperty("Content-Type", "application/json");
                String input = "{\"id\":" + id + ",\"prenom\":\"" + prenom + "\",\"nom\":\"" + nom + "\",\"email\":\"" + email + "\",\"telephoneFixe\":\"" + telephoneFixe + "\",\"telephonePortable\":\"" + telephonePortable + "\",\"ville\":\"" + ville + "\",\"codePostale\":" + codePostale + ",\"adresse\":\"" + adresse + "\",\"homme\":\"" + homme + "\",\"dateDeNaissance\":\"" + dateDeNaissance + "\"}";
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
                URL url = new URL(Ressources.getPathToAccesWebService() + "utilisateur/loginIsUse");
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
                InputStream fluxLecture = null;
                URL url = new URL(Ressources.getPathToAccesWebService() + "utilisateur/" + from + "/" + nbResut);
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

        @Override
        protected Object doInBackground(Object... params) {
            Technicien technicien = new Technicien();
            try {
                Technicien utilisateur = (Technicien) params[0];
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
                URL url = new URL(Ressources.getPathToAccesWebService() + "utilisateur/verificationConnexion");
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
            } catch (ParserConfigurationException ex) {
                technicien = null;
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                technicien = null;
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                technicien = null;
                Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return technicien;
        }
    }
}