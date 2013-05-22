/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.authorisationAcces;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.AuthorisationAcces;
import metier.entitys.Ressource;
import metier.entitys.Secteur;
import metier.entitys.Utilisateur;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface AuthorisationAccesServiceWeb {
    public void add(Ressource ressource, AuthorisationAcces authorisationAcces)throws MalformedURLException, IOException;
    public void attacherSecteur(Ressource ressource, Utilisateur utilisateur, Secteur secteur)throws MalformedURLException, IOException;
    public void detacherSecteur(Ressource ressource, Utilisateur utilisateur, Secteur secteur)throws MalformedURLException, IOException;
    public void update(Ressource ressource, AuthorisationAcces authorisationAcces)throws MalformedURLException, IOException;
    public AuthorisationAcces getByUtilisateur(Ressource ressource,Utilisateur utilisateur)throws SAXException, ParserConfigurationException, MalformedURLException, IOException;
}
