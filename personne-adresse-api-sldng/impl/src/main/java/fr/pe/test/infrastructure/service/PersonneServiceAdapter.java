package fr.pe.test.infrastructure.service;

import fr.pe.test.domain.entities.Adresse;
import fr.pe.test.domain.entities.Personne;
import fr.pe.test.domain.use_cases.PersonnePort;
import fr.pe.test.infrastructure.service.adresseapispring.AdresseServiceApi;
import fr.pe.test.infrastructure.service.adresseapispring.AdresseServiceExterneAdapter;
import fr.pe.test.infrastructure.service.personneapithorntail.PersonneServiceApi;
import fr.pe.test.infrastructure.service.personneapithorntail.PersonneServiceExterneAdapter;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonneServiceAdapter implements PersonnePort {

    AdresseServiceExterneAdapter adresseServiceExterneAdapter;
    PersonneServiceExterneAdapter personneServiceExterneAdapter;

    public PersonneServiceAdapter(URI urlPersonneApiThorntail, URI urlAdressesApiSpring) {
        this.adresseServiceExterneAdapter = new AdresseServiceExterneAdapter(urlAdressesApiSpring);
        this.personneServiceExterneAdapter = new PersonneServiceExterneAdapter(urlPersonneApiThorntail);
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
        AdresseServiceApi adresseServiceApi = this.adresseServiceExterneAdapter.saveAdresse(toAdresseServiceApi(personne.getAdresse()));
        personne.setAdresse(toAdresse(adresseServiceApi));
        return toPersonne(this.personneServiceExterneAdapter.savePersonne(toPersonneServiceApi(personne)),adresseServiceApi);
    }

    private Personne toPersonne(PersonneServiceApi personneServiceApi, AdresseServiceApi adresseServiceApi) {
        return new Personne(
                personneServiceApi.getIdPersonne(),
                personneServiceApi.getNom(),
                personneServiceApi.getPrenom(),
                toAdresse(adresseServiceApi)
        );
    }

    private Adresse toAdresse(AdresseServiceApi adresseServiceApi) {
        return new Adresse(
                adresseServiceApi.getIdAdresse(),
                adresseServiceApi.getLigneAdresse1(),
                adresseServiceApi.getLigneAdresse2(),
                adresseServiceApi.getLigneAdresse3(),
                adresseServiceApi.getCodePostal(),
                adresseServiceApi.getVille()
        );
    }

    private AdresseServiceApi toAdresseServiceApi(Adresse adresse) {
        return new AdresseServiceApi(
                adresse.getIdAdresse(),
                adresse.getLigneAdresse1(),
                adresse.getLigneAdresse2(),
                adresse.getLigneAdresse3(),
                adresse.getCodePostal(),
                adresse.getVille()
        );
    }
    private PersonneServiceApi toPersonneServiceApi(Personne personne) {
        return new PersonneServiceApi(
                personne.getIdPersonne(),
                personne.getNom(),
                personne.getPrenom(),
                personne.getAdresse().getIdAdresse()
        );
    }
}
