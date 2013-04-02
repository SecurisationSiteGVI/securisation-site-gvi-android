/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import android.content.Context;
import java.util.List;
import metier.entitys.Technicien;
import metier.entitys.Utilisateur;
import physique.dataOut.PhysiqueDataOutFactory;
import physique.dataOut.utilisateur.UtilisateurServiceWeb;

/**
 *
 * @author damien
 */
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurServiceWeb utilisateurSrv = PhysiqueDataOutFactory.getPersonneClientServiceWeb();

    public List<Utilisateur> getAll(Context context) throws Exception {
        return this.utilisateurSrv.getAll(context);
    }

    public List<Utilisateur> getAll(int from, int nbResut, Context context) throws Exception {
        return this.utilisateurSrv.getAll(from, nbResut, context);
    }

    public boolean add(Utilisateur utilisateur, Context context) throws Exception {
        Boolean b = false;
        if (utilisateur != null) {
            if (utilisateur instanceof Utilisateur) {
                b = utilisateurSrv.add(utilisateur, context);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }
    public boolean addTechnicien(Technicien utilisateur, Context context) throws Exception {
        Boolean b = false;
        if (utilisateur != null) {
            if (utilisateur instanceof Utilisateur) {
                b = utilisateurSrv.addTechnicien(utilisateur, context);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public boolean remove(Utilisateur utilisateur, Context context) throws Exception {
        Boolean b = false;
        if (utilisateur != null) {
            if (utilisateur instanceof Utilisateur) {
                b = utilisateurSrv.remove(utilisateur, context);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public boolean update(Utilisateur utilisateur, Context context) throws Exception {
        Boolean b = false;
        if (utilisateur != null) {
            if (utilisateur instanceof Utilisateur) {
                b = utilisateurSrv.update(utilisateur, context);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public boolean loginIsUse(String login, Context context) throws Exception {
        Boolean b = false;
        if (login != null) {
            if (login instanceof String) {
                b = utilisateurSrv.loginIsUse(login, context);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return b;
    }

    public Technicien verificationConnexion(Technicien utilisateur, Context context) throws Exception {
        Technicien technicien = null;
        if (utilisateur != null) {
            if (utilisateur instanceof Utilisateur) {
                technicien = utilisateurSrv.verificationConnexion(utilisateur, context);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return technicien;
    }

    public Utilisateur getById(Long id, Context context) throws Exception {
        Utilisateur u = null;
        if (id != null) {
            if (id instanceof Long) {
                u = utilisateurSrv.getById(id, context);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return u;
    }

    public int count(Context c) throws Exception {
        int u = 0;
        if (c != null) {
            if (c instanceof Context) {
                u = utilisateurSrv.count(c);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }
        return u;
    }
}
