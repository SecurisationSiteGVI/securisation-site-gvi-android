/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.numeroPredefini.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.DetecteurIntrusion;
import metier.entitys.Position;
import metier.entitys.Ressource;
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
public class RESTNumeroPredefiniGetAll {
    public static List<DetecteurIntrusion> execute(Ressource ressource) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        List<DetecteurIntrusion> detecteurIntrusions = new ArrayList<DetecteurIntrusion>();
        InputStream fluxLecture = null;
        URL url = new URL(ressource.getPathToAccesWebService() + "detecteurintrusion");
        fluxLecture = url.openStream();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fluxLecture);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("detecteurintrusion");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            DetecteurIntrusion detecteurIntrusion = new DetecteurIntrusion();
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                detecteurIntrusion.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElement)));
                detecteurIntrusion.setNom(BoiteAOutils.getTagValue("nom", eElement));
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
                detecteurIntrusion.setPosition(position);
                detecteurIntrusions.add(detecteurIntrusion);
            }
        }
        return detecteurIntrusions;
    }
}
