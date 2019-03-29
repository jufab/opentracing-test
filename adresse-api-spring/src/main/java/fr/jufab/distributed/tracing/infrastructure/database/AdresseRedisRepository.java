package fr.jufab.distributed.tracing.infrastructure.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRedisRepository extends CrudRepository<AdresseRedis, String> {}
