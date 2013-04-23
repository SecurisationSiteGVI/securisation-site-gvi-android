/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.evenement.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Acces;
import metier.entitys.BorneAcces;
import metier.entitys.Camera;
import metier.entitys.DetecteurIntrusion;
import metier.entitys.Evenement;
import metier.entitys.Intrusion;
import metier.entitys.Photo;
import metier.entitys.Position;
import metier.entitys.Ressource;
import metier.entitys.Utilisateur;
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
public class RESTEvenementGetByID {

    /**
     *
     * @param params
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws SAXException
     * @throws ParseException
     * @throws ParserConfigurationException
     */
    public static Object execute(Object... params) throws MalformedURLException, IOException, SAXException, ParseException, ParserConfigurationException {
        Ressource ressource = (Ressource) params[0];
        Long id = (Long) params[1];
        Evenement evenement = null;
        Acces acces = null;
        Photo photo = null;
        Intrusion intrusion = null;
        InputStream fluxLecture = null;
        URL url = null;
        url = new URL(ressource.getPathToAccesWebService() + "evenement/" + id);
        fluxLecture = url.openStream();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fluxLecture);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("acces");
        acces = new Acces();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                acces.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElement)));
                Long idborne = null;
                Boolean entre = null;
                String nom = null;
                Long idPos = null;
                Double latitude = null;
                Double longitude = null;
                NodeList nodeBorneAcces = doc.getElementsByTagName("borneAcces");
                for (int temp2 = 0; temp2 < nodeBorneAcces.getLength(); temp2++) {
                    Node nNodeBorneAcces = nodeBorneAcces.item(temp2);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElementBorneAcces = (Element) nNodeBorneAcces;
                        idborne = Long.parseLong(BoiteAOutils.getTagValue("id", eElementBorneAcces));
                        entre = Boolean.parseBoolean(BoiteAOutils.getTagValue("entrer", eElementBorneAcces));
                        nom = BoiteAOutils.getTagValue("nom", eElementBorneAcces);
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
                    }
                }
                acces.setPassage(Boolean.parseBoolean(BoiteAOutils.getTagValue("passage", eElement)));
                BorneAcces b = new BorneAcces();
                b.setId(idborne);
                b.setEntrer(entre);
                b.setNom(nom);
                Position pos = new Position();
                pos.setLongitude(longitude);
                pos.setLatitude(latitude);
                pos.setId(id);
                b.setPosition(pos);
                acces.setBorneAcces(b);
                NodeList nodeUtilisateur = doc.getElementsByTagName("utilisateur");
                String adresse = null;
                Integer codePostale = null;
                Boolean homme = null;
                Long idUtilisateur = null;
                String nomUtilisateur = null;
                String prenom = null;
                String ville = null;
                for (int tempUtilisateur = 0; tempUtilisateur < nodeUtilisateur.getLength(); tempUtilisateur++) {
                    Node nNodeUtilisateur = nodeUtilisateur.item(tempUtilisateur);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElementUtilisateur = (Element) nNodeUtilisateur;
                        idUtilisateur = Long.parseLong(BoiteAOutils.getTagValue("id", eElementUtilisateur));
                        homme = Boolean.parseBoolean(BoiteAOutils.getTagValue("homme", eElementUtilisateur));
                        adresse = BoiteAOutils.getTagValue("adresse", eElementUtilisateur);
                        codePostale = Integer.parseInt(BoiteAOutils.getTagValue("codePostale", eElementUtilisateur));
                        nomUtilisateur = BoiteAOutils.getTagValue("nom", eElementUtilisateur);
                        prenom = BoiteAOutils.getTagValue("prenom", eElementUtilisateur);
                        ville = BoiteAOutils.getTagValue("ville", eElementUtilisateur);

                    }
                }
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setAdresse(adresse);
                utilisateur.setCodePostale(codePostale);
                utilisateur.setHomme(homme);
                utilisateur.setId(idUtilisateur);
                utilisateur.setNom(nomUtilisateur);
                utilisateur.setPrenom(prenom);
                utilisateur.setVille(ville);
                acces.setUtilisateur(utilisateur);

                if (BoiteAOutils.getTagValue("dateEvt", eElement) != null) {
                    String dateStr = BoiteAOutils.getTagValue("dateEvt", eElement);
                    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    Date d = new Date();
                    d = dateFormat.parse(dateStr);
                    acces.setDateEvt(d);
                }
                evenement = acces;
            }
        }
        if (evenement == null) {
            NodeList nListP = doc.getElementsByTagName("photo");
            Photo p = new Photo();
            for (int temp = 0; temp < nListP.getLength(); temp++) {
                Node nNode = nListP.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    p.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElement)));
                    String img = BoiteAOutils.getTagValue("image", eElement);
                    p.setImage(img.getBytes());
                    NodeList nodeBorneAcces = doc.getElementsByTagName("camera");
                    Long idCamera = null;
                    String ip = null;
                    String nom = null;
                    String type = null;
                    Double latitude = null;
                    Double longitude = null;
                    Long idPos = null;
                    for (int temp2 = 0; temp2 < nodeBorneAcces.getLength(); temp2++) {
                        Node nNodeBorneAcces = nodeBorneAcces.item(temp2);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElementBorneAcces = (Element) nNodeBorneAcces;
                            idCamera = Long.parseLong(BoiteAOutils.getTagValue("id", eElementBorneAcces));
                            ip = BoiteAOutils.getTagValue("ip", eElementBorneAcces);
                            nom = BoiteAOutils.getTagValue("nom", eElementBorneAcces);
                            type = BoiteAOutils.getTagValue("type", eElementBorneAcces);
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
                        }
                    }
                    Camera camera = new Camera();
                    camera.setId(idCamera);
                    camera.setIp(ip);
                    camera.setNom(nom);
                    camera.setType(type);
                    Position position = new Position();
                    position.setLatitude(latitude);
                    position.setLatitude(latitude);
                    camera.setPosition(position);
                    p.setCamera(camera);
                    if (BoiteAOutils.getTagValue("dateEvt", eElement) != null) {
                        String dateStr = BoiteAOutils.getTagValue("dateEvt", eElement);
                        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        Date d = new Date();
                        d = dateFormat.parse(dateStr);
                        p.setDateEvt(d);
                    }
                    evenement = p;
                }
            }
        }
        if (evenement == null) {
            NodeList nListP = doc.getElementsByTagName("intrusion");
            Intrusion p = new Intrusion();
            for (int temp = 0; temp < nListP.getLength(); temp++) {
                Node nNode = nListP.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    p.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElement)));
                    String img = BoiteAOutils.getTagValue("image", eElement);
                    NodeList nodeDetecteur = doc.getElementsByTagName("detecteurIntrusion");
                    Long idDetecteur = null;
                    String ip = null;
                    String nom = null;
                    Double latitude = null;
                    Double longitude = null;
                    Long idPos = null;
                    for (int temp2 = 0; temp2 < nodeDetecteur.getLength(); temp2++) {
                        Node nNodeBorneAcces = nodeDetecteur.item(temp2);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElementBorneAcces = (Element) nNodeBorneAcces;
                            idDetecteur = Long.parseLong(BoiteAOutils.getTagValue("id", eElementBorneAcces));
                            ip = BoiteAOutils.getTagValue("ip", eElementBorneAcces);
                            nom = BoiteAOutils.getTagValue("nom", eElementBorneAcces);
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
                        }
                    }
                    DetecteurIntrusion detecteurIntrusion = new DetecteurIntrusion();
                    detecteurIntrusion.setId(idDetecteur);
                    detecteurIntrusion.setNom(nom);
                    Position position = new Position();
                    position.setLatitude(latitude);
                    position.setLatitude(latitude);
                    detecteurIntrusion.setPosition(position);
                    p.setDetecteurIntrusion(detecteurIntrusion);
                    if (BoiteAOutils.getTagValue("dateEvt", eElement) != null) {
                        String dateStr = BoiteAOutils.getTagValue("dateEvt", eElement);
                        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        Date d = new Date();
                        d = dateFormat.parse(dateStr);
                        p.setDateEvt(d);
                    }
                    evenement = p;
                }
            }
        }
        return evenement;
    }
}
