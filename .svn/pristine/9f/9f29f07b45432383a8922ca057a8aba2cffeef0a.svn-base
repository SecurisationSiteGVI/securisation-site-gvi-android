/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Evenement;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface EvenementService {

    /**
     *
     * @param context
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws Exception
     */
    public List<Evenement> getAll(Context context) throws ParserConfigurationException, SAXException, IOException, Exception;

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
    public List<Evenement> getAll(Context context, int index, int nbResultat) throws ParserConfigurationException, SAXException, IOException, Exception;

    /**
     *
     * @param c
     * @return
     * @throws IOException
     * @throws Exception
     */
    public int count(Context c) throws  IOException, Exception;

    /**
     *
     * @param context
     * @param id
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws SAXException
     * @throws ParseException
     * @throws ParserConfigurationException
     * @throws Exception
     */
    public Evenement getById(Context context, Long id) throws MalformedURLException, IOException, SAXException, ParseException, ParserConfigurationException, Exception;
}
