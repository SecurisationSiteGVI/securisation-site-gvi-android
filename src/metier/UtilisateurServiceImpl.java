/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.util.List;
import metier.entitys.Technicien;
import metier.entitys.Utilisateur;
import physique.dataOut.PhysiqueDataOutFactory;
import physique.dataOut.UtilisateurServiceWeb;

/**
 *
 * @author damien
 */
public class UtilisateurServiceImpl implements UtilisateurService{
    private UtilisateurServiceWeb utilisateurSrv = PhysiqueDataOutFactory.getPersonneClientServiceWeb();
    public List<Utilisateur> getAll() throws Exception {
        return this.utilisateurSrv.getAll();
    }

    public List<Utilisateur> getAll(int from, int nbResut) throws Exception {
        return this.utilisateurSrv.getAll(from, nbResut);
    }

    public boolean add(Utilisateur utilisateur) throws Exception {
        Boolean b = false;
        if (utilisateur != null) {
            if (utilisateur instanceof Utilisateur) {
                b=utilisateurSrv.add(utilisateur);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }return b;
    }

    public boolean remove(Utilisateur utilisateur) throws Exception {
        Boolean b = false;
        if (utilisateur != null) {
            if (utilisateur instanceof Utilisateur) {
                b=utilisateurSrv.add(utilisateur);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }return b;
    }

    public boolean update(Utilisateur utilisateur) throws Exception {
        Boolean b = false;
        if (utilisateur != null) {
            if (utilisateur instanceof Utilisateur) {
                b=utilisateurSrv.update(utilisateur);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }return b;
    }

    public boolean loginIsUse(String login) throws Exception {
        Boolean b = false;
        if (login != null) {
            if (login instanceof String) {
               b= utilisateurSrv.loginIsUse(login);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }return b;
    }

    public Technicien verificationConnexion(Technicien utilisateur) throws Exception {
        Technicien  technicien = null;
        if (utilisateur != null) {
            if (utilisateur instanceof Utilisateur) {
                technicien = utilisateurSrv.verificationConnexion(utilisateur);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }return technicien;
    }

    public Utilisateur getById(Long id) throws Exception {
        Utilisateur u = null;
        if (id != null) {
            if (id instanceof Long) {
                u=utilisateurSrv.getById(id);
            } else {
                System.out.println("L'instance de l'objet ne coresspond pas veuiller utiliser la bonne classe de service.");
            }
        } else {
            throw new NullPointerException("Objet passé en parametre égale à null");
        }return u;
    }
    
}
