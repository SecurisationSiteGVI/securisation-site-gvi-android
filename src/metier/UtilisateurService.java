/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.util.List;
import metier.entitys.Technicien;
import metier.entitys.Utilisateur;

/**
 *
 * @author damien
 */
public interface UtilisateurService {
    public List<Utilisateur> getAll(Context context) throws Exception;
    public List<Utilisateur> getAll(int from,int nbResut,Context context) throws Exception;
    public boolean add(Utilisateur utilisateur,Context context) throws Exception;
    public boolean remove(Utilisateur utilisateur,Context context) throws Exception;
    public boolean update(Utilisateur utilisateur,Context context) throws Exception;
    public boolean loginIsUse(String login,Context context) throws Exception;
    public Technicien verificationConnexion(Technicien utilisateur,Context context) throws Exception;
    public Utilisateur getById(Long id,Context context)throws Exception;
}
