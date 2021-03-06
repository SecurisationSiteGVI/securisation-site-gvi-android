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

    /**
     *
     * @param ressource
     * @param secteur
     * @return
     * @throws IOException
     */
    public boolean ajouter(Ressource ressource, Secteur secteur) throws IOException;

    /**
     *
     * @param ressource
     * @param index
     * @param nbResutltat
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public List<Secteur> getAll(Ressource ressource, int index, int nbResutltat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException;

    /**
     *
     * @param ressource
     * @param secteur
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public boolean remove(Ressource ressource, Secteur secteur) throws MalformedURLException, IOException;
    
    /**
     *
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public int count(Ressource ressource) throws MalformedURLException, IOException;
    
}
