/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.numeroPredefini;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.NumeroPredefinis;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface NumeroPredefiniServiceWeb {
    /**
     *
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public NumeroPredefinis getAll(Ressource ressource) throws MalformedURLException, IOException, ParserConfigurationException, SAXException;

    /**
     *
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public int count(Ressource ressource) throws MalformedURLException, IOException;

    /**
     *
     * @param ressource
     * @param index
     * @param nbResultat
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public NumeroPredefinis getAll(Ressource ressource, int index, int nbResultat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException;

    /**
     *
     * @param ressource
     * @param numero
     * @throws MalformedURLException
     * @throws IOException
     */
    public void add(Ressource ressource, String numero) throws MalformedURLException, IOException;

    /**
     *
     * @param ressource
     * @param numero
     * @throws MalformedURLException
     * @throws IOException
     */
    public void remove(Ressource ressource, String numero) throws MalformedURLException, IOException;
}
