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

    /**
     *
     * @param context
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws Exception
     */
    public List<Utilisateur> getAll(Context context) throws ParserConfigurationException, SAXException, IOException, Exception;

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
    public List<Utilisateur> getAll(int from, int nbResut, Context context) throws SAXException, ParserConfigurationException, MalformedURLException, IOException, Exception;

    /**
     *
     * @param utilisateur
     * @param context
     * @return
     * @throws IOException
     * @throws Exception
     */
    public boolean add(Utilisateur utilisateur, Context context) throws IOException, Exception;

    /**
     *
     * @param utilisateur
     * @param context
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public boolean remove(Utilisateur utilisateur, Context context) throws MalformedURLException, IOException, Exception;

    /**
     *
     * @param utilisateur
     * @param context
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public boolean update(Utilisateur utilisateur, Context context) throws MalformedURLException, IOException, Exception;

    /**
     *
     * @param login
     * @param context
     * @return
     * @throws IOException
     * @throws Exception
     */
    public boolean loginIsUse(String login, Context context) throws IOException, Exception;

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
    public Technicien verificationConnexion(Technicien utilisateur, Context context) throws Throwable, IOException, SSLPeerUnverifiedException, ConnectException, SAXException, ParserConfigurationException;

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
    public Utilisateur getById(Long id, Context context) throws SAXException, ParserConfigurationException, MalformedURLException, IOException, Exception;

    /**
     *
     * @param c
     * @return
     * @throws Exception
     * @throws MalformedURLException
     * @throws IOException
     */
    public int count(Context c) throws Exception,MalformedURLException, IOException;

    /**
     *
     * @param utilisateur
     * @param context
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws Exception
     */
    public boolean addTechnicien(Technicien utilisateur, Context context) throws MalformedURLException, IOException, Exception;
}
