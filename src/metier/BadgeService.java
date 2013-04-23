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

    /**
     *
     * @param context
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws Exception
     */
    public List<Badge> getAll(Context context) throws ParserConfigurationException, SAXException, IOException, Exception;

    /**
     *
     * @param context
     * @param index
     * @param nbResultat
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws Exception
     */
    public List<Badge> getAll(Context context, int index, int nbResultat) throws ParserConfigurationException, SAXException, IOException, Exception;

    /**
     *
     * @param c
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public int count(Context c) throws MalformedURLException, IOException, Exception;

    /**
     *
     * @param c
     * @param badge
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public boolean remove(Context c, Badge badge) throws MalformedURLException, IOException, Exception;

    /**
     *
     * @param context
     * @param badge
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public boolean add(Context context, Badge badge) throws MalformedURLException, IOException, Exception;
}
