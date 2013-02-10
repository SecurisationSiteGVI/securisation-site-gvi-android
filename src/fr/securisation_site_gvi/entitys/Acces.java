/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.securisation_site_gvi.entitys;

/**
 *
 * @author damien
 */
public class Acces extends Evenement  {


    private Long id;
    private Utilisateur utilisateur;
    private Boolean passage;
    private BorneAcces borneAcces;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acces)) {
            return false;
        }
        Acces other = (Acces) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "passege de l'utilisateur : "+utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Boolean getPassage() {
        return passage;
    }

    public void setPassage(Boolean passage) {
        this.passage = passage;
    }

    public BorneAcces getBorneAcces() {
        return borneAcces;
    }

    public void setBorneAcces(BorneAcces borneAcces) {
        this.borneAcces = borneAcces;
    }
    
}
