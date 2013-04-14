/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.attributionSecteurBorneAcces;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.AttributionSecteurBorneAcces;
import metier.entitys.BorneAcces;
import metier.entitys.Ressource;
import metier.entitys.Secteur;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface AttributionSecteurBorneAccesServiceWeb {
    public void attribuer(Ressource ressource, Secteur secteur, BorneAcces borneAcces)throws MalformedURLException, IOException, RuntimeException;
    public void desattribuer(Ressource ressource, Secteur secteur, BorneAcces borneAcces)throws MalformedURLException, IOException, RuntimeException;
    public AttributionSecteurBorneAcces getBySecteur(Ressource ressource, Secteur secteur) throws SAXException, ParserConfigurationException, MalformedURLException, IOException;
}
