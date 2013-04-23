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
import metier.entitys.DetecteurIntrusion;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface DetecteurIntrusionService {
    /**
     *
     * @param context
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws Exception
     */
    public List<DetecteurIntrusion> getAll(Context context)throws MalformedURLException, IOException, ParserConfigurationException, SAXException,Exception;

    /**
     *
     * @param context
     * @param index
     * @param nbResultat
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws Exception
     */
    public List<DetecteurIntrusion> getAll(Context context, int index, int nbResultat)throws MalformedURLException, IOException, ParserConfigurationException, SAXException,Exception ;
    
    /**
     *
     * @param contex
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public int count(Context contex) throws MalformedURLException, IOException,Exception;

    /**
     *
     * @param contex
     * @param detecteurIntrusion
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public void add(Context contex, DetecteurIntrusion detecteurIntrusion) throws MalformedURLException, IOException,Exception;

    /**
     *
     * @param contex
     * @param detecteurIntrusion
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public void remove(Context contex, DetecteurIntrusion detecteurIntrusion) throws MalformedURLException, IOException,Exception;
}
