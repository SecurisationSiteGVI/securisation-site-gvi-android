/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physique.dataOut;

import android.content.Context;
import metier.entitys.Technicien;
import metier.entitys.Utilisateur;
import java.util.List;

/**
 *
 * @author damien
 */
public interface UtilisateurServiceWeb {

    public List<Utilisateur> getAll(Context context) throws Exception;

    public List<Utilisateur> getAll(int from, int nbResut, Context context) throws Exception;

    public boolean add(Utilisateur utilisateur, Context context) throws Exception;

    public boolean remove(Utilisateur utilisateur, Context context) throws Exception;

    public boolean update(Utilisateur utilisateur, Context context) throws Exception;

    public boolean loginIsUse(String login, Context context) throws Exception;

    public Technicien verificationConnexion(Technicien utilisateur, Context context) throws Exception;

    public Utilisateur getById(Long id, Context context) throws Exception;

    public int count(Context c) throws Exception;
    
    public boolean addTechnicien(Technicien technicien,Context context) throws Exception;
}
