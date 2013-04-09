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
import metier.entitys.Badge;
import metier.entitys.Ressource;
import metier.entitys.Utilisateur;
import org.xml.sax.SAXException;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.PhysiqueDataOutFactory;
import physique.dataOut.attributionUtilisateurBadge.AttributionUtilisateurBadgeServiceWeb;

/**
 *
 * @author damien
 */
public class AttributionUtilisateurBadgeServiceImpl implements AttributionUtilisateurBadgeService {


    private AttributionUtilisateurBadgeServiceWeb attributionUtilisateurBadgeSrv = PhysiqueDataOutFactory.getAttributionUtilisateurBadgeSrv();
    public List<Badge> getBadgesNotAssign(Context context,  int debut, int nbResult)throws ParserConfigurationException, SAXException, IOException, Exception {
        List<Badge> b = null;
        if (context != null) {
            if (context instanceof Context) {
                b = this.attributionUtilisateurBadgeSrv.getBadgesNotAssign(this.getRessource(context), debut, nbResult);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public List<Badge> getBadgesNotAssignByNumero(Context context, String numero, int debut, int nbResult) throws ParserConfigurationException, SAXException, IOException, Exception{
        List<Badge> b = null;
        if (context != null) {
            if (context instanceof Context) {
                b = this.attributionUtilisateurBadgeSrv.getBadgesNotAssignByNumero(this.getRessource(context), numero, debut, nbResult);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public List<Utilisateur> getUtilisateurNotAssign(Context context,  int debut, int nbResult)throws ParseException, ParserConfigurationException, SAXException, IOException, MalformedURLException, Exception {
        List<Utilisateur> b = null;
        if (context != null) {
            if (context instanceof Context) {
                b = this.attributionUtilisateurBadgeSrv.getUtilisateurNotAssign(this.getRessource(context), debut, nbResult);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public List<Utilisateur> getUtilisateurNotAssignByNom(Context context,  String nom, int debut, int nbResult)throws MalformedURLException, IOException, ParserConfigurationException, SAXException, ParseException, Exception {
        List<Utilisateur> b = null;
        if (context != null) {
            if (context instanceof Context) {
                b = this.attributionUtilisateurBadgeSrv.getUtilisateurNotAssignByNom(this.getRessource(context), nom, debut, nbResult);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public boolean attribuer(Context context, Utilisateur utilisateur, Badge badge) throws MalformedURLException, IOException,Exception{
        Boolean b = null;
        if (context != null) {
            if (context instanceof Context) {
                    b = this.attributionUtilisateurBadgeSrv.attribuer(this.getRessource(context), utilisateur, badge);
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
