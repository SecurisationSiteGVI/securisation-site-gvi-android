/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut;


import metier.entitys.Technicien;
import metier.entitys.Utilisateur;
import java.util.List;

/**
 *
 * @author damien
 */
public interface UtilisateurServiceWeb {
    public List<Utilisateur> getAll() throws Exception;
    public List<Utilisateur> getAll(int from,int nbResut) throws Exception;
    public boolean add(Utilisateur utilisateur) throws Exception;
    public boolean remove(Utilisateur utilisateur) throws Exception;
    public boolean update(Utilisateur utilisateur) throws Exception;
    public boolean loginIsUse(String login) throws Exception;
    public Technicien verificationConnexion(Technicien utilisateur) throws Exception;
    public Utilisateur getById(Long id)throws Exception;
    
}
