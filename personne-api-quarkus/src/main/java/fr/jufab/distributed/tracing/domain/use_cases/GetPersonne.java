package fr.jufab.distributed.tracing.domain.use_cases;

import fr.jufab.distributed.tracing.domain.entities.Personne;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class GetPersonne {
    PersonnePort personnePort;

    public GetPersonne(PersonnePort personnePort) {
        this.personnePort = personnePort;
    }

    public Personne execute(UUID idPersonne) {
        return this.personnePort.findById(idPersonne);
    }
}
