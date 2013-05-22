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
    /**
     *
     * @param context
     * @param debut
     * @param nbResult
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws Exception
     */
    public List<Badge> getBadgesNotAssign(Context context,  int debut, int nbResult)throws ParserConfigurationException, SAXException, IOException,Exception;
    /**
     *
     * @param context
     * @param numero
     * @param debut
     * @param nbResult
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws Exception
     */
    public List<Badge> getBadgesNotAssignByNumero(Context context,  String numero,int debut, int nbResult)throws ParserConfigurationException, SAXException, IOException, Exception;
    /**
     *
     * @param context
     * @param debut
     * @param nbResult
     * @return
     * @throws ParseException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws MalformedURLException
     * @throws Exception
     */
    public List<Utilisateur> getUtilisateurNotAssign(Context context, int debut,int nbResult)throws ParseException, ParserConfigurationException, SAXException, IOException, MalformedURLException, Exception ;
    /**
     *
     * @param context
     * @param nom
     * @param debut
     * @param nbResult
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws ParseException
     * @throws Exception
     */
    public List<Utilisateur> getUtilisateurNotAssignByNom(Context context, String nom,int debut,int nbResult)throws MalformedURLException, IOException, ParserConfigurationException, SAXException, ParseException, Exception;
    /**
     *
     * @param context
     * @param utilisateur
     * @param badge
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public boolean attribuer(Context context, Utilisateur utilisateur, Badge badge)throws MalformedURLException, IOException,Exception;
}
