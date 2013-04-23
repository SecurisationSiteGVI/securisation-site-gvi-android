/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.entitys;

import java.util.Date;
import java.util.List;

/**
 *
 * @author damien
 */
public class AuthorisationAcces {

    private Long id;
    private Date heureOuverture;
    private Date heureFermeture;
    private Utilisateur utilisateur;
    private List<Secteur> secteurs;

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
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

    /**
     *
     * @return
     */
    public Date getHeureOuverture() {
        return heureOuverture;
    }

    /**
     *
     * @param heureOuverture
     */
    public void setHeureOuverture(Date heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    /**
     *
     * @return
     */
    public Date getHeureFermeture() {
        return heureFermeture;
    }

    /**
     *
     * @param heureFermeture
     */
    public void setHeureFermeture(Date heureFermeture) {
        this.heureFermeture = heureFermeture;
    }

    /**
     *
     * @return
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     *
     * @param utilisateur
     */
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    /**
     *
     * @return
     */
    public List<Secteur> getSecteurs() {
        return secteurs;
    }

    /**
     *
     * @param secteurs
     */
    public void setSecteurs(List<Secteur> secteurs) {
        this.secteurs = secteurs;
    }
}
