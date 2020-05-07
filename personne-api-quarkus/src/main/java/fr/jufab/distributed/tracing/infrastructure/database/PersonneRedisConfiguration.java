package fr.jufab.distributed.tracing.infrastructure.database;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import redis.clients.jedis.Jedis;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class PersonneRedisConfiguration {
    String redisUrl;
    String redisPort;

    public PersonneRedisConfiguration(@ConfigProperty(name="redis.host", defaultValue = "localhost") String redisUrl, @ConfigProperty(name="redis.port", defaultValue = "6379") String redisPort) {
        this.redisUrl = redisUrl;
        this.redisPort = redisPort;
    }

    @Dependent
    @Produces
    public Jedis get() {
        Jedis jedis = new Jedis(redisUrl, Integer.valueOf(redisPort), 10000);
        jedis.connect();
        return jedis;
    }

    public void destroy(@Disposes Jedis jedis) {
        jedis.close();
    }
}
