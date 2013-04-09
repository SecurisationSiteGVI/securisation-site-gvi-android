/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.AttributionSecteurCamera;

import java.io.IOException;
import java.net.MalformedURLException;
import metier.entitys.Camera;
import metier.entitys.Ressource;
import metier.entitys.Secteur;

/**
 *
 * @author damien
 */
public interface AttributionSecteurCameraServiceWeb {
    public void attribuer(Ressource ressource, Secteur secteur, Camera camera)throws MalformedURLException, IOException, RuntimeException;
    public void desattribuer(Ressource ressource, Secteur secteur, Camera camera)throws MalformedURLException, IOException, RuntimeException;
}
