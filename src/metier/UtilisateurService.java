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
import metier.entitys.Technicien;
import metier.entitys.Utilisateur;
import org.xml.sax.SAXException;

/**
 *
 * @author damien
 */
public interface UtilisateurService {

    public List<Utilisateur> getAll(Context context) throws Exception;

    public List<Utilisateur> getAll(int from, int nbResut, Context context) throws Exception;

    public boolean add(Utilisateur utilisateur, Context context) throws Exception;

    public boolean remove(Utilisateur utilisateur, Context context) throws Exception;

    public boolean update(Utilisateur utilisateur, Context context) throws Exception;

    public boolean loginIsUse(String login, Context context) throws Exception;

    public Technicien verificationConnexion(Technicien utilisateur, Context context) throws Throwable, IOException, SSLPeerUnverifiedException, ConnectException, SAXException, ParserConfigurationException;

    public Utilisateur getById(Long id, Context context) throws Exception;

    public int count(Context c) throws MalformedURLException, IOException;

    public boolean addTechnicien(Technicien utilisateur, Context context) throws Exception;
}
