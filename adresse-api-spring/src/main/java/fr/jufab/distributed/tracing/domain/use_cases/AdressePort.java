package fr.jufab.distributed.tracing.domain.use_cases;

import fr.jufab.distributed.tracing.domain.entities.Adresse;

import java.util.UUID;

public interface AdressePort {
    void save(Adresse adresse);
    Adresse getAdresseById(UUID idAdresse);
}
