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

/**
 *
 * @author damien
 */
public interface AttributionUtilisateurBadgeService {
    public List<Badge> getBadgesNotAssign(Context context,  int debut, int nbResult)throws ParserConfigurationException, SAXException, IOException,Exception;
    public List<Badge> getBadgesNotAssignByNumero(Context context,  String numero,int debut, int nbResult)throws ParserConfigurationException, SAXException, IOException, Exception;
    public List<Utilisateur> getUtilisateurNotAssign(Context context, int debut,int nbResult)throws ParseException, ParserConfigurationException, SAXException, IOException, MalformedURLException, Exception ;
    public List<Utilisateur> getUtilisateurNotAssignByNom(Context context, String nom,int debut,int nbResult)throws MalformedURLException, IOException, ParserConfigurationException, SAXException, ParseException, Exception;
    public boolean attribuer(Context context, Utilisateur utilisateur, Badge badge)throws MalformedURLException, IOException,Exception;
}
