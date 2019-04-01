package fr.jufab.distributed.tracing.infrastructure.service;

public class AdresseService {

    String postCode;
    String city;
    String name;
    double score;

    public AdresseService() {
    }

    public AdresseService(String postCode, String city, String name, double score) {
        this.postCode = postCode;
        this.city = city;
        this.name = name;
        this.score = score;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
