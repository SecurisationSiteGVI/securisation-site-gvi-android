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

/**
 *
 * @author damien
 */
public interface AttributionSecteurDetecteurIntrusionServiceWeb {
    public void attribuer(Ressource ressource, Secteur secteur, DetecteurIntrusion detecteurIntrusion)throws MalformedURLException, IOException, RuntimeException;
    public void desattribuer(Ressource ressource, Secteur secteur, DetecteurIntrusion detecteurIntrusion)throws MalformedURLException, IOException, RuntimeException;
}
