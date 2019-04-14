package fr.pe.test.infrastructure.service;

import fr.pe.test.application.config.UrlAdresseApi;
import fr.pe.test.application.config.UrlPersonneApi;
import fr.pe.test.domain.entities.Adresse;
import fr.pe.test.domain.entities.Personne;
import fr.pe.test.domain.use_cases.PersonnePort;
import fr.pe.test.infrastructure.service.adresseapispring.AdresseServiceApi;
import fr.pe.test.infrastructure.service.adresseapispring.AdresseServiceExterneAdapter;
import fr.pe.test.infrastructure.service.personneapithorntail.PersonneServiceApi;
import fr.pe.test.infrastructure.service.personneapithorntail.PersonneServiceExterneAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonneServiceAdapter implements PersonnePort {

    AdresseServiceExterneAdapter adresseServiceExterneAdapter;
    PersonneServiceExterneAdapter personneServiceExterneAdapter;
    Logger logger = LoggerFactory.getLogger(PersonneServiceAdapter.class);

    @Inject
    public PersonneServiceAdapter(@UrlPersonneApi String urlPersonneApiThorntail, @UrlAdresseApi String urlAdressesApiSpring) throws URISyntaxException {
        this.adresseServiceExterneAdapter = new AdresseServiceExterneAdapter(new URI(urlAdressesApiSpring));
        this.personneServiceExterneAdapter = new PersonneServiceExterneAdapter(new URI(urlPersonneApiThorntail));
    }

    @Override
    public List<Personne> getAllPersonnes() {
        return personneServiceExterneAdapter
                .findAllPersonnes()
                .stream()
                .map(personneServiceApi -> {
                    AdresseServiceApi adresseServiceApi = this.adresseServiceExterneAdapter.findAdresse(personneServiceApi.getIdAdresse());
                    return toPersonne(personneServiceApi,adresseServiceApi);
                }).collect(Collectors.toList());
    }

    @Override
    public Personne getPersonne(UUID idPersonne) {
        PersonneServiceApi personneServiceApi = personneServiceExterneAdapter.findPersonneById(idPersonne);
        AdresseServiceApi adresseServiceApi = adresseServiceExterneAdapter.findAdresse(personneServiceApi.getIdAdresse());
        return toPersonne(personneServiceApi,adresseServiceApi);
    }

    @Override
    public Personne savePersonne(Personne personne) {
        PersonneServiceApi personneServiceApi = toPersonneServiceApi(personne);
        AdresseServiceApi adresseServiceApi = toAdresseServiceApi(personne.getAdresse());
        this.adresseServiceExterneAdapter.saveAdresse(adresseServiceApi);
        this.personneServiceExterneAdapter.savePersonne(personneServiceApi);
        return personne;
    }

    private Personne toPersonne(PersonneServiceApi personneServiceApi, AdresseServiceApi adresseServiceApi) {
        return new Personne(
                UUID.fromString(personneServiceApi.getIdPersonne()),
                personneServiceApi.getNom(),
                personneServiceApi.getPrenom(),
                toAdresse(adresseServiceApi)
        );
    }

    private Adresse toAdresse(AdresseServiceApi adresseServiceApi) {
        return new Adresse(
                UUID.fromString(adresseServiceApi.getIdAdresse()),
                adresseServiceApi.getLigneAdresse1(),
                adresseServiceApi.getLigneAdresse2(),
                adresseServiceApi.getLigneAdresse3(),
                adresseServiceApi.getCodePostal(),
                adresseServiceApi.getVille()
        );
    }

    private AdresseServiceApi toAdresseServiceApi(Adresse adresse) {
        return new AdresseServiceApi(
                adresse.getIdAdresse().toString(),
                adresse.getLigneAdresse1(),
                adresse.getLigneAdresse2(),
                adresse.getLigneAdresse3(),
                adresse.getCodePostal(),
                adresse.getVille()
        );
    }
    private PersonneServiceApi toPersonneServiceApi(Personne personne) {
        return new PersonneServiceApi(
                personne.getIdPersonne().toString(),
                personne.getNom(),
                personne.getPrenom(),
                personne.getAdresse().getIdAdresse().toString()
        );
    }
}
