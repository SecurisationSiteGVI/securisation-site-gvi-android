/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.badge;

import android.content.Context;
import android.os.AsyncTask;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Badge;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;
import physique.dataOut.badge.rest.RESTBadgeAdd;
import physique.dataOut.badge.rest.RESTBadgeCount;
import physique.dataOut.badge.rest.RESTBadgeGetAll;
import physique.dataOut.badge.rest.RESTBadgeGetAllByRange;
import physique.dataOut.badge.rest.RESTBadgeRemove;

/**
 *
 * @author damien
 */
public class BadgeServiceWebImpl implements BadgeServiceWeb {

    /**
     *
     * @param ressource
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public List<Badge> getAll(Ressource ressource) throws ParserConfigurationException, SAXException, IOException {
        List<Badge> retour = (List<Badge>) RESTBadgeGetAll.execute(ressource);
        return retour;
    }

    /**
     *
     * @param ressource
     * @param index
     * @param nbResultat
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public List<Badge> getAll(Ressource ressource, int index, int nbResultat) throws ParserConfigurationException, SAXException, IOException {
        List<Badge> retour = (List<Badge>) RESTBadgeGetAllByRange.execute(ressource, index, nbResultat);
        return retour;
    }

    /**
     *
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public int count(Ressource ressource) throws MalformedURLException, IOException {
        Integer retour = (Integer) RESTBadgeCount.execute(ressource);
        return retour;
    }

    /**
     *
     * @param ressource
     * @param badge
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public boolean remove(Ressource ressource, Badge badge) throws MalformedURLException, IOException {
        Boolean retour = (Boolean) RESTBadgeRemove.execute(ressource, badge);
        return retour;
    }

    /**
     *
     * @param ressource
     * @param badge
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public boolean add(Ressource ressource, Badge badge) throws MalformedURLException, IOException {
        Boolean retour = (Boolean) RESTBadgeAdd.execute(ressource, badge);
        return retour;
    }
}
