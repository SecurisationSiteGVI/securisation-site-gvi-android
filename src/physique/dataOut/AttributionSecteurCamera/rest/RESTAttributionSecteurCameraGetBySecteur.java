/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.AttributionSecteurCamera.rest;

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
import metier.entitys.AttributionSecteurCamera;
import metier.entitys.Camera;
import metier.entitys.Position;
import metier.entitys.Ressource;
import metier.entitys.Secteur;
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
public class RESTAttributionSecteurCameraGetBySecteur {

    /**
     *
     * @param ressource
     * @param secteur
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws MalformedURLException
     * @throws IOException
     */
    public static AttributionSecteurCamera execute(Ressource ressource, Secteur secteur) throws SAXException, ParserConfigurationException, MalformedURLException, IOException {
       AttributionSecteurCamera attributionSecteurCameras = new AttributionSecteurCamera();
        InputStream fluxLecture = null;
        URL url = new URL(ressource.getPathToAccesWebService() + "attributionsecteurcamera/" + secteur.getId());
        fluxLecture = url.openStream();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fluxLecture);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("attributionSecteurCamera");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            AttributionSecteurCamera attributionSecteurCamera = new AttributionSecteurCamera();
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                List<Camera> cameras = new ArrayList<Camera>();
                NodeList nListCamera = doc.getElementsByTagName("cameras");
                for (int tempCamera = 0; tempCamera < nListCamera.getLength(); tempCamera++) {
                    Camera camera = new Camera();
                    Node nNodeCamera = nListCamera.item(tempCamera);
                    if (nNodeCamera.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElementCamera = (Element) nNodeCamera;
                        camera.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElementCamera)));
                        camera.setIp(BoiteAOutils.getTagValue("ip", eElementCamera));
                        camera.setNom(BoiteAOutils.getTagValue("nom", eElementCamera));
                        camera.setType(BoiteAOutils.getTagValue("type", eElementCamera));
                        Double latitude = null;
                        Double longitude = null;
                        Long idPos = null;
                        NodeList nodePosition = doc.getElementsByTagName("position");
                        for (int tempPosition = 0; tempPosition < nodePosition.getLength(); tempPosition++) {
                            Node nNodePosition = nodePosition.item(tempPosition);
                            if (nNodeCamera.getNodeType() == Node.ELEMENT_NODE) {
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
                        camera.setPosition(position);
                        cameras.add(camera);
                    }
                }
                attributionSecteurCameras.setCameras(cameras);
                NodeList nListSecteur = doc.getElementsByTagName("secteur");
                for (int tempSecteur = 0; tempSecteur < nList.getLength(); tempSecteur++) {
                    Secteur secteur2 = new Secteur();
                    Node nNodeSecteur = nList.item(tempSecteur);
                    if (nNodeSecteur.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElementSecteur = (Element) nNodeSecteur;
                        secteur2.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElementSecteur)));
                        secteur2.setNom(BoiteAOutils.getTagValue("nom", eElementSecteur));
                    }
                    attributionSecteurCameras.setSecteur(secteur2);
                }
            }
        }
        return attributionSecteurCameras;
    }
}