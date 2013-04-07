/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.badge.rest;

import android.content.Context;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import physique.dataOut.utilisateur.UtilisateurServiceWebImpl;

/**
 *
 * @author damien
 */
public class RESTBadgeGetAllByRange extends AsyncTask<Object, Void, Object> {

    @Override
    protected Object doInBackground(Object... params) {
        Context c = (Context) params[0];
        Ressource ressource = (Ressource) params[1];
        Integer index = (Integer) params[2];
        Integer nbResult = (Integer) params[3];
        List<Badge> badges = new ArrayList<Badge>();
        try {
            InputStream fluxLecture = null;
            URL url = new URL(ressource.getPathToAccesWebService() + "badge/" + index + "/" + nbResult);
            fluxLecture = url.openStream();
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
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilisateurServiceWebImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return badges;
    }
}
