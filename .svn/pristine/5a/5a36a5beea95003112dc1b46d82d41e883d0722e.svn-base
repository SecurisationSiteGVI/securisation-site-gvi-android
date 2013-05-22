/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.entitys;

import java.util.Date;

/**
 *
 * @author damien
 */
public class Evenement {

    private Long id;
    private Date dateEvt;

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
        if (!(object instanceof Evenement)) {
            return false;
        }
        Evenement other = (Evenement) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        
        return "Evenement à "+this.dateEvt.toLocaleString();
    }

    public Date getDateEvt() {
        return dateEvt;
    }

    public void setDateEvt(Date dateEvt) {
        this.dateEvt = dateEvt;
    }
}
