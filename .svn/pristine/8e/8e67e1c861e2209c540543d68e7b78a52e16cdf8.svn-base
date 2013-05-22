/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.evenement;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Evenement;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface EvenementServiceWeb {

    /**
     *
     * @param ressource
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public List<Evenement> getAll(Ressource ressource) throws ParserConfigurationException, SAXException, IOException;

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
    public List<Evenement> getAll(Ressource ressource, int index, int nbResultat) throws ParserConfigurationException, SAXException, IOException;

    /**
     *
     * @param ressource
     * @return
     * @throws IOException
     * @throws MalformedURLException
     */
    public int count(Ressource ressource) throws IOException, MalformedURLException;

    /**
     *
     * @param ressource
     * @param id
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws SAXException
     * @throws ParseException
     * @throws ParserConfigurationException
     */
    public Evenement getById(Ressource ressource, Long id) throws MalformedURLException, IOException, SAXException, ParseException, ParserConfigurationException;
}
