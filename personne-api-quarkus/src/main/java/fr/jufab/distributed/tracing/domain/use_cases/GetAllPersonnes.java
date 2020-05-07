package fr.jufab.distributed.tracing.domain.use_cases;

import fr.jufab.distributed.tracing.domain.entities.Personne;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class GetAllPersonnes {
    PersonnePort personnePort;

    public GetAllPersonnes(PersonnePort personnePort) {
        this.personnePort = personnePort;
    }

    public List<Personne> execute() {
        return personnePort.findAll();
    }
}
