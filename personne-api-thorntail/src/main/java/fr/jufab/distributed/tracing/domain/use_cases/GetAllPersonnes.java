package fr.jufab.distributed.tracing.domain.use_cases;

import fr.jufab.distributed.tracing.domain.entities.Personne;

import javax.inject.Inject;
import java.util.List;

public class GetAllPersonnes {
    PersonnePort personnePort;

    @Inject
    public GetAllPersonnes(PersonnePort personnePort) {
        this.personnePort = personnePort;
    }

    public List<Personne> execute() {
        return personnePort.findAll();
    }
}
