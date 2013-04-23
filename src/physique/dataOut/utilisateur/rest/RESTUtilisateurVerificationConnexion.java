/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.utilisateur.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Ressource;
import metier.entitys.Technicien;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import physique.dataOut.BoiteAOutils;

/**
 *
 * @author damien
 */
public class RESTUtilisateurVerificationConnexion {

    /**
     *
     * @param params
     * @return
     * @throws Throwable
     * @throws IOException
     * @throws SSLPeerUnverifiedException
     * @throws ConnectException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public Object execute(Object... params) throws Throwable, IOException, SSLPeerUnverifiedException, ConnectException, SAXException, ParserConfigurationException {
        Ressource ressource = (Ressource) params[1];
        Technicien technicien = new Technicien();
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
                technicien.setEmail(BoiteAOutils.getTagValue("email", eElement));
                technicien.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElement)));
                technicien.setLogin(BoiteAOutils.getTagValue("login", eElement));
                technicien.setNom(BoiteAOutils.getTagValue("nom", eElement));
                technicien.setPassword(BoiteAOutils.getTagValue("password", eElement));
                technicien.setPrenom(BoiteAOutils.getTagValue("prenom", eElement));
                technicien.setVille(BoiteAOutils.getTagValue("ville", eElement));
                technicien.setCodePostale(Integer.parseInt(BoiteAOutils.getTagValue("codePostale", eElement)));
                technicien.setAdresse(BoiteAOutils.getTagValue("adresse", eElement));
                technicien.setTelephonePortable(BoiteAOutils.getTagValue("telephonePortable", eElement));
                technicien.setTelephoneFixe(BoiteAOutils.getTagValue("telephoneFixe", eElement));
                technicien.setHomme(Boolean.parseBoolean(BoiteAOutils.getTagValue("homme", eElement)));
                if (BoiteAOutils.getTagValue("dateDeNaissance", eElement) != null) {
                    long parse = Date.parse(BoiteAOutils.getTagValue("dateDeNaissance", eElement));
                    Date d = new Date(parse);
                    technicien.setDateDeNaissance(d);
                }
            }
        } catch (Exception e) {
            NodeList nList = doc.getElementsByTagName("technicien");
            Node nNode = nList.item(0);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                technicien.setEmail(BoiteAOutils.getTagValue("email", eElement));
                technicien.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElement)));
                technicien.setLogin(BoiteAOutils.getTagValue("login", eElement));
                technicien.setNom(BoiteAOutils.getTagValue("nom", eElement));
                technicien.setPassword(BoiteAOutils.getTagValue("password", eElement));
                technicien.setPrenom(BoiteAOutils.getTagValue("prenom", eElement));
                technicien.setVille(BoiteAOutils.getTagValue("ville", eElement));
                technicien.setCodePostale(Integer.parseInt(BoiteAOutils.getTagValue("codePostale", eElement)));
                technicien.setAdresse(BoiteAOutils.getTagValue("adresse", eElement));
                technicien.setTelephonePortable(BoiteAOutils.getTagValue("telephonePortable", eElement));
                technicien.setTelephoneFixe(BoiteAOutils.getTagValue("telephoneFixe", eElement));
                technicien.setHomme(Boolean.parseBoolean(BoiteAOutils.getTagValue("homme", eElement)));
                if (BoiteAOutils.getTagValue("dateDeNaissance", eElement) != null) {
                    long parse = Date.parse(BoiteAOutils.getTagValue("dateDeNaissance", eElement));
                    Date d = new Date(parse);
                    technicien.setDateDeNaissance(d);
                }
            }
        }
        conn.disconnect();
        return technicien;
    }
}