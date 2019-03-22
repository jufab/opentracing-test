package fr.jufab.distributed.tracing.domain.use_cases;

import fr.jufab.distributed.tracing.domain.entities.Personne;

import javax.inject.Inject;

public class SavePersonne {
    PersonnePort personnePort;

    @Inject
    public SavePersonne(PersonnePort personnePort) {
        this.personnePort = personnePort;
    }

    public void execute(Personne personne) {
        this.personnePort.save(personne);
    }
}
