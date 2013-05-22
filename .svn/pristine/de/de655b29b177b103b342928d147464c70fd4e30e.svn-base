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
public class Utilisateur {

    private Long id;
    private String nom;
    private String prenom;
    private String ville;
    private int codePostale;
    private String adresse;
    private String telephonePortable;
    private String telephoneFixe;
    private boolean homme;
    private Date dateDeNaissance;
    private String email;
    private int age;

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
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return prenom + " " + nom;
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
    public String getPrenom() {
        return prenom;
    }

    /**
     *
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     *
     * @return
     */
    public String getVille() {
        return ville;
    }

    /**
     *
     * @param ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     *
     * @return
     */
    public int getCodePostale() {
        return codePostale;
    }

    /**
     *
     * @param codePostale
     */
    public void setCodePostale(int codePostale) {
        this.codePostale = codePostale;
    }

    /**
     *
     * @return
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     *
     * @param adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     *
     * @return
     */
    public boolean isHomme() {
        return homme;
    }

    /**
     *
     * @param homme
     */
    public void setHomme(boolean homme) {
        this.homme = homme;
    }

    /**
     *
     * @return
     */
    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    /**
     *
     * @param dateDeNaissance
     */
    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public int getAge() {
        int agen = 0;
        if (dateDeNaissance != null) {
            int yearH = dateDeNaissance.getYear();
            int yearN = new Date().getYear();
            agen = yearN - yearH;
        }
        return agen;
    }

    /**
     *
     * @return
     */
    public String getTelephonePortable() {
        return telephonePortable;
    }

    /**
     *
     * @param telephonePortable
     */
    public void setTelephonePortable(String telephonePortable) {
        this.telephonePortable = telephonePortable;
    }

    /**
     *
     * @return
     */
    public String getTelephoneFixe() {
        return telephoneFixe;
    }

    /**
     *
     * @param telephoneFixe
     */
    public void setTelephoneFixe(String telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
    }
}
