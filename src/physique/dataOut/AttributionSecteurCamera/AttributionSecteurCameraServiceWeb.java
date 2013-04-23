/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.AttributionSecteurCamera;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.AttributionSecteurCamera;
import metier.entitys.Camera;
import metier.entitys.Ressource;
import metier.entitys.Secteur;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface AttributionSecteurCameraServiceWeb {
    /**
     *
     * @param ressource
     * @param secteur
     * @param camera
     * @throws MalformedURLException
     * @throws IOException
     * @throws RuntimeException
     */
    public void attribuer(Ressource ressource, Secteur secteur, Camera camera)throws MalformedURLException, IOException, RuntimeException;
    /**
     *
     * @param ressource
     * @param secteur
     * @param camera
     * @throws MalformedURLException
     * @throws IOException
     * @throws RuntimeException
     */
    public void desattribuer(Ressource ressource, Secteur secteur, Camera camera)throws MalformedURLException, IOException, RuntimeException;
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
    public AttributionSecteurCamera getBySecteur(Ressource ressource,Secteur secteur)throws SAXException, ParserConfigurationException, MalformedURLException, IOException;
}
