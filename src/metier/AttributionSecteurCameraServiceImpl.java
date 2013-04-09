/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import metier.entitys.Camera;
import metier.entitys.Ressource;
import metier.entitys.Secteur;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.AttributionSecteurCamera.AttributionSecteurCameraServiceWeb;
import physique.dataOut.PhysiqueDataOutFactory;

/**
 *
 * @author damien
 */
public class AttributionSecteurCameraServiceImpl implements AttributionSecteurCameraService{
    private AttributionSecteurCameraServiceWeb attributionSecteurCameraSrv = PhysiqueDataOutFactory.getAttributionSecteurCameraSrv();
    public void attribuer(Context context, Secteur secteur, Camera camera) throws MalformedURLException, IOException, RuntimeException, Exception {
         if ((context != null)&&(secteur != null)&&(camera != null)) {
            if ((context instanceof Context)&&(secteur instanceof Secteur)&&(camera instanceof Camera)) {
                this.attributionSecteurCameraSrv.attribuer(this.getRessource(context), secteur, camera);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }

    public void desattribuer(Context context, Secteur secteur, Camera camera) throws MalformedURLException, IOException, RuntimeException, Exception {
        if ((context != null)&&(secteur != null)&&(camera != null)) {
            if ((context instanceof Context)&&(secteur instanceof Secteur)&&(camera instanceof Camera)) {
                this.attributionSecteurCameraSrv.desattribuer(this.getRessource(context), secteur, camera);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }
    private Ressource getRessource(Context c) throws Exception {
        RessourcesServiceDataIn ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(c);
        Ressource ressource = null;
        ressource = ressourcesSrv.getRessource();
        return ressource;
    }
}
