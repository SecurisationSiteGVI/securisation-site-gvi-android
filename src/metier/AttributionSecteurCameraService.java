/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.AttributionSecteurCamera;
import metier.entitys.Camera;
import metier.entitys.Secteur;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface AttributionSecteurCameraService {
    public void attribuer(Context context, Secteur secteur, Camera camera)throws MalformedURLException, IOException, RuntimeException,Exception;
    public void desattribuer(Context context, Secteur secteur, Camera camera)throws MalformedURLException, IOException, RuntimeException,Exception;
    public AttributionSecteurCamera getBySecteur(Context context,Secteur secteur)throws SAXException, ParserConfigurationException, MalformedURLException, IOException,Exception;
}
