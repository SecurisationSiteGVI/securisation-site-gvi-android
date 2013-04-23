/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.NumeroPredefinis;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface NumeroPredefiniService {
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
    public NumeroPredefinis getAll(Context context)throws MalformedURLException, IOException, ParserConfigurationException, SAXException,Exception;

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
    public NumeroPredefinis getAll(Context context, int index, int nbResultat)throws MalformedURLException, IOException, ParserConfigurationException, SAXException,Exception ;
    
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
     * @param numero
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public void add(Context contex, String  numero) throws MalformedURLException, IOException,Exception;

    /**
     *
     * @param contex
     * @param numero
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public void remove(Context contex, String  numero) throws MalformedURLException, IOException,Exception;
}
