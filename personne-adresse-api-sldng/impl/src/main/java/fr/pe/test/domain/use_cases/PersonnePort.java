package fr.pe.test.domain.use_cases;

import fr.pe.test.domain.entities.Personne;

import java.util.List;
import java.util.UUID;

public interface PersonnePort {
    List<Personne> getAllPersonnes();
    Personne getPersonne(UUID idPersonne);
    Personne savePersonne(Personne personne);
}
