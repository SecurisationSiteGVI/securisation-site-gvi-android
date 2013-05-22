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
import metier.entitys.Secteur;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface SecteurService {
    /**
     *
     * @param context
     * @param secteur
     * @return
     * @throws IOException
     * @throws Exception
     */
    public boolean ajouter(Context context,  Secteur secteur)throws IOException, Exception;
    /**
     *
     * @param context
     * @param index
     * @param nbResutltat
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws Exception
     */
    public List<Secteur> getAll(Context context,  int index, int nbResutltat)throws MalformedURLException, IOException, ParserConfigurationException, SAXException, Exception;
    /**
     *
     * @param context
     * @param secteur
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public boolean remove(Context context, Secteur secteur)throws MalformedURLException, IOException, Exception;
    /**
     *
     * @param context
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public int count(Context context) throws MalformedURLException, IOException, Exception;
}
