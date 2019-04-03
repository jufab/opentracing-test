package fr.pe.test.domain.use_cases;

import fr.pe.test.domain.entities.Personne;

import javax.inject.Inject;

public class SavePersonne {
    PersonnePort personnePort;

    @Inject
    public SavePersonne(PersonnePort personnePort) {
        this.personnePort = personnePort;
    }

    public Personne execute(Personne personne) {
        return this.personnePort.savePersonne(personne);
    }
}
