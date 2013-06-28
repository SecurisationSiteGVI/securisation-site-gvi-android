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
public class EvenementServiceWebJsonImpl implements physique.dataOut.evenement.EvenementServiceWeb {

    public List<Evenement> getAll(Ressource ressource) throws ParserConfigurationException, SAXException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Evenement> getAll(Ressource ressource, int index, int nbResultat) throws ParserConfigurationException, SAXException, IOException {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int count(Ressource ressource) throws IOException, MalformedURLException {
        return 20;
    }

    public Evenement getById(Ressource ressource, Long id) throws MalformedURLException, IOException, SAXException, ParseException, ParserConfigurationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
