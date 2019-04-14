package fr.pe.test.infrastructure.service.personneapithorntail;


public class PersonneServiceApi {
    String idPersonne;
    String nom;
    String prenom;
    String idAdresse;

    public PersonneServiceApi() {
    }

    public PersonneServiceApi(String idPersonne, String nom, String prenom, String idAdresse) {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.idAdresse = idAdresse;
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

    public String getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(String idAdresse) {
        this.idAdresse = idAdresse;
    }

    @Override
    public String toString() {
        return "PersonneServiceApi{" +
                "idPersonne=" + idPersonne +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", idAdresse=" + idAdresse +
                '}';
    }
}
