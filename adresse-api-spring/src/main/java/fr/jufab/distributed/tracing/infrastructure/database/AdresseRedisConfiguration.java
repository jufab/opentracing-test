package fr.jufab.distributed.tracing.infrastructure.database;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@ComponentScan("fr.jufab.distributed.tracing.infrastructure.database")
@EnableRedisRepositories(basePackages = "fr.jufab.distributed.tracing.infrastructure.database")
public class AdresseRedisConfiguration {
    @Value("${application.redis.host}")
    private String host;
    @Value("${application.redis.port}")
    private int port;

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        logger.info("Host REDIS : " + host + " , Port REDIS : " + port);
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host,port);
        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.defaultConfiguration();
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfiguration);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }
}
