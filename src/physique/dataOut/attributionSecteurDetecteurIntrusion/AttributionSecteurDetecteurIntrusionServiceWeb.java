/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.attributionSecteurDetecteurIntrusion;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.AttributionSecteurDetecteurIntrusion;
import metier.entitys.DetecteurIntrusion;
import metier.entitys.Ressource;
import metier.entitys.Secteur;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface AttributionSecteurDetecteurIntrusionServiceWeb {
    /**
     *
     * @param ressource
     * @param secteur
     * @param detecteurIntrusion
     * @throws MalformedURLException
     * @throws IOException
     * @throws RuntimeException
     */
    public void attribuer(Ressource ressource, Secteur secteur, DetecteurIntrusion detecteurIntrusion)throws MalformedURLException, IOException, RuntimeException;
    /**
     *
     * @param ressource
     * @param secteur
     * @param detecteurIntrusion
     * @throws MalformedURLException
     * @throws IOException
     * @throws RuntimeException
     */
    public void desattribuer(Ressource ressource, Secteur secteur, DetecteurIntrusion detecteurIntrusion)throws MalformedURLException, IOException, RuntimeException;
    /**
     *
     * @param ressource
     * @param secteur
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws MalformedURLException
     * @throws IOException
     */
    public AttributionSecteurDetecteurIntrusion getBySecteur(Ressource ressource, Secteur secteur) throws SAXException, ParserConfigurationException, MalformedURLException, IOException;
}
