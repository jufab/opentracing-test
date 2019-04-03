package fr.pe.test.application.v1.rest.ressource;

import java.util.UUID;

public class PersonneAPI {
    UUID idPersonne;
    String nom;
    String prenom;
    AdresseAPI adresseAPI;

    public PersonneAPI() {
    }

    public PersonneAPI(UUID idPersonne, String nom, String prenom, AdresseAPI adresseAPI) {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.adresseAPI = adresseAPI;
    }

    public UUID getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(UUID idPersonne) {
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
}
