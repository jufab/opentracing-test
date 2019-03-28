package fr.jufab.distributed.tracing.infrastructure.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonneRedisRepository {
    Jedis jedis;
    static Logger LOGGER = LoggerFactory.getLogger(PersonneRedisRepository.class);
    Tracer tracer;

    @Inject
    public PersonneRedisRepository(Jedis jedis, Tracer tracer) {
        this.tracer = tracer;
        this.jedis = jedis;
    }

    void save(PersonneRedis personneRedis) {
        Span span = tracer.buildSpan("redis").start();
        span.log("Sauvegarde de la personne : " + personneRedis.toString());
        span.setTag("personneRedis", personneRedis.toString());
        try {
            jedis.set(personneRedis.getIdPersonne().toString(), personneRedis.toJSON());
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage(), e);
            span.log("ERREUR : " + e.getMessage());
        }
        span.finish();
    }

    PersonneRedis findPersonneById(UUID idPersonne) {
        return retournePersonneRedisEtTrace(idPersonne.toString());
    }

    List<PersonneRedis> findAllPersonnes() {
        Span span = tracer.buildSpan("redis").start();
        span.setTag("redis.jedis.objet", jedis.toString());
        span.log("Recherche des personnes");
        List<PersonneRedis> listePersonneRedis = jedis.keys("*").stream()
                .map(personneKey -> retournePersonneRedisEtTrace(personneKey))
                .collect(Collectors.toList());
        span.finish();
        return listePersonneRedis;
    }

    private PersonneRedis retournePersonneRedisEtTrace(String personneUUIDString) {
        PersonneRedis personneRedis = new PersonneRedis();
        Span span = tracer.buildSpan("redis").start();
        span.setTag("redis.jedis.objet", jedis.toString());
        span.log("Recherche de la personne : " + personneUUIDString);
        span.setTag("idPersonne", personneUUIDString);
        try {
            personneRedis = PersonneRedis.toObject(jedis.get(personneUUIDString));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            span.log("ERREUR : " + e.getMessage());
        }
        span.finish();
        return personneRedis;
    }
}
