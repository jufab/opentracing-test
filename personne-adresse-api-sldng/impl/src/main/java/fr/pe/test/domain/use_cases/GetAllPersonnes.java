package fr.pe.test.domain.use_cases;

import fr.pe.test.domain.entities.Personne;

import javax.inject.Inject;
import java.util.List;

public class GetAllPersonnes {

    PersonnePort personnePort;

    @Inject
    public GetAllPersonnes(PersonnePort personnePort) {
        this.personnePort = personnePort;
    }

    public List<Personne> execute() {
        return this.personnePort.getAllPersonnes();
    }
}
