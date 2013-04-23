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
    /**
     *
     * @param ressource
     * @param debut
     * @param nbResult
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public List<Badge> getBadgesNotAssign( Ressource ressource, int debut, int nbResult)throws ParserConfigurationException, SAXException, IOException;
    /**
     *
     * @param ressource
     * @param numero
     * @param debut
     * @param nbResult
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public List<Badge> getBadgesNotAssignByNumero( Ressource ressource, String numero,int debut, int nbResult)throws ParserConfigurationException, SAXException, IOException;
    /**
     *
     * @param ressource
     * @param debut
     * @param nbResult
     * @return
     * @throws ParseException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws MalformedURLException
     */
    public List<Utilisateur> getUtilisateurNotAssign( Ressource ressource,int debut,int nbResult)throws ParseException, ParserConfigurationException, SAXException, IOException, MalformedURLException;
    /**
     *
     * @param ressource
     * @param nom
     * @param debut
     * @param nbResult
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws ParseException
     */
    public List<Utilisateur> getUtilisateurNotAssignByNom( Ressource ressource,String nom,int debut,int nbResult)throws MalformedURLException, IOException, ParserConfigurationException, SAXException, ParseException;
    /**
     *
     * @param ressource
     * @param utilisateur
     * @param badge
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public boolean attribuer( Ressource ressource, Utilisateur utilisateur, Badge badge)throws MalformedURLException, IOException;
}
