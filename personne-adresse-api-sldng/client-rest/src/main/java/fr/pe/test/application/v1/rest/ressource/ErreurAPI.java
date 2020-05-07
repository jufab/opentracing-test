package fr.pe.test.application.v1.rest.ressource;

public class ErreurAPI {
    int codeErreur;
    String libelleErreur;

    public ErreurAPI() {
    }

    public ErreurAPI(int codeErreur, String libelleErreur) {
        this.codeErreur = codeErreur;
        this.libelleErreur = libelleErreur;
    }

    public int getCodeErreur() {
        return codeErreur;
    }

    public void setCodeErreur(int codeErreur) {
        this.codeErreur = codeErreur;
    }

    public String getLibelleErreur() {
        return libelleErreur;
    }

    public void setLibelleErreur(String libelleErreur) {
        this.libelleErreur = libelleErreur;
    }

    @Override
    public String toString() {
        return "ErreurAPI{" +
                "codeErreur=" + codeErreur +
                ", libelleErreur='" + libelleErreur + '\'' +
                '}';
    }
}
