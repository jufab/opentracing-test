package fr.jufab.distributed.tracing.infrastructure.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.jufab.distributed.tracing.AbstractTestContainerRedis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonneRedisRepositoryTest extends AbstractTestContainerRedis {

    Jedis jedis;
    UUID idPersonne = UUID.randomUUID();
    PersonneRedisRepository personneRedisRepository;
    PersonneRedis unePersonneRedis;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        jedis = new Jedis(redis.getContainerIpAddress(), redis.getMappedPort(6379));
        unePersonneRedis = new PersonneRedis(idPersonne,"TOTO","Tata", UUID.randomUUID());
        jedis.set(idPersonne.toString(),unePersonneRedis.toJSON());
        personneRedisRepository = new PersonneRedisRepository(jedis);
    }

    @Test
    void save() throws IOException {
        UUID idTest = UUID.randomUUID();
        PersonneRedis personneRedis = new PersonneRedis(idTest,"NOM", "Prenom", UUID.randomUUID());
        personneRedisRepository.save(personneRedis);
        PersonneRedis personneRedisAVerifier = PersonneRedis.toObject(jedis.get(idTest.toString()));
        assertEquals(personneRedis,personneRedisAVerifier);
    }

    @Test
    void findPersonneById() {
        PersonneRedis personneRedis = personneRedisRepository.findPersonneById(idPersonne);
        assertEquals(personneRedis,unePersonneRedis);
    }
    @Test
    void findAllPersonnes() {
        List<PersonneRedis> personneRedisList = personneRedisRepository.findAllPersonnes();
        assertEquals(personneRedisList.size(),1);
    }
}