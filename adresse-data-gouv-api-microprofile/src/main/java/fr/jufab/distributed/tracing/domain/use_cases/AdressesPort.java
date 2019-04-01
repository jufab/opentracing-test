package fr.jufab.distributed.tracing.domain.use_cases;

import fr.jufab.distributed.tracing.domain.entities.Adresse;

import java.util.List;

public interface AdressesPort {
    List<Adresse> getDesAdressesAPartirDuNomDeLAdresse(String nomAdresse);
    List<Adresse> verifierUneAdresse(Adresse adresse);
}
