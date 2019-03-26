package fr.jufab.distributed.tracing.domain.use_cases;

import fr.jufab.distributed.tracing.domain.entities.Personne;
import org.eclipse.microprofile.opentracing.Traced;

import java.util.List;
import java.util.UUID;

public interface PersonnePort {
    void save(Personne personne);
    List<Personne> findAll();
    Personne findById(UUID idPersonne);
}
