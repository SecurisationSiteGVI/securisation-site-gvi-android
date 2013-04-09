/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.secteur;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Ressource;
import metier.entitys.Secteur;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface SecteurServiceWeb {

    public boolean ajouter(Ressource ressource, Secteur secteur) throws IOException;

    public List<Secteur> getAll(Ressource ressource, int index, int nbResutltat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException;

    public boolean remove(Ressource ressource, Secteur secteur) throws MalformedURLException, IOException;
    
    public int count(Ressource ressource) throws MalformedURLException, IOException;
    
}
