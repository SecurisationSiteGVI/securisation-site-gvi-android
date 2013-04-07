/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Evenement;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.evenement.EvenementServiceWeb;
import physique.dataOut.PhysiqueDataOutFactory;

/**
 *
 * @author damien
 */
public class EvenementServiceImpl implements EvenementService {

    private EvenementServiceWeb evenementSrv = PhysiqueDataOutFactory.getEvenementServiceWeb();

    public List<Evenement> getAll(Context context) throws ParserConfigurationException, SAXException, IOException, Exception  {
        List<Evenement> b = null;
        if (context != null) {
            if (context instanceof Context) {
                b = evenementSrv.getAll(this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public List<Evenement> getAll(Context context, int index, int nbResultat) throws ParserConfigurationException, SAXException, IOException, Exception  {
        List<Evenement> b = null;
        if (context != null) {
            if (context instanceof Context) {
                b = evenementSrv.getAll(this.getRessource(context), index, nbResultat);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public int count(Context context) throws IOException, Exception  {
        int b = 0;
        if (context != null) {
            if (context instanceof Context) {
                b = evenementSrv.count(this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public Evenement getById(Context context, Long id) throws MalformedURLException, IOException, SAXException, ParseException, ParserConfigurationException, Exception  {
        Evenement b = null;
        if ((context != null)&&(id!=null)) {
            if ((context instanceof Context)&&(id instanceof Long)) {
                b = evenementSrv.getById(this.getRessource(context), id);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }
    private Ressource getRessource(Context c) throws Exception {
        RessourcesServiceDataIn ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(c);
        Ressource ressource = null;
        ressource = ressourcesSrv.getRessource();
        return ressource;
    }
}
