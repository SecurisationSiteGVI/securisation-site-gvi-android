/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.util.List;
import metier.entitys.Technicien;
import metier.entitys.Utilisateur;

/**
 *
 * @author damien
 */
public interface UtilisateurService {
    public List<Utilisateur> getAll() throws Exception;
    public List<Utilisateur> getAll(int from,int nbResut) throws Exception;
    public boolean add(Utilisateur utilisateur) throws Exception;
    public boolean remove(Utilisateur utilisateur) throws Exception;
    public boolean update(Utilisateur utilisateur) throws Exception;
    public boolean loginIsUse(String login) throws Exception;
    public Technicien verificationConnexion(Technicien utilisateur) throws Exception;
    public Utilisateur getById(Long id)throws Exception;
}
