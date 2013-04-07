/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.utilisateur;

import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import metier.entitys.Technicien;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Ressource;
import metier.entitys.Utilisateur;
import org.xml.sax.SAXException;
import physique.dataOut.utilisateur.rest.RESTUtilisateurAdd;
import physique.dataOut.utilisateur.rest.RESTUtilisateurAddTechniecien;
import physique.dataOut.utilisateur.rest.RESTUtilisateurCount;
import physique.dataOut.utilisateur.rest.RESTUtilisateurGetAll;
import physique.dataOut.utilisateur.rest.RESTUtilisateurGetAllByRange;
import physique.dataOut.utilisateur.rest.RESTUtilisateurGetById;
import physique.dataOut.utilisateur.rest.RESTUtilisateurLoginIsUse;
import physique.dataOut.utilisateur.rest.RESTUtilisateurRemove;
import physique.dataOut.utilisateur.rest.RESTUtilisateurUpdate;
import physique.dataOut.utilisateur.rest.RESTUtilisateurVerificationConnexion;

/**
 *
 * @author damien
 */
public class UtilisateurServiceWebImpl implements UtilisateurServiceWeb {

    @Override
    public List<Utilisateur> getAll(Ressource ressource) throws ParserConfigurationException, SAXException, IOException {
        List<Utilisateur> retour = (List<Utilisateur>) new RESTUtilisateurGetAll().execute(ressource);
        return retour;
    }

    @Override
    public boolean add(Utilisateur utilisateur, Ressource ressource) throws IOException {
        Boolean retour = (Boolean) new RESTUtilisateurAdd().execute(utilisateur, ressource);
        return retour;
    }

    @Override
    public boolean remove(Utilisateur utilisateur, Ressource ressource) throws MalformedURLException, IOException {
        Boolean retour = (Boolean) new RESTUtilisateurRemove().execute(utilisateur, ressource);
        return retour;
    }

    @Override
    public boolean update(Utilisateur utilisateur, Ressource ressource) throws MalformedURLException, IOException {
        Boolean retour = (Boolean) new RESTUtilisateurUpdate().execute(utilisateur, ressource);
        return retour;
    }

    @Override
    public boolean loginIsUse(String loginn, Ressource ressource) throws IOException {
        Boolean retour = (Boolean) new RESTUtilisateurLoginIsUse().execute(loginn, ressource);
        return retour;
    }

    @Override
    public Technicien verificationConnexion(Technicien utilisateur, Ressource ressource) throws Throwable, IOException, SSLPeerUnverifiedException, ConnectException, SAXException, ParserConfigurationException {
        Technicien technicien = (Technicien) new RESTUtilisateurVerificationConnexion().execute(utilisateur, ressource);
        return technicien;
    }

    @Override
    public List<Utilisateur> getAll(int from, int nbResut, Ressource ressource) throws SAXException, ParserConfigurationException, MalformedURLException, IOException {
        List<Utilisateur> utilisateurs = (List<Utilisateur>)  new RESTUtilisateurGetAllByRange().execute(from, nbResut, ressource);
        return utilisateurs;

    }

    public Utilisateur getById(Long id, Ressource ressource) throws SAXException, ParserConfigurationException, MalformedURLException, IOException {
         List<Utilisateur> utilisateurs = (List<Utilisateur>) new RESTUtilisateurGetById().execute(id, ressource);
        return utilisateurs.get(0);
    }

    public int count(Ressource ressource) throws MalformedURLException, IOException {
        Integer count = (Integer) new RESTUtilisateurCount().execute(ressource);
        return count;
    }

    public boolean addTechnicien(Technicien technicien, Ressource ressource) throws MalformedURLException, IOException {
         Boolean retour = (Boolean) new RESTUtilisateurAddTechniecien().execute(technicien, ressource);
        return retour;
    }
}
