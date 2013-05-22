/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.entitys;

import java.util.List;

/**
 *
 * @author damien
 */
public class NumeroPredefinis {

    private Long id;
    private List<String> numeros;

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
        if (!(object instanceof NumeroPredefinis)) {
            return false;
        }
        NumeroPredefinis other = (NumeroPredefinis) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "liste de numeros";
    }

    /**
     *
     * @return
     */
    public List<String> getNumeros() {
        return numeros;
    }

    /**
     *
     * @param numeros
     */
    public void setNumeros(List<String> numeros) {
        this.numeros = numeros;
    }
}
