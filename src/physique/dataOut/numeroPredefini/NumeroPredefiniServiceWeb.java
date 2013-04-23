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
    public NumeroPredefinis getAll(Ressource ressource) throws MalformedURLException, IOException, ParserConfigurationException, SAXException;

    public int count(Ressource ressource) throws MalformedURLException, IOException;

    public NumeroPredefinis getAll(Ressource ressource, int index, int nbResultat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException;

    public void add(Ressource ressource, String numero) throws MalformedURLException, IOException;

    public void remove(Ressource ressource, String numero) throws MalformedURLException, IOException;
}
