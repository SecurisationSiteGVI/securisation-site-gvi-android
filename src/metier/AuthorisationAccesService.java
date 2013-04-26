/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.AuthorisationAcces;
import metier.entitys.Secteur;
import metier.entitys.Utilisateur;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface AuthorisationAccesService {
    public void add(Context context, AuthorisationAcces authorisationAcces)throws MalformedURLException, IOException,Exception;
    public void attacherSecteur(Context context, Utilisateur utilisateur, Secteur secteur)throws MalformedURLException, IOException,Exception;
    public void detacherSecteur(Context context, Utilisateur utilisateur, Secteur secteur)throws MalformedURLException, IOException,Exception;
    public void update(Context context, AuthorisationAcces authorisationAcces)throws MalformedURLException, IOException,Exception;
    public AuthorisationAcces getByUtilisateur(Context context, Utilisateur utilisateur) throws SAXException, ParserConfigurationException, MalformedURLException, IOException, Exception;
}
