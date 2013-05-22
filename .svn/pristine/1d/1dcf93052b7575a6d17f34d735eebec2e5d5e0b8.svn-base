/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut.utilisateur;

import android.content.Context;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import metier.entitys.Technicien;
import metier.entitys.Utilisateur;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.xml.parsers.ParserConfigurationException;
import metier.entitys.Ressource;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface UtilisateurServiceWeb {

    /**
     *
     * @param ressource
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public List<Utilisateur> getAll(Ressource ressource) throws ParserConfigurationException, SAXException, IOException;

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
    public List<Utilisateur> getAll(int from, int nbResut, Ressource ressource) throws SAXException, ParserConfigurationException, MalformedURLException, IOException;

    /**
     *
     * @param utilisateur
     * @param ressource
     * @return
     * @throws IOException
     */
    public boolean add(Utilisateur utilisateur, Ressource ressource) throws IOException;

    /**
     *
     * @param utilisateur
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public boolean remove(Utilisateur utilisateur, Ressource ressource) throws MalformedURLException, IOException;

    /**
     *
     * @param utilisateur
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public boolean update(Utilisateur utilisateur, Ressource ressource) throws MalformedURLException, IOException;

    /**
     *
     * @param login
     * @param ressource
     * @return
     * @throws IOException
     */
    public boolean loginIsUse(String login, Ressource ressource) throws IOException;

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
    public Technicien verificationConnexion(Technicien utilisateur, Ressource ressource) throws Throwable, IOException, SSLPeerUnverifiedException, ConnectException, SAXException, ParserConfigurationException;

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
    public Utilisateur getById(Long id, Ressource ressource) throws SAXException, ParserConfigurationException, MalformedURLException, IOException;

    /**
     *
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public int count(Ressource ressource) throws MalformedURLException, IOException;

    /**
     *
     * @param technicien
     * @param ressource
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public boolean addTechnicien(Technicien technicien, Ressource ressource)throws MalformedURLException, IOException;
}
