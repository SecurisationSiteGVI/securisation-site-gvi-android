/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.badge;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Badge;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface BadgeServiceWeb {

    /**
     *
     * @param ressource
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public List<Badge> getAll( Ressource ressource) throws ParserConfigurationException, SAXException, IOException;

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
    public List<Badge> getAll( Ressource ressource, int index, int nbResultat) throws ParserConfigurationException, SAXException, IOException;

    /**
     *
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public int count( Ressource ressource) throws MalformedURLException, IOException;

    /**
     *
     * @param ressource
     * @param badge
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public boolean remove( Ressource ressource, Badge badge) throws MalformedURLException, IOException;

    /**
     *
     * @param ressource
     * @param badge
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public boolean add(Ressource ressource, Badge badge) throws MalformedURLException, IOException;
}
