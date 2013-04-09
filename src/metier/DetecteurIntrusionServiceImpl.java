/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.DetecteurIntrusion;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.PhysiqueDataOutFactory;
import physique.dataOut.detecteurIntrusion.DetecteurIntrusionServiceWeb;

/**
 *
 * @author damien
 */
public class DetecteurIntrusionServiceImpl implements DetecteurIntrusionService{
    private DetecteurIntrusionServiceWeb detecteurIntrusionSrv = PhysiqueDataOutFactory.getDetecteurIntrusionServiceWeb();
    public List<DetecteurIntrusion> getAll(Context context) throws MalformedURLException, IOException, ParserConfigurationException, SAXException, Exception {
        List<DetecteurIntrusion> ret = null;
        if (context != null) {
            if (context instanceof Context) {
                    ret = this.detecteurIntrusionSrv.getAll(this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return ret;
    }

    public List<DetecteurIntrusion> getAll(Context context, int index, int nbResultat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException, Exception {
        List<DetecteurIntrusion> ret = null;
        if (context != null) {
            if (context instanceof Context) {
                    ret = this.detecteurIntrusionSrv.getAll(this.getRessource(context), index, nbResultat);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return ret;
    }
    private Ressource getRessource(Context c) throws Exception {
        RessourcesServiceDataIn ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(c);
        Ressource ressource = null;
        ressource = ressourcesSrv.getRessource();
        return ressource;
    }
}
