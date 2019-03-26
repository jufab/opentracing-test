package fr.jufab.distributed.tracing.infrastructure.database;

import fr.jufab.distributed.tracing.domain.entities.Personne;
import fr.jufab.distributed.tracing.domain.use_cases.PersonnePort;
import org.eclipse.microprofile.opentracing.Traced;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonneRedisAdapter implements PersonnePort {

    PersonneRedisRepository personneRedisRepository;

    @Inject
    public PersonneRedisAdapter(PersonneRedisRepository personneRedisRepository) {
        this.personneRedisRepository = personneRedisRepository;
    }

    @Override
    public void save(Personne personne) {
        this.personneRedisRepository.save(toPersonneRedis(personne));
    }

    @Override
    public List<Personne> findAll() {
        return this.personneRedisRepository.findAllPersonnes().stream().map(this::toPersonne).collect(Collectors.toList());
    }

    @Override
    public Personne findById(UUID idPersonne) {
        return toPersonne(this.personneRedisRepository.findPersonneById(idPersonne));
    }

    private PersonneRedis toPersonneRedis(Personne personne) {
        return new PersonneRedis(personne.getIdPersonne(),personne.getNom(),personne.getPrenom(),personne.getAdresse());
    }

    private Personne toPersonne(PersonneRedis personneRedis) {
        return new Personne(personneRedis.getIdPersonne(),personneRedis.getNom(),personneRedis.getPrenom(),personneRedis.getIdAdresse());
    }


}
