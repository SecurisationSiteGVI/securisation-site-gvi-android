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
public class AttributionSecteurDetecteurIntrusion {

    private Long id;
    private Secteur secteur;
    private List<DetecteurIntrusion> detecteurIntrusions;

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
        if (!(object instanceof AttributionSecteurDetecteurIntrusion)) {
            return false;
        }
        AttributionSecteurDetecteurIntrusion other = (AttributionSecteurDetecteurIntrusion) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Attribution secteur " + secteur;
    }

    /**
     *
     * @return
     */
    public Secteur getSecteur() {
        return secteur;
    }

    /**
     *
     * @param secteur
     */
    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    /**
     *
     * @return
     */
    public List<DetecteurIntrusion> getDetecteurIntrusions() {
        return detecteurIntrusions;
    }

    /**
     *
     * @param detecteurIntrusions
     */
    public void setDetecteurIntrusions(List<DetecteurIntrusion> detecteurIntrusions) {
        this.detecteurIntrusions = detecteurIntrusions;
    }
}
