/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.position.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Position;
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
public class RESTPositionGetByRange {
    /**
     *
     * @param ressource
     * @param index
     * @param nbResult
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws MalformedURLException
     * @throws IOException
     */
    public static List<Position> execute(Ressource ressource, int index, int nbResult) throws SAXException, ParserConfigurationException, MalformedURLException, IOException {
       List<Position> positions = new ArrayList<Position>();
        InputStream fluxLecture = null;
        URL url = new URL(ressource.getPathToAccesWebService() + "position/"+index+"/"+nbResult);
        fluxLecture = url.openStream();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fluxLecture);
        doc.getDocumentElement().normalize();
        NodeList nodePosition = doc.getElementsByTagName("position");
        for (int tempPosition = 0; tempPosition < nodePosition.getLength(); tempPosition++) {
            Node nNodePosition = nodePosition.item(tempPosition);
            Position position = new Position();
            if (nNodePosition.getNodeType() == Node.ELEMENT_NODE) {
                Element eElementPosition = (Element) nNodePosition;
                position.setLatitude(Double.parseDouble(BoiteAOutils.getTagValue("latitude", eElementPosition)));
                position.setLongitude(Double.parseDouble(BoiteAOutils.getTagValue("longitude", eElementPosition)));
                position.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElementPosition)));
                positions.add(position);
            }
        }
        return positions;
    }
}
