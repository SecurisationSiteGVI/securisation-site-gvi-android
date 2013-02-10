/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.clientServiceWeb;


import fr.securisation_site_gvi.entitys.Utilisateur;
import java.util.List;

/**
 *
 * @author damien
 */
public interface UtilisateurServiceWeb {
    public List<Utilisateur> getAll() throws Exception;
    public boolean add(Utilisateur utilisateur) throws Exception;
    public boolean remove(Utilisateur utilisateur) throws Exception;
    public boolean update(Utilisateur utilisateur) throws Exception;
    public boolean loginIsUse(String login) throws Exception;
    public Utilisateur verificationConnexion(Utilisateur utilisateur) throws Exception;
}
