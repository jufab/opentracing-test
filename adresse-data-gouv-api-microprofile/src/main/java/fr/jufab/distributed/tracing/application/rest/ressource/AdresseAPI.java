package fr.jufab.distributed.tracing.application.rest.ressource;

public class AdresseAPI {

    String nomAdresse;
    String ville;
    String codePostal;

    public AdresseAPI() {
    }

    public AdresseAPI(String nomAdresse, String ville, String codePostal) {
        this.nomAdresse = nomAdresse;
        this.ville = ville;
        this.codePostal = codePostal;
    }

    public String getNomAdresse() {
        return nomAdresse;
    }

    public void setNomAdresse(String nomAdresse) {
        this.nomAdresse = nomAdresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
}
