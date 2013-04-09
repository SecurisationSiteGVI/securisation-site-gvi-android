/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Ressource;
import metier.entitys.Secteur;
import org.xml.sax.SAXException;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.PhysiqueDataOutFactory;
import physique.dataOut.secteur.SecteurServiceWeb;

/**
 *
 * @author damien
 */
public class SecteurServiceImpl implements SecteurService {

    private SecteurServiceWeb secteurSrv = PhysiqueDataOutFactory.getSecteurServiceWeb();

    public boolean ajouter(Context context, Secteur secteur) throws IOException, Exception {
        Boolean b = null;
        if ((context != null)&&(secteur!=null)) {
            if ((context instanceof Context)&&(secteur instanceof Secteur)) {
                b = this.secteurSrv.ajouter(this.getRessource(context), secteur);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public List<Secteur> getAll(Context context, int index, int nbResutltat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException, Exception {
        List<Secteur> b = null;
        if (context != null) {
            if (context instanceof Context) {
                b = this.secteurSrv.getAll(this.getRessource(context), index, nbResutltat);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public boolean remove(Context context, Secteur secteur) throws MalformedURLException, IOException, Exception {
        Boolean b = null;
        if ((context != null)&&(secteur!=null)) {
            if ((context instanceof Context)&&(secteur instanceof Secteur)) {
                b = this.secteurSrv.remove( this.getRessource(context), secteur);
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

    public int count(Context context) throws MalformedURLException, IOException, Exception {
        Integer b = null;
        if ((context != null)) {
            if ((context instanceof Context)) {
                b = this.secteurSrv.count(this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }
}
