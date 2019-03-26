package fr.jufab.distributed.tracing.domain.entities;

import java.util.Objects;
import java.util.UUID;

public class Personne {
    UUID idPersonne;
    String Nom;
    String Prenom;
    UUID Adresse;

    public Personne() {
    }

    public Personne(UUID idPersonne, String nom, String prenom, UUID adresse) {
        this.idPersonne = idPersonne;
        Nom = nom;
        Prenom = prenom;
        Adresse = adresse;
    }

    public UUID getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(UUID idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public UUID getAdresse() {
        return Adresse;
    }

    public void setAdresse(UUID adresse) {
        Adresse = adresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return Objects.equals(idPersonne, personne.idPersonne) &&
                Objects.equals(Nom, personne.Nom) &&
                Objects.equals(Prenom, personne.Prenom) &&
                Objects.equals(Adresse, personne.Adresse);
    }

    @Override
    public String toString() {
        return "Personne{" +
                "idPersonne=" + idPersonne +
                ", Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", Adresse=" + Adresse +
                '}';
    }
}
