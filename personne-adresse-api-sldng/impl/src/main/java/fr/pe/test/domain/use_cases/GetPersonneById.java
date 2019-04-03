package fr.pe.test.domain.use_cases;

import fr.pe.test.domain.entities.Personne;

import javax.inject.Inject;
import java.util.UUID;

public class GetPersonneById {
    PersonnePort personnePort;

    @Inject
    public GetPersonneById(PersonnePort personnePort) {
        this.personnePort = personnePort;
    }

    public Personne execute(UUID idPersonne) {
        return this.personnePort.getPersonne(idPersonne);
    }
}
