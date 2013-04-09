/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.attributionSecteurDetecteurIntrusion;

import java.io.IOException;
import java.net.MalformedURLException;
import metier.entitys.BorneAcces;
import metier.entitys.DetecteurIntrusion;
import metier.entitys.Ressource;
import metier.entitys.Secteur;
import physique.dataOut.attributionSecteurDetecteurIntrusion.rest.RESTAttributionSecteurDetecteurIntrusionAttribuer;

/**
 *
 * @author damien
 */
public class AttributionSecteurDetecteurIntrusionServiceWebImpl implements AttributionSecteurDetecteurIntrusionServiceWeb{

    public void attribuer(Ressource ressource, Secteur secteur, DetecteurIntrusion detecteurIntrusion) throws MalformedURLException, IOException, RuntimeException {
        RESTAttributionSecteurDetecteurIntrusionAttribuer.execute(ressource, secteur, detecteurIntrusion);
    }

    public void desattribuer(Ressource ressource, Secteur secteur, DetecteurIntrusion  detecteurIntrusion) throws MalformedURLException, IOException, RuntimeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
