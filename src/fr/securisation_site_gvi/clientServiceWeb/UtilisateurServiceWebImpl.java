/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.clientServiceWeb;

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
import fr.securisation_site_gvi.entitys.Utilisateur;
import fr.securisation_site_gvi.ressources.Ressources;
import java.util.Date;

/**
 *
 * @author damien
 */
public class UtilisateurServiceWebImpl implements UtilisateurServiceWeb {

   

    private static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }
//
//    @Override
//    public boolean update(Personne personne) throws Exception {
//        Boolean retour = true;
//        Long id = personne.getId();
//        String prenom = personne.getPrenom();
//        String nom = personne.getNom();
//        String password = personne.getPassword();
//        String login = personne.getLogin();
//        String email = personne.getEmail();
//        boolean isAdministrateur = personne.isIsAdministrateur();
//        Integer telephone = personne.getTelephone();
//        personne.encode(true);
//        String surl = R.getPathToAccesWebService() + "metier.personne";
//        URL url = new URL(surl);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setDoOutput(true);
//        conn.setRequestMethod("PUT");
//        conn.setRequestProperty("Content-Type", "application/json");
//        String input = "{\"id\":" + id + ",\"prenom\":\"" + prenom + "\",\"nom\":\"" + nom + "\",\"email\":\"" + email + "\",\"password\":\"" + password + "\",\"login\":\"" + login + "\",\"isAdministrateur\":" + isAdministrateur + ",\"telephone\":" + telephone + ",\"carnetAdresse\":null}";
//        System.out.println(input);
//        OutputStream os = conn.getOutputStream();
//        os.write(input.getBytes());
//        os.flush();
//        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//            if (conn.getResponseCode() != 204) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + conn.getResponseCode());
//            } else {
//                System.out.println("Requete envoyé mais pas de réponse du serveur.");
//            }
//        }
//        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//        String output;
//        System.out.println("Output from Server .... \n");
//        while ((output = br.readLine()) != null) {
//            System.out.println(output);
//        }
//        conn.disconnect();
//        return retour;
//    }

//    @Override
//    public Personne testConnexion(Personne personne) throws Exception {
//        Personne retour = null;
//        Long id = personne.getId();
//        String prenom = personne.getPrenom();
//        String nom = personne.getNom();
//        String password = personne.getPassword();
//        String login = personne.getLogin();
//        String email = personne.getEmail();
//        boolean isAdministrateur = personne.isIsAdministrateur();
//        Integer telephone = personne.getTelephone();
//        URL url = new URL(R.getPathToAccesWebService() + "metier.personne/testConnexion");
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setDoOutput(true);
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Content-Type", "application/json");
//        String input = "{\"id\":0,\"prenom\":\"" + prenom + "\",\"nom\":\"" + nom + "\",\"email\":\"" + email + "\",\"password\":\"" + password + "\",\"login\":\"" + login + "\",\"isAdministrateur\":" + isAdministrateur + ",\"telephone\":" + telephone + ",\"carnetAdresse\":null}";
//        OutputStream os = conn.getOutputStream();
//        os.write(input.getBytes());
//        os.flush();
//        InputStream fluxLecture = null;
//        fluxLecture = conn.getInputStream();
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        Document doc = dBuilder.parse(fluxLecture);
//        doc.getDocumentElement().normalize();
//        NodeList nList = doc.getElementsByTagName("personne");
//        Node nNode = nList.item(0);
//        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//            Element eElement = (Element) nNode;
//            personne.setEmail(getTagValue("email", eElement));
//            personne.setId(Long.parseLong(getTagValue("id", eElement)));
//            personne.setIsAdministrateur(Boolean.parseBoolean(getTagValue("isAdministrateur", eElement)));
//            personne.setLogin(getTagValue("login", eElement));
//            personne.setNom(getTagValue("nom", eElement));
//            personne.setPassword(getTagValue("password", eElement));
//            personne.setPrenom(getTagValue("prenom", eElement));
//            personne.setTelephone(Integer.parseInt(getTagValue("telephone", eElement)));
//        }
//        conn.disconnect();
//        return personne;
//    }

    public List<Utilisateur> getAll() throws Exception {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
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

        return utilisateurs;
    }

    public boolean add(Utilisateur utilisateur) throws Exception {
        Boolean retour = true;
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
        URL url = new URL(Ressources.getPathToAccesWebService() + "utilisateur/post");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        String input = "{\"id\":0,\"prenom\":\"" + prenom + "\",\"nom\":\"" + nom + "\",\"email\":\"" + email + "\",\"telephoneFixe\":\"" + telephoneFixe + "\",\"telephonePortable\":\"" + telephonePortable + "\",\"ville\":" + ville + ",\"codePostale\":" + codePostale + ",\"adresse\":"+adresse+",\"homme\":\"" + homme + "\",\"dateDeNaissance\":\"" + dateDeNaissance + "\"}";
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
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
        conn.disconnect();
        return retour;
    }

    public boolean remove(Utilisateur utilisateur) throws Exception {
        Boolean retour = true;
        Long id = utilisateur.getId();
   
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
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
        conn.disconnect();
        return retour;
    }

    public boolean update(Utilisateur utilisateur) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean loginIsUse(String login) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Utilisateur verificationConnexion(Utilisateur utilisateur) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
