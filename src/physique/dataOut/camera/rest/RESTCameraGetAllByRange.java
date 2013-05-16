/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.camera.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Camera;
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
public class RESTCameraGetAllByRange {
    /**
     *
     * @param ressource
     * @param index
     * @param nbResult
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static List<Camera> execute(Ressource ressource,int index, int nbResult) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        List<Camera> cameras = new ArrayList<Camera>();
        InputStream fluxLecture = null;
        URL url = new URL(ressource.getPathToAccesWebService() + "camera/"+index+"/"+nbResult);
        fluxLecture = url.openStream();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fluxLecture);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("camera");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Camera camera = new Camera();
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                camera.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElement)));
                camera.setIp(BoiteAOutils.getTagValue("ip", eElement));
                camera.setNom(BoiteAOutils.getTagValue("nom", eElement));
                camera.setType(BoiteAOutils.getTagValue("type", eElement));
                Double latitude = null;
                Double longitude = null;
                Long idPos = null;
                NodeList nodePosition = doc.getElementsByTagName("position");
                if(nodePosition.getLength()!=0){
                    for (int tempPosition = 0; tempPosition < nodePosition.getLength(); tempPosition++) {
                    Node nNodePosition = nodePosition.item(tempPosition);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElementPosition = (Element) nNodePosition;
                        latitude = Double.parseDouble(BoiteAOutils.getTagValue("latitude", eElementPosition));
                        longitude = Double.parseDouble(BoiteAOutils.getTagValue("longitude", eElementPosition));
                        idPos = Long.parseLong(BoiteAOutils.getTagValue("id", eElementPosition));
                    }
                }Position position = new Position();
                position.setId(idPos);
                position.setLongitude(longitude);
                position.setLatitude(latitude);
                camera.setPosition(position);
                }
                             
                
                cameras.add(camera);
            }
        }
        return cameras;
    }
}
