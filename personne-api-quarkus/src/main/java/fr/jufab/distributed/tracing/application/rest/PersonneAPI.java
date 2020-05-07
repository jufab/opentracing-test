package fr.jufab.distributed.tracing.application.rest;

import java.util.UUID;


public class PersonneAPI {
    UUID idPersonne;
    String nom;
    String prenom;
    UUID idAdresse;

    public PersonneAPI() {
    }

    public PersonneAPI(UUID idPersonne, String nom, String prenom, UUID idAdresse) {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.idAdresse = idAdresse;
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

    public UUID getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(UUID idAdresse) {
        this.idAdresse = idAdresse;
    }
}
