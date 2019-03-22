package fr.jufab.distributed.tracing.domain.use_cases;

import fr.jufab.distributed.tracing.domain.entities.Personne;

import javax.inject.Inject;
import java.util.UUID;

public class GetPersonne {
    PersonnePort personnePort;

    @Inject
    public GetPersonne(PersonnePort personnePort) {
        this.personnePort = personnePort;
    }

    public Personne execute(UUID idPersonne) {
        return this.personnePort.findById(idPersonne);
    }
}
