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
    /**
     *
     * @param context
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws Exception
     */
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

    /**
     *
     * @param context
     * @param index
     * @param nbResultat
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws Exception
     */
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

    /**
     *
     * @param context
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public int count(Context context) throws MalformedURLException, IOException, Exception {
        Integer ret = null;
        if (context != null) {
            if (context instanceof Context) {
                    ret = this.detecteurIntrusionSrv.count(this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return ret;
    }

    /**
     *
     * @param context
     * @param detecteurIntrusion
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public void add(Context context, DetecteurIntrusion detecteurIntrusion) throws MalformedURLException, IOException, Exception {
        if ((context != null)&&(detecteurIntrusion!=null)) {
            if ((context instanceof Context)&&(detecteurIntrusion instanceof DetecteurIntrusion)) {
                    this.detecteurIntrusionSrv.add(this.getRessource(context),detecteurIntrusion);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }

    /**
     *
     * @param context
     * @param detecteurIntrusion
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public void remove(Context context, DetecteurIntrusion detecteurIntrusion) throws MalformedURLException, IOException, Exception {
        if ((context != null)&&(detecteurIntrusion!=null)) {
            if ((context instanceof Context)&&(detecteurIntrusion instanceof DetecteurIntrusion)) {
                    this.detecteurIntrusionSrv.remove(this.getRessource(context),detecteurIntrusion);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }

}
