/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

/**
 *
 * @author damien
 */
public class Intrusion extends Evenement  {

    private Long id;
    private DetecteurIntrusion detecteurIntrusion;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Intrusion)) {
            return false;
        }
        Intrusion other = (Intrusion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evenement sur : "+detecteurIntrusion;
    }

    public DetecteurIntrusion getDetecteurIntrusion() {
        return detecteurIntrusion;
    }

    public void setDetecteurIntrusion(DetecteurIntrusion detecteurIntrusion) {
        this.detecteurIntrusion = detecteurIntrusion;
    }
    
}
