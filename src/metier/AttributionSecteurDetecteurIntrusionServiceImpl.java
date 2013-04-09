/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import metier.entitys.DetecteurIntrusion;
import metier.entitys.Ressource;
import metier.entitys.Secteur;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.PhysiqueDataOutFactory;
import physique.dataOut.attributionSecteurDetecteurIntrusion.AttributionSecteurDetecteurIntrusionServiceWeb;

/**
 *
 * @author damien
 */
public class AttributionSecteurDetecteurIntrusionServiceImpl implements AttributionSecteurDetecteurIntrusionService {

    private AttributionSecteurDetecteurIntrusionServiceWeb attributionSecteurDetecteurIntrusionSrv = PhysiqueDataOutFactory.getAttributionSecteurDetecteurIntrusionSrv();

    public void attribuer(Context context, Secteur secteur, DetecteurIntrusion detecteurIntrusion) throws MalformedURLException, IOException, RuntimeException, Exception {
        if ((context != null) && (secteur != null) && (detecteurIntrusion != null)) {
            if ((context instanceof Context) && (secteur instanceof Secteur) && (detecteurIntrusion instanceof DetecteurIntrusion)) {
                this.attributionSecteurDetecteurIntrusionSrv.attribuer(this.getRessource(context), secteur, detecteurIntrusion);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }

    public void desattribuer(Context context, Secteur secteur, DetecteurIntrusion detecteurIntrusion) throws MalformedURLException, IOException, RuntimeException, Exception {
        if ((context != null) && (secteur != null) && (detecteurIntrusion != null)) {
            if ((context instanceof Context) && (secteur instanceof Secteur) && (detecteurIntrusion instanceof DetecteurIntrusion)) {
                this.attributionSecteurDetecteurIntrusionSrv.desattribuer(this.getRessource(context), secteur, detecteurIntrusion);
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
