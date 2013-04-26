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
import metier.entitys.Ressource;
import metier.entitys.Secteur;
import metier.entitys.Utilisateur;
import org.xml.sax.SAXException;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.PhysiqueDataOutFactory;
import physique.dataOut.authorisationAcces.AuthorisationAccesServiceWeb;

/**
 *
 * @author damien
 */
public class AuthorisationAccesServiceImpl implements AuthorisationAccesService{
    private AuthorisationAccesServiceWeb authorisationAccesSrv = PhysiqueDataOutFactory.getAuthorisationAccesServiceWeb();
    public void add(Context context, AuthorisationAcces authorisationAcces) throws MalformedURLException, IOException, Exception {
         if ((context != null)&&(authorisationAcces!=null)) {
            if ((context instanceof Context)&&(authorisationAcces instanceof AuthorisationAcces)) {
                authorisationAccesSrv.add(this.getRessource(context), authorisationAcces);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }

    public void attacherSecteur(Context context, Utilisateur utilisateur, Secteur secteur) throws MalformedURLException, IOException, Exception {
        if ((context != null)&&(utilisateur!=null)&&(secteur!=null)) {
            if ((context instanceof Context)&&(utilisateur instanceof Utilisateur)&&(secteur instanceof Secteur)) {
                authorisationAccesSrv.attacherSecteur(this.getRessource(context), utilisateur,secteur);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }

    public void detacherSecteur(Context context, Utilisateur utilisateur, Secteur secteur) throws MalformedURLException, IOException, Exception {
        if ((context != null)&&(utilisateur!=null)&&(secteur!=null)) {
            if ((context instanceof Context)&&(utilisateur instanceof Utilisateur)&&(secteur instanceof Secteur)) {
                authorisationAccesSrv.detacherSecteur(this.getRessource(context), utilisateur,secteur);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }

    public void update(Context context, AuthorisationAcces authorisationAcces) throws MalformedURLException, IOException, Exception {
        if ((context != null)&&(authorisationAcces!=null)) {
            if ((context instanceof Context)&&(authorisationAcces instanceof AuthorisationAcces)) {
                authorisationAccesSrv.update(this.getRessource(context), authorisationAcces);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
    }

    public AuthorisationAcces getByUtilisateur(Context context, Utilisateur utilisateur) throws SAXException, ParserConfigurationException, MalformedURLException, IOException, Exception {
        AuthorisationAcces authorisationAcces=null;
        if ((context != null)&&(utilisateur!=null)) {
            if ((context instanceof Context)&&(utilisateur instanceof Utilisateur)) {
                authorisationAcces= authorisationAccesSrv.getByUtilisateur(this.getRessource(context), utilisateur);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return authorisationAcces;
    }
    private Ressource getRessource(Context c) throws Exception {
        RessourcesServiceDataIn ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(c);
        Ressource ressource = null;
        ressource = ressourcesSrv.getRessource();
        return ressource;
    }
}
