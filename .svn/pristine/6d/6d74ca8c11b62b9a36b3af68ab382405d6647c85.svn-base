/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Ressource;
import metier.entitys.Technicien;
import metier.entitys.Utilisateur;
import org.xml.sax.SAXException;
import physique.dataIn.PhysiqueDataInFactory;
import physique.dataIn.RessourcesServiceDataIn;
import physique.dataOut.PhysiqueDataOutFactory;
import physique.dataOut.utilisateur.UtilisateurServiceWeb;

/**
 *
 * @author damien
 */
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurServiceWeb utilisateurSrv = PhysiqueDataOutFactory.getPersonneClientServiceWeb();

    /**
     *
     * @param context
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws Exception
     */
    public List<Utilisateur> getAll(Context context) throws ParserConfigurationException, SAXException, IOException, Exception  {
        return this.utilisateurSrv.getAll(this.getRessource(context));
    }

    /**
     *
     * @param from
     * @param nbResut
     * @param context
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public List<Utilisateur> getAll(int from, int nbResut, Context context) throws SAXException, ParserConfigurationException, MalformedURLException, IOException, Exception  {
        return this.utilisateurSrv.getAll(from, nbResut, this.getRessource(context));
    }

    /**
     *
     * @param utilisateur
     * @param context
     * @return
     * @throws IOException
     * @throws Exception
     */
    public boolean add(Utilisateur utilisateur, Context context) throws IOException, Exception  {
        Boolean b = false;
        if ((utilisateur != null) && (context != null)) {
            if ((utilisateur instanceof Utilisateur) && (context instanceof Context)) {
                b = utilisateurSrv.add(utilisateur, this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    /**
     *
     * @param utilisateur
     * @param context
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public boolean addTechnicien(Technicien utilisateur, Context context) throws MalformedURLException, IOException, Exception {
        Boolean b = false;
        if (utilisateur != null) {
            if (utilisateur instanceof Utilisateur) {
                b = utilisateurSrv.addTechnicien(utilisateur, this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    /**
     *
     * @param utilisateur
     * @param context
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public boolean remove(Utilisateur utilisateur, Context context) throws MalformedURLException, IOException, Exception  {
        Boolean b = false;
        if (utilisateur != null) {
            if (utilisateur instanceof Utilisateur) {
                b = utilisateurSrv.remove(utilisateur, this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    /**
     *
     * @param utilisateur
     * @param context
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public boolean update(Utilisateur utilisateur, Context context) throws MalformedURLException, IOException, Exception  {
        Boolean b = false;
        if (utilisateur != null) {
            if (utilisateur instanceof Utilisateur) {
                b = utilisateurSrv.update(utilisateur, this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    /**
     *
     * @param login
     * @param context
     * @return
     * @throws IOException
     * @throws Exception
     */
    public boolean loginIsUse(String login, Context context) throws IOException, Exception  {
        Boolean b = false;
        if (login != null) {
            if (login instanceof String) {
                b = utilisateurSrv.loginIsUse(login, this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    /**
     *
     * @param utilisateur
     * @param context
     * @return
     * @throws Throwable
     * @throws IOException
     * @throws SSLPeerUnverifiedException
     * @throws ConnectException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public Technicien verificationConnexion(Technicien utilisateur, Context context) throws Throwable, IOException, SSLPeerUnverifiedException, ConnectException, SAXException, ParserConfigurationException {
        Technicien technicien = null;
        if (utilisateur != null) {
            if (utilisateur instanceof Utilisateur) {
                technicien = utilisateurSrv.verificationConnexion(utilisateur, this.getRessource(context));
                Thread.sleep(500);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return technicien;
    }

    /**
     *
     * @param id
     * @param context
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public Utilisateur getById(Long id, Context context) throws SAXException, ParserConfigurationException, MalformedURLException, IOException, Exception  {
        Utilisateur u = null;
        if (id != null) {
            if (id instanceof Long) {
                u = utilisateurSrv.getById(id, this.getRessource(context));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return u;
    }

    /**
     *
     * @param c
     * @return
     * @throws Exception
     * @throws MalformedURLException
     * @throws IOException
     */
    public int count(Context c) throws Exception, MalformedURLException, IOException {
        int u = 0;
        if (c != null) {
            if (c instanceof Context) {
                
                u = utilisateurSrv.count(this.getRessource(c));
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return u;
    }

    private Ressource getRessource(Context c) throws Exception {
        RessourcesServiceDataIn ressourcesSrv = PhysiqueDataInFactory.getRessourceSrv(c);
        Ressource ressource = null;
        ressource = ressourcesSrv.getRessource();
        return ressource;
    }
}
