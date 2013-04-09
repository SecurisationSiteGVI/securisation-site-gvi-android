/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.detecteurIntrusion;

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
public interface DetecteurIntrusionServiceWeb {
    public List<DetecteurIntrusion> getAll(Ressource ressource) throws MalformedURLException, IOException, ParserConfigurationException, SAXException;

    public List<DetecteurIntrusion> getAll(Ressource ressource, int index, int nbResultat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException;
}
