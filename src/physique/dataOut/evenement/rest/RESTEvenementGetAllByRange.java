/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.evenement.rest;

import java.io.IOException;
import java.io.InputStream;
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
import metier.entitys.Evenement;
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
public class RESTEvenementGetAllByRange {

    /**
     *
     * @param params
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Object execute(Ressource ressource,Integer index,Integer nbResult) throws ParserConfigurationException, SAXException, IOException {
        List<Evenement> evenements = new ArrayList<Evenement>();
        URL url = new URL(ressource.getPathToAccesWebService() + "evenement/" + index + "/" + nbResult);
        InputStream fluxLecture = url.openStream();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fluxLecture);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("evenement");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Evenement evenement = new Evenement();
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                evenement.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElement)));
                if (BoiteAOutils.getTagValue("dateEvt", eElement) != null) {
                    String dateStr = BoiteAOutils.getTagValue("dateEvt", eElement);
                    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    Date d = new Date();
                    try {
                        d = dateFormat.parse(dateStr);
                    } catch (ParseException ex) {
                        Logger.getLogger(UtilisateurServiceWebXMLImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    evenement.setDateEvt(d);
                }
                evenements.add(evenement);
            }
        }
        return evenements;
    }
}