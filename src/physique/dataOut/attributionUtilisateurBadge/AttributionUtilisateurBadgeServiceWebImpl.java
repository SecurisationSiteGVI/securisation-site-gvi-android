/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.attributionUtilisateurBadge;

import android.os.AsyncTask;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Badge;
import metier.entitys.Ressource;
import metier.entitys.Utilisateur;
import org.xml.sax.SAXException;
import physique.dataOut.attributionUtilisateurBadge.rest.RESTAttributionBadgeAttribuer;
import physique.dataOut.attributionUtilisateurBadge.rest.RESTAttributionBadgeGetBadgeNotAssign;
import physique.dataOut.attributionUtilisateurBadge.rest.RESTAttributionBadgeGetBadgeNotAssignByNumero;
import physique.dataOut.attributionUtilisateurBadge.rest.RESTAttributionBadgeGetUtilisateurNotAssign;
import physique.dataOut.attributionUtilisateurBadge.rest.RESTAttributionUtilisateurBadgeGetUtilisateurNotAssignByNom;

/**
 *
 * @author damien
 */
public class AttributionUtilisateurBadgeServiceWebImpl implements AttributionUtilisateurBadgeServiceWeb {

    public List<Badge> getBadgesNotAssign(Ressource ressource, int debut, int nbResult) throws ParserConfigurationException, SAXException, IOException {
        List<Badge> retour = (List<Badge>) RESTAttributionBadgeGetBadgeNotAssign.execute(ressource, debut, nbResult);
        return retour;
    }

    public List<Badge> getBadgesNotAssignByNumero(Ressource ressource, String numero, int debut, int nbResult)throws ParserConfigurationException, SAXException, IOException {
        List<Badge> retour = (List<Badge>)  RESTAttributionBadgeGetBadgeNotAssignByNumero.execute(ressource, debut, nbResult, numero);
        return retour;
    }

    public List<Utilisateur> getUtilisateurNotAssign(Ressource ressource, int debut, int nbResult) throws ParseException, ParserConfigurationException, SAXException, IOException, MalformedURLException{
        List<Utilisateur> retour = (List<Utilisateur>)  RESTAttributionBadgeGetUtilisateurNotAssign.execute(ressource, debut, nbResult);
        return retour;
    }

    public List<Utilisateur> getUtilisateurNotAssignByNom(Ressource ressource, String nom, int debut, int nbResult)throws MalformedURLException, IOException, ParserConfigurationException, SAXException, ParseException {
       List<Utilisateur> retour = (List<Utilisateur>)   RESTAttributionUtilisateurBadgeGetUtilisateurNotAssignByNom.execute(ressource, debut, nbResult, nom);
        return retour;
    }

    public boolean attribuer(Ressource ressource, Utilisateur utilisateur, Badge badge) throws MalformedURLException, IOException {
        Boolean retour = (Boolean) RESTAttributionBadgeAttribuer.execute(ressource, utilisateur, badge);
        return retour;
    }
}
