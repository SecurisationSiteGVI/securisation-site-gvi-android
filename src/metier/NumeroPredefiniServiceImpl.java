/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.NumeroPredefinis;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.PhysiqueDataOutFactory;
import physique.dataOut.numeroPredefini.NumeroPredefiniServiceWeb;

/**
 *
 * @author damien
 */
public class NumeroPredefiniServiceImpl implements NumeroPredefiniService{
    private NumeroPredefiniServiceWeb numeroPredefiniSrv = PhysiqueDataOutFactory.getNumeroPredefiniServiceWeb();
    public NumeroPredefinis getAll(Context context) throws MalformedURLException, IOException, ParserConfigurationException, SAXException, Exception {
        NumeroPredefinis ret = null;
        if (context != null) {
            if (context instanceof Context) {
                ret = numeroPredefiniSrv.getAll(this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return ret;
    }

    public NumeroPredefinis getAll(Context context, int index, int nbResultat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException, Exception {
        NumeroPredefinis ret = null;
        if (context != null) {
            if (context instanceof Context) {
                ret = numeroPredefiniSrv.getAll(this.getRessource(context), index, nbResultat);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return ret;
    }

    public int count(Context context) throws MalformedURLException, IOException, Exception {
        Integer ret = null;
        if (context != null) {
            if (context instanceof Context) {
                ret = numeroPredefiniSrv.count(this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return ret;
    }

    public void add(Context context, String numero) throws MalformedURLException, IOException, Exception {
        if ((context != null)&&(numero!=null)) {
            if ((context instanceof Context)&&(numero instanceof String)) {
                 numeroPredefiniSrv.add(this.getRessource(context), numero);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }

    public void remove(Context context, String numero) throws MalformedURLException, IOException, Exception {
        if ((context != null)&&(numero!=null)) {
            if ((context instanceof Context)&&(numero instanceof String)) {
                 numeroPredefiniSrv.remove(this.getRessource(context), numero);
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
