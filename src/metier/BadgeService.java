/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Badge;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface BadgeService {

    public List<Badge> getAll(Context context) throws ParserConfigurationException, SAXException, IOException, Exception;

    public List<Badge> getAll(Context context, int index, int nbResultat) throws ParserConfigurationException, SAXException, IOException, Exception;

    public int count(Context c) throws MalformedURLException, IOException, Exception;

    public boolean remove(Context c, Badge badge) throws MalformedURLException, IOException, Exception;

    public boolean add(Context context, Badge badge) throws MalformedURLException, IOException, Exception;
}
