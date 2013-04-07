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

    public List<Badge> getAll(Context context, Ressource ressource) throws ParserConfigurationException, SAXException, IOException;

    public List<Badge> getAll(Context context, Ressource ressource, int index, int nbResultat) throws ParserConfigurationException, SAXException, IOException;

    public int count(Context c, Ressource ressource) throws MalformedURLException, IOException;

    public boolean remove(Context c, Ressource ressource, Badge badge) throws MalformedURLException, IOException;

    public boolean add(Context context, Ressource ressource, Badge badge) throws MalformedURLException, IOException;
}
