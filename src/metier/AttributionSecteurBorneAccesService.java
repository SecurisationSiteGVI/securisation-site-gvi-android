/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.AttributionSecteurBorneAcces;
import metier.entitys.BorneAcces;
import metier.entitys.Secteur;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface AttributionSecteurBorneAccesService {
    public void attribuer(Context context, Secteur secteur, BorneAcces borneAcces)throws MalformedURLException, IOException, RuntimeException,Exception;
    public void desattribuer(Context context, Secteur secteur, BorneAcces borneAcces)throws MalformedURLException, IOException, RuntimeException,Exception;
    public AttributionSecteurBorneAcces getBySecteur(Context context, Secteur secteur) throws SAXException, ParserConfigurationException, MalformedURLException, IOException, Exception;
}
