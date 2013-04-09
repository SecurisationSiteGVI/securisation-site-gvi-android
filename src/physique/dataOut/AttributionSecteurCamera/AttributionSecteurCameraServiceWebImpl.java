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
import physique.dataOut.AttributionSecteurCamera.rest.RESTAttributionSecteurCameraAttribuer;
import physique.dataOut.AttributionSecteurCamera.rest.RESTAttributionSecteurCameraDesattribuer;

/**
 *
 * @author damien
 */
public class AttributionSecteurCameraServiceWebImpl implements AttributionSecteurCameraServiceWeb{

    public void attribuer(Ressource ressource, Secteur secteur, Camera camera) throws MalformedURLException, IOException, RuntimeException {
        RESTAttributionSecteurCameraAttribuer.execute(ressource, secteur, camera);
    }

    public void desattribuer(Ressource ressource, Secteur secteur, Camera camera) throws MalformedURLException, IOException, RuntimeException {
        RESTAttributionSecteurCameraDesattribuer.execute(ressource, secteur, camera);
    }
    
}
