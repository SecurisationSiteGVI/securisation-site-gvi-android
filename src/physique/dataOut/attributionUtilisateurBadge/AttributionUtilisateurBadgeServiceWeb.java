/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.attributionUtilisateurBadge;

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
public interface AttributionUtilisateurBadgeServiceWeb {
    public List<Badge> getBadgesNotAssign( Ressource ressource, int debut, int nbResult)throws ParserConfigurationException, SAXException, IOException;
    public List<Badge> getBadgesNotAssignByNumero( Ressource ressource, String numero,int debut, int nbResult)throws ParserConfigurationException, SAXException, IOException;
    public List<Utilisateur> getUtilisateurNotAssign( Ressource ressource,int debut,int nbResult)throws ParseException, ParserConfigurationException, SAXException, IOException, MalformedURLException;
    public List<Utilisateur> getUtilisateurNotAssignByNom( Ressource ressource,String nom,int debut,int nbResult)throws MalformedURLException, IOException, ParserConfigurationException, SAXException, ParseException;
    public boolean attribuer( Ressource ressource, Utilisateur utilisateur, Badge badge)throws MalformedURLException, IOException;
}
