package fr.pe.test.application.v1.rest;

import fr.pe.test.application.v1.rest.api.PersonneRestAdapter;
import fr.pe.test.application.v1.rest.ressource.AdresseAPI;
import fr.pe.test.application.v1.rest.ressource.PersonneAPI;
import fr.pe.test.domain.entities.Adresse;
import fr.pe.test.domain.entities.Personne;
import fr.pe.test.domain.use_cases.GetAllPersonnes;
import fr.pe.test.domain.use_cases.GetPersonneById;
import fr.pe.test.domain.use_cases.SavePersonne;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonneRestAdapterImpl implements PersonneRestAdapter {

    @Inject
    GetAllPersonnes getAllPersonnes;
    @Inject
    GetPersonneById getPersonneById;
    @Inject
    SavePersonne savePersonne;

    @Override
    public List<PersonneAPI> getAllPersonnes() {
        return this.getAllPersonnes.execute().stream().map(this::toPersonneApi).collect(Collectors.toList());
    }

    @Override
    public PersonneAPI getPersonne(UUID idPersonne) {
        return toPersonneApi(this.getPersonneById.execute(idPersonne));
    }

    @Override
    public PersonneAPI savePersonne(PersonneAPI personneAPI) {
        return toPersonneApi(this.savePersonne.execute(toPersonne(personneAPI)));
    }

    private PersonneAPI toPersonneApi(Personne personne) {
        return new PersonneAPI(
                personne.getIdPersonne(),
                personne.getNom(),
                personne.getPrenom(),
                toAdresseApi(personne.getAdresse())
        );
    }

    private AdresseAPI toAdresseApi(Adresse adresse) {
        return new AdresseAPI(
                adresse.getIdAdresse(),
                adresse.getLigneAdresse1(),
                adresse.getLigneAdresse2(),
                adresse.getLigneAdresse3(),
                adresse.getCodePostal(),
                adresse.getVille()
        );
    }

    private Personne toPersonne(PersonneAPI personneAPI) {
        return new Personne(
                personneAPI.getIdPersonne(),
                personneAPI.getNom(),
                personneAPI.getPrenom(),
                toAdresse(personneAPI.getAdresseAPI())
        );
    }

    private Adresse toAdresse(AdresseAPI adresseAPI) {
        return new Adresse(
                adresseAPI.getIdAdresse(),
                adresseAPI.getLigneAdresse1(),
                adresseAPI.getLigneAdresse2(),
                adresseAPI.getLigneAdresse3(),
                adresseAPI.getCodePostal(),
                adresseAPI.getVille()
        );
    }
}
