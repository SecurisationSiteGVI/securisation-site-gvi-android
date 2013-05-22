/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.attributionUtilisateurBadge.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Badge;
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
public class RESTAttributionBadgeGetBadgeNotAssign {

    /**
     *
     * @param params
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Object execute(Object... params) throws ParserConfigurationException, SAXException, IOException {
        Ressource ressource = (Ressource) params[0];
        Integer debut = (Integer) params[1];
        Integer nbResult = (Integer) params[2];
        List<Badge> badges = new ArrayList<Badge>();
        URL url = new URL(ressource.getPathToAccesWebService() + "attributionutilisateurbadge/getBadgesNotAssign/" + debut + "/" + nbResult);
        InputStream fluxLecture = url.openStream();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fluxLecture);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("badge");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Badge badge = new Badge();
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                badge.setId(Long.parseLong(BoiteAOutils.getTagValue("id", eElement)));
                badge.setNumero(BoiteAOutils.getTagValue("numero", eElement));
                badges.add(badge);
            }
        }
        return badges;
    }
}
