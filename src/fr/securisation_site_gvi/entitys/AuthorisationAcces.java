/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.entitys;

import java.util.Date;
import java.util.List;

/**
 *
 * @author damien
 */

public class AuthorisationAcces  {
    
    private Long id;
    private Date heureOuverture;
    private Date heureFermeture;
    private Utilisateur utilisateur;
    private List<Secteur> secteurs;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthorisationAcces)) {
            return false;
        }
        AuthorisationAcces other = (AuthorisationAcces) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metier.entitys.AuthorisationAcces7[ id=" + getId() + " ]";
    }

    public Date getHeureOuverture() {
        return heureOuverture;
    }

    public void setHeureOuverture(Date heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    public Date getHeureFermeture() {
        return heureFermeture;
    }

    public void setHeureFermeture(Date heureFermeture) {
        this.heureFermeture = heureFermeture;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Secteur> getSecteurs() {
        return secteurs;
    }

    public void setSecteurs(List<Secteur> secteurs) {
        this.secteurs = secteurs;
    }
    
}
