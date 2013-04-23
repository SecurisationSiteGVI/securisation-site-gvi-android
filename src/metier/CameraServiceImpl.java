/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Camera;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.PhysiqueDataOutFactory;
import physique.dataOut.camera.CameraServiceWeb;

/**
 *
 * @author damien
 */
public class CameraServiceImpl implements CameraService{
    private CameraServiceWeb cameraSrv = PhysiqueDataOutFactory.getCameraServiceWeb();
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
    public List<Camera> getAll(Context context) throws MalformedURLException, IOException, ParserConfigurationException, SAXException, Exception {
        List<Camera> ret = null;
        if (context != null) {
            if (context instanceof Context) {
                    ret = this.cameraSrv.getAll(this.getRessource(context));
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
    public List<Camera> getAll(Context context, int index, int nbResultat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException, Exception {
        List<Camera> ret = null;
        if (context != null) {
            if (context instanceof Context) {
               
                    ret = this.cameraSrv.getAll(this.getRessource(context), index, nbResultat);
                
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
                    ret = this.cameraSrv.count(this.getRessource(context));
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
     * @param camera
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public void add(Context context, Camera camera) throws MalformedURLException, IOException, Exception {
        if ((context != null)&&(camera!=null)) {
            if ((context instanceof Context)&&(camera instanceof Camera)) {
                    this.cameraSrv.add(this.getRessource(context),camera);
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
     * @param camera
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public void remove(Context context, Camera camera) throws MalformedURLException, IOException, Exception {
        if ((context != null)&&(camera!=null)) {
            if ((context instanceof Context)&&(camera instanceof Camera)) {
                    this.cameraSrv.remove(this.getRessource(context),camera);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }
    
}
