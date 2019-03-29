package fr.jufab.distributed.tracing.infrastructure.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@RedisHash("AdresseRedis")
public class AdresseRedis implements Serializable {
    @Id
    String idAdresse;
    String ligneAdresse1;
    String ligneAdresse2;
    String ligneAdresse3;
    String codePostal;
    String ville;

    public AdresseRedis() {
    }

    public AdresseRedis(String idAdresse, String ligneAdresse1, String ligneAdresse2, String ligneAdresse3, String codePostal, String ville) {
        this.idAdresse = idAdresse;
        this.ligneAdresse1 = ligneAdresse1;
        this.ligneAdresse2 = ligneAdresse2;
        this.ligneAdresse3 = ligneAdresse3;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public String getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(String idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getLigneAdresse1() {
        return ligneAdresse1;
    }

    public void setLigneAdresse1(String ligneAdresse1) {
        this.ligneAdresse1 = ligneAdresse1;
    }

    public String getLigneAdresse2() {
        return ligneAdresse2;
    }

    public void setLigneAdresse2(String ligneAdresse2) {
        this.ligneAdresse2 = ligneAdresse2;
    }

    public String getLigneAdresse3() {
        return ligneAdresse3;
    }

    public void setLigneAdresse3(String ligneAdresse3) {
        this.ligneAdresse3 = ligneAdresse3;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "AdresseRedis{" +
                "idAdresse=" + idAdresse +
                ", ligneAdresse1='" + ligneAdresse1 + '\'' +
                ", ligneAdresse2='" + ligneAdresse2 + '\'' +
                ", ligneAdresse3='" + ligneAdresse3 + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
