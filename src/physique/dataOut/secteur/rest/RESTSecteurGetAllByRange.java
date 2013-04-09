/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.secteur.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Badge;
import metier.entitys.Ressource;
import metier.entitys.Secteur;
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
public class RESTSecteurGetAllByRange {
    public static List<Secteur> execute(Ressource ressource, int index, int nbResutltat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException{
        List<Secteur> secteurs = new ArrayList<Secteur>();
        InputStream fluxLecture = null;
        URL url = new URL(ressource.getPathToAccesWebService() + "secteur/" + index + "/" + nbResutltat);
        fluxLecture = url.openStream();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fluxLecture);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("secteur");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Secteur secteur = new Secteur();
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                secteur.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElement)));
                secteur.setNom(BoiteAOutils.getTagValue("nom", eElement));
                secteurs.add(secteur);
            }
        }
        return secteurs;
    }
}
