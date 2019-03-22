package fr.jufab.distributed.tracing.infrastructure.database;

import fr.jufab.distributed.tracing.AbstractTestContainerRedis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import static org.junit.jupiter.api.Assertions.*;

class PersonneRedisConfigurationTest extends AbstractTestContainerRedis {

    private PersonneRedisConfiguration personneRedisConfiguration;

    @BeforeEach
    void setUp() {
        personneRedisConfiguration = new PersonneRedisConfiguration(redis.getContainerIpAddress(), redis.getMappedPort(6379).toString());
    }

    @Test
    void get() {
        Jedis jedis = personneRedisConfiguration.get();
        assertNotNull(jedis.info());
    }

    @Test
    void destroy() {
        Jedis jedis = personneRedisConfiguration.get();
        personneRedisConfiguration.destroy(jedis);
    }
}