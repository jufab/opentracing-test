package fr.jufab.distributed.tracing;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.GenericContainer;

public class AbstractTestContainerRedis {

    @Rule
    public GenericContainer redis;

    @BeforeEach
    void setUpRedisContainer() {
        redis = new GenericContainer("redis:latest").withExposedPorts(6379);
        redis.start();
    }

}
