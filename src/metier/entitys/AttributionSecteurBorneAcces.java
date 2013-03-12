/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.entitys;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author damien
 */

public class AttributionSecteurBorneAcces  {

    private Long id;
    private Secteur secteur;
    private List<BorneAcces> borneAccess;
    
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
        if (!(object instanceof AttributionSecteurBorneAcces)) {
            return false;
        }
        AttributionSecteurBorneAcces other = (AttributionSecteurBorneAcces) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AttributionSecteurBorneAcces sur secteur : "+getSecteur();
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public List<BorneAcces> getBorneAccess() {
        return borneAccess;
    }

    public void setBorneAccess(List<BorneAcces> borneAccess) {
        this.borneAccess = borneAccess;
    }
    
}
