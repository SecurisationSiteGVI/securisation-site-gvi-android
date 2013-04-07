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

    public List<Evenement> getAll(Ressource ressource) throws ParserConfigurationException, SAXException, IOException;

    public List<Evenement> getAll(Ressource ressource, int index, int nbResultat) throws ParserConfigurationException, SAXException, IOException;

    public int count(Ressource ressource) throws IOException, MalformedURLException;

    public Evenement getById(Ressource ressource, Long id) throws MalformedURLException, IOException, SAXException, ParseException, ParserConfigurationException;
}
