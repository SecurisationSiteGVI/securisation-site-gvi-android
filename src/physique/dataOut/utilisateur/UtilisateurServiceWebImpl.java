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
import physique.dataOut.BoiteAOutils;
import physique.dataOut.BoiteAOutils.Path;
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
@Path(ressourceName = "utilisateur")
public class UtilisateurServiceWebImpl implements UtilisateurServiceWeb {

    @Override
    public List<Utilisateur> getAll(Ressource ressource) throws ParserConfigurationException, SAXException, IOException {
        List<Utilisateur> retour = (List<Utilisateur>) new RESTUtilisateurGetAll().execute(ressource);
        return retour;
    }

    /**
     *
     * @param utilisateur
     * @param ressource
     * @return
     * @throws IOException
     */
    @Override
    public boolean add(Utilisateur utilisateur, Ressource ressource) throws IOException {
        Boolean retour = (Boolean) new RESTUtilisateurAdd().execute(utilisateur, ressource);
        return retour;
    }

    /**
     *
     * @param utilisateur
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    @Override
    public boolean remove(Utilisateur utilisateur, Ressource ressource) throws MalformedURLException, IOException {
        Boolean retour = (Boolean) new RESTUtilisateurRemove().execute(utilisateur, ressource);
        return retour;
    }

    /**
     *
     * @param utilisateur
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    @Override
    public boolean update(Utilisateur utilisateur, Ressource ressource) throws MalformedURLException, IOException {
        Boolean retour = (Boolean) new RESTUtilisateurUpdate().execute(utilisateur, ressource);
        return retour;
    }

    /**
     *
     * @param loginn
     * @param ressource
     * @return
     * @throws IOException
     */
    @Override
    public boolean loginIsUse(String loginn, Ressource ressource) throws IOException {
        Boolean retour = (Boolean) new RESTUtilisateurLoginIsUse().execute(loginn, ressource);
        return retour;
    }

    /**
     *
     * @param utilisateur
     * @param ressource
     * @return
     * @throws Throwable
     * @throws IOException
     * @throws SSLPeerUnverifiedException
     * @throws ConnectException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    @Override
    public Technicien verificationConnexion(Technicien utilisateur, Ressource ressource) throws Throwable, IOException, SSLPeerUnverifiedException, ConnectException, SAXException, ParserConfigurationException {
        Technicien technicien = (Technicien) new RESTUtilisateurVerificationConnexion().execute(utilisateur, ressource);
        return technicien;
    }

    /**
     *
     * @param from
     * @param nbResut
     * @param ressource
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws MalformedURLException
     * @throws IOException
     */
    @Override
    public List<Utilisateur> getAll(int from, int nbResut, Ressource ressource) throws SAXException, ParserConfigurationException, MalformedURLException, IOException {
        List<Utilisateur> utilisateurs = (List<Utilisateur>)  new RESTUtilisateurGetAllByRange().execute(from, nbResut, ressource);
        return utilisateurs;

    }

    /**
     *
     * @param id
     * @param ressource
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws MalformedURLException
     * @throws IOException
     */
    public Utilisateur getById(Long id, Ressource ressource) throws SAXException, ParserConfigurationException, MalformedURLException, IOException {
         List<Utilisateur> utilisateurs = (List<Utilisateur>) new RESTUtilisateurGetById().execute(id, ressource);
        return utilisateurs.get(0);
    }

    /**
     *
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public int count(Ressource ressource) throws MalformedURLException, IOException {
        Integer count = (Integer) new RESTUtilisateurCount().execute(ressource);
        return count;
    }

    /**
     *
     * @param technicien
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public boolean addTechnicien(Technicien technicien, Ressource ressource) throws MalformedURLException, IOException {
         Boolean retour = (Boolean) new RESTUtilisateurAddTechniecien().execute(technicien, ressource);
        return retour;
    }
}
