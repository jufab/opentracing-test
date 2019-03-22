package fr.jufab.distributed.tracing.infrastructure.database;

import fr.jufab.distributed.tracing.domain.entities.Personne;
import fr.jufab.distributed.tracing.domain.use_cases.PersonnePort;

import java.util.List;
import java.util.UUID;

public class PersonneRedisAdapter implements PersonnePort {

    @Override
    public void save(Personne personne) {

    }

    @Override
    public List<Personne> findAll() {
        return null;
    }

    @Override
    public Personne findById(UUID idPersonne) {
        return null;
    }
}
