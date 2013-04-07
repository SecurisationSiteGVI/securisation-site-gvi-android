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
import metier.entitys.Badge;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.badge.BadgeServiceWeb;
import physique.dataOut.PhysiqueDataOutFactory;

/**
 *
 * @author damien
 */
public class BadgeServiceImpl implements BadgeService {

    private BadgeServiceWeb badgeSrv = PhysiqueDataOutFactory.getBadgeServiceWeb();

    public List<Badge> getAll(Context context) throws ParserConfigurationException, SAXException, IOException, Exception  {
        List<Badge> b = null;
        if (context != null) {
            if (context instanceof Context) {
                b = badgeSrv.getAll(this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public List<Badge> getAll(Context context, int index, int nbResultat) throws ParserConfigurationException, SAXException, IOException, Exception  {
        List<Badge> b = null;
        if (context != null) {
            if (context instanceof Context) {
                b = badgeSrv.getAll(this.getRessource(context), index, nbResultat);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public int count(Context context) throws MalformedURLException, IOException, Exception  {
        Integer count = null;
        if (context != null) {
            if (context instanceof Context) {
                count = badgeSrv.count( this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return count;
    }

    public boolean remove(Context c, Badge badge) throws MalformedURLException, IOException, Exception  {
        Boolean ret = null;
        if (c != null) {
            if (c instanceof Context) {
                ret = badgeSrv.remove( this.getRessource(c), badge);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return ret;
    }

    public boolean add(Context context, Badge badge) throws MalformedURLException, IOException, Exception  {
        Boolean ret = null;
        if (context != null) {
            if (context instanceof Context) {
                ret = badgeSrv.add( this.getRessource(context), badge);
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
