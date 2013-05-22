/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.entitys;

/**
 *
 * @author damien
 */
public class BorneAcces {

    private Long id;
    private Position position;
    private String nom;
    private Boolean entrer;

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
        if (!(object instanceof BorneAcces)) {
            return false;
        }
        BorneAcces other = (BorneAcces) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        String entr = "sorie";
        if (this.entrer) {
            entr = "entré";
        }
        return nom + " " + entr;
    }

    /**
     *
     * @return
     */
    public Position getPosition() {
        return position;
    }

    /**
     *
     * @param position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return
     */
    public Boolean getEntrer() {
        return entrer;
    }

    /**
     *
     * @param entrer
     */
    public void setEntrer(Boolean entrer) {
        this.entrer = entrer;
    }
}
