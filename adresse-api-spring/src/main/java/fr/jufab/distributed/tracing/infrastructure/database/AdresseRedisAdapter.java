package fr.jufab.distributed.tracing.infrastructure.database;

import fr.jufab.distributed.tracing.domain.entities.Adresse;
import fr.jufab.distributed.tracing.domain.use_cases.AdressePort;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class AdresseRedisAdapter implements AdressePort {

    AdresseRedisRepository adresseRedisRepository;

    public AdresseRedisAdapter(AdresseRedisRepository adresseRedisRepository) {
        this.adresseRedisRepository = adresseRedisRepository;
    }

    @NewSpan("redis")
    @Override
    public void save(Adresse adresse) {
        adresseRedisRepository.save(this.toAdresseRedis(adresse));
    }

    @NewSpan("redis")
    @Override
    public Adresse getAdresseById(UUID idAdresse) {
        return adresseRedisRepository.findById(idAdresse.toString()).map(a -> toAdresse(a)).orElse(new Adresse());
    }

    private Adresse toAdresse(AdresseRedis adresseRedis) {
        return new Adresse(UUID.fromString(adresseRedis.getIdAdresse()),adresseRedis.getLigneAdresse1(),adresseRedis.getLigneAdresse2(),adresseRedis.getLigneAdresse3(),adresseRedis.getCodePostal(),adresseRedis.getVille());
    }

    private AdresseRedis toAdresseRedis(Adresse adresse) {
        return new AdresseRedis(adresse.getIdAdresse().toString(),adresse.getLigneAdresse1(),adresse.getLigneAdresse2(),adresse.getLigneAdresse3(),adresse.getCodePostal(),adresse.getVille());
    }
}
