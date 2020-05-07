package fr.jufab.distributed.tracing.domain.use_cases;

import fr.jufab.distributed.tracing.domain.entities.Personne;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SavePersonne {
    PersonnePort personnePort;

    public SavePersonne(PersonnePort personnePort) {
        this.personnePort = personnePort;
    }

    public void execute(Personne personne) {
        this.personnePort.save(personne);
    }
}
