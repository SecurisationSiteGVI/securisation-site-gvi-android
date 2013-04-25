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
import physique.dataOut.authorisationAcces.rest.RESTAuthorisationAccesAdd;
import physique.dataOut.authorisationAcces.rest.RESTAuthorisationAccesGetByUtilisateur;

/**
 *
 * @author damien
 */
public class AuthorisationAccesServiceWebImpl implements AuthorisationAccesServiceWeb{

    public void add(Ressource ressource, AuthorisationAcces authorisationAcces) throws MalformedURLException, IOException{
        RESTAuthorisationAccesAdd.execute(ressource, authorisationAcces);
    }

    public void attacherSecteur(Ressource ressource, Utilisateur utilisateur, Secteur secteur) {
        
    }

    public void detacherSecteur(Ressource ressource, Utilisateur utilisateur, Secteur secteur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update(Ressource ressource, AuthorisationAcces authorisationAcces) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public AuthorisationAcces getByUtilisateur(Ressource ressource, Utilisateur utilisateur) throws SAXException, ParserConfigurationException, MalformedURLException, IOException{
       return RESTAuthorisationAccesGetByUtilisateur.execute(ressource, utilisateur);
    }
}
