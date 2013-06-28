/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.borneAcces.rest;

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
import metier.entitys.BorneAcces;
import metier.entitys.Position;
import metier.entitys.Ressource;
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
public class RESTBorneAccesGetAll {

    /**
     *
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static List<BorneAcces> execute(Ressource ressource) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        List<BorneAcces> borneAcceses = new ArrayList<BorneAcces>();
        InputStream fluxLecture = null;
        URL url = new URL(ressource.getPathToAccesWebService() + "borneacces");
        fluxLecture = url.openStream();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fluxLecture);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("borneAcces");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            BorneAcces borneAcces = new BorneAcces();
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                borneAcces.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElement)));
                borneAcces.setEntrer(Boolean.valueOf(BoiteAOutils.getTagValue("ville", eElement)));
                borneAcces.setNom(BoiteAOutils.getTagValue("nom", eElement));
                Double latitude = null;
                Double longitude = null;
                Long idPos = null;
                NodeList nodePosition = doc.getElementsByTagName("position");
                for (int tempPosition = 0; tempPosition < nodePosition.getLength(); tempPosition++) {
                    Node nNodePosition = nodePosition.item(tempPosition);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElementPosition = (Element) nNodePosition;
                        latitude = Double.parseDouble(BoiteAOutils.getTagValue("latitude", eElementPosition));
                        longitude = Double.parseDouble(BoiteAOutils.getTagValue("longitude", eElementPosition));
                        idPos = Long.parseLong(BoiteAOutils.getTagValue("id", eElementPosition));
                    }
                }
                Position position = new Position();
                position.setId(idPos);
                position.setLongitude(longitude);
                position.setLatitude(latitude);
                borneAcces.setPosition(position);
                borneAcceses.add(borneAcces);
            }
        }
        return borneAcceses;
    }
}
