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

    public List<Utilisateur> getAll(Ressource ressource) throws ParserConfigurationException, SAXException, IOException;

    public List<Utilisateur> getAll(int from, int nbResut, Ressource ressource) throws SAXException, ParserConfigurationException, MalformedURLException, IOException;

    public boolean add(Utilisateur utilisateur, Ressource ressource) throws IOException;

    public boolean remove(Utilisateur utilisateur, Ressource ressource) throws MalformedURLException, IOException;

    public boolean update(Utilisateur utilisateur, Ressource ressource) throws MalformedURLException, IOException;

    public boolean loginIsUse(String login, Ressource ressource) throws IOException;

    public Technicien verificationConnexion(Technicien utilisateur, Ressource ressource) throws Throwable, IOException, SSLPeerUnverifiedException, ConnectException, SAXException, ParserConfigurationException;

    public Utilisateur getById(Long id, Ressource ressource) throws SAXException, ParserConfigurationException, MalformedURLException, IOException;

    public int count(Ressource ressource) throws MalformedURLException, IOException;

    public boolean addTechnicien(Technicien technicien, Ressource ressource)throws MalformedURLException, IOException;
}
