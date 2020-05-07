package fr.jufab.distributed.tracing.infrastructure.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class PersonneRedis {

    UUID idPersonne;
    String nom;
    String prenom;
    UUID idAdresse;

    public PersonneRedis() {
    }

    public PersonneRedis(UUID idPersonne, String nom, String prenom, UUID idAdresse) {
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

    public String toJSON() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    public static PersonneRedis toObject(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json,PersonneRedis.class);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonneRedis that = (PersonneRedis) o;
        return Objects.equals(idPersonne, that.idPersonne) &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(prenom, that.prenom) &&
                Objects.equals(idAdresse, that.idAdresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersonne, nom, prenom, idAdresse);
    }

    @Override
    public String toString() {
        return "PersonneRedis{" +
                "idPersonne=" + idPersonne +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", idAdresse=" + idAdresse +
                '}';
    }
}
