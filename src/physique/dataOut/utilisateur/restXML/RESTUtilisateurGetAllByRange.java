/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.utilisateur.restXML;

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
import physique.dataOut.BoiteAOutils;
import physique.dataOut.utilisateur.UtilisateurServiceWebXMLImpl;

/**
 *
 * @author damien
 */
public class RESTUtilisateurGetAllByRange {

    /**
     *
     * @param params
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws MalformedURLException
     * @throws IOException
     */
    public Object execute(Object... params) throws SAXException, ParserConfigurationException, MalformedURLException, IOException {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        Integer from = (Integer) params[0];
        Integer nbResut = (Integer) params[1];
        Ressource ressource = (Ressource) params[2];
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
                utilisateur.setEmail(BoiteAOutils.getTagValue("email", eElement));
                utilisateur.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElement)));
                utilisateur.setVille(BoiteAOutils.getTagValue("ville", eElement));
                utilisateur.setNom(BoiteAOutils.getTagValue("nom", eElement));
                utilisateur.setPrenom(BoiteAOutils.getTagValue("prenom", eElement));
                utilisateur.setCodePostale(Integer.parseInt(BoiteAOutils.getTagValue("codePostale", eElement)));
                utilisateur.setAdresse(BoiteAOutils.getTagValue("adresse", eElement));
                utilisateur.setTelephonePortable(BoiteAOutils.getTagValue("telephonePortable", eElement));
                utilisateur.setTelephoneFixe(BoiteAOutils.getTagValue("telephoneFixe", eElement));
                utilisateur.setHomme(Boolean.parseBoolean(BoiteAOutils.getTagValue("homme", eElement)));
                if (BoiteAOutils.getTagValue("dateDeNaissance", eElement) != null) {
                    String dateStr = BoiteAOutils.getTagValue("dateDeNaissance", eElement);
                    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    Date d = new Date();
                    try {
                        d = dateFormat.parse(dateStr);
                    } catch (ParseException ex) {
                        Logger.getLogger(UtilisateurServiceWebXMLImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    utilisateur.setDateDeNaissance(d);

                }
                utilisateurs.add(utilisateur);
            }
        }
        return utilisateurs;
    }
}