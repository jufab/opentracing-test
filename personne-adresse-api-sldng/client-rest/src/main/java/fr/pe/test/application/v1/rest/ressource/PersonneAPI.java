package fr.pe.test.application.v1.rest.ressource;

public class PersonneAPI {
    String idPersonne;
    String nom;
    String prenom;
    AdresseAPI adresseAPI;

    public PersonneAPI() {
    }

    public PersonneAPI(String idPersonne, String nom, String prenom, AdresseAPI adresseAPI) {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.adresseAPI = adresseAPI;
    }

    public String getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(String idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public AdresseAPI getAdresseAPI() {
        return adresseAPI;
    }

    public void setAdresseAPI(AdresseAPI adresseAPI) {
        this.adresseAPI = adresseAPI;
    }

    @Override
    public String toString() {
        return "PersonneAPI{" +
                "idPersonne=" + idPersonne +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresseAPI=" + adresseAPI +
                '}';
    }
}
