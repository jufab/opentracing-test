package fr.jufab.distributed.tracing.infrastructure.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.eclipse.microprofile.opentracing.Traced;
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

    @Inject
    public PersonneRedisRepository(Jedis jedis) {
        this.jedis = jedis;
    }

    void save(PersonneRedis personneRedis) {
        try {
            jedis.set(personneRedis.getIdPersonne().toString(),personneRedis.toJSON());
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage(),e);
        }
    }

    PersonneRedis findPersonneById(UUID idPersonne) {
        try {
            return PersonneRedis.toObject(jedis.get(idPersonne.toString()));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
        }
        return null;
    }

    List<PersonneRedis> findAllPersonnes() {
        return jedis.keys("*").stream().map(personneKey -> {
            PersonneRedis personneRedis = new PersonneRedis();
            try {
                personneRedis = PersonneRedis.toObject(jedis.get(personneKey));
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
            return personneRedis;
        }).collect(Collectors.toList());
    }
}
