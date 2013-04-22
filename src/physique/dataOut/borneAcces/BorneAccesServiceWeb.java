/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.borneAcces;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.BorneAcces;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface BorneAccesServiceWeb {
    public List<BorneAcces> getAll(Ressource ressource)throws MalformedURLException, IOException, ParserConfigurationException, SAXException;

    public List<BorneAcces> getAll(Ressource ressource, int index, int nbResultat)throws MalformedURLException, IOException, ParserConfigurationException, SAXException ;
    public int count(Ressource ressource) throws MalformedURLException, IOException;

    public void add(Ressource ressource, BorneAcces borneAcces) throws MalformedURLException, IOException;

    public void remove(Ressource ressource, BorneAcces borneAcces) throws MalformedURLException, IOException;
}
