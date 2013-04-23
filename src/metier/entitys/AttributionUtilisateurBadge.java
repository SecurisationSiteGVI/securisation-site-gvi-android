/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.entitys;

import java.io.Serializable;

/**
 *
 * @author damien
 */
public class AttributionUtilisateurBadge {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Utilisateur utilisateur;
    private Badge badge;

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
        if (!(object instanceof AttributionUtilisateurBadge)) {
            return false;
        }
        AttributionUtilisateurBadge other = (AttributionUtilisateurBadge) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getUtilisateur() + " " + getBadge();
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
    public Badge getBadge() {
        return badge;
    }

    /**
     *
     * @param badge
     */
    public void setBadge(Badge badge) {
        this.badge = badge;
    }
}
