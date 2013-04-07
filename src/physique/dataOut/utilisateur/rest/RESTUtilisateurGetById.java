/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.utilisateur.rest;

import android.content.Context;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Ressource;
import metier.entitys.Utilisateur;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.BoiteAOutils;
import physique.dataOut.utilisateur.UtilisateurServiceWebImpl;

/**
 *
 * @author damien
 */
public class RESTUtilisateurGetById extends AsyncTask<Object, Void, Object> {

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
                    utilisateur.setEmail(BoiteAOutils.getTagValue("email", eElement));
                    utilisateur.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElement)));
                    utilisateur.setVille(BoiteAOutils.getTagValue("ville", eElement));
                    utilisateur.setNom(BoiteAOutils.getTagValue("nom", eElement));
                    utilisateur.setPrenom(BoiteAOutils.getTagValue("prenom", eElement));
                    if (BoiteAOutils.getTagValue("dateDeNaissance", eElement) != null) {
                        String dateStr = BoiteAOutils.getTagValue("dateDeNaissance", eElement);
                        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        Date d = new Date();
                        try {
                            d = dateFormat.parse(dateStr);
                        } catch (ParseException ex) {
                            Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        utilisateur.setDateDeNaissance(d);
                    }
                    utilisateur.setCodePostale(Integer.parseInt(BoiteAOutils.getTagValue("codePostale", eElement)));
                    utilisateur.setAdresse(BoiteAOutils.getTagValue("adresse", eElement));
                    utilisateur.setTelephonePortable(BoiteAOutils.getTagValue("telephonePortable", eElement));
                    utilisateur.setTelephoneFixe(BoiteAOutils.getTagValue("telephoneFixe", eElement));
                    utilisateur.setHomme(Boolean.parseBoolean(BoiteAOutils.getTagValue("homme", eElement)));
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