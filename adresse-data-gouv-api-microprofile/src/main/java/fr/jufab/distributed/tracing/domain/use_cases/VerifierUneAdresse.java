package fr.jufab.distributed.tracing.domain.use_cases;

import fr.jufab.distributed.tracing.domain.entities.Adresse;

import javax.inject.Inject;
import java.util.List;

public class VerifierUneAdresse {
    AdressesPort adressesPort;

    @Inject
    public VerifierUneAdresse(AdressesPort adressesPort) {
        this.adressesPort = adressesPort;
    }

    public List<Adresse> execute(Adresse uneAdresse) {
        return this.adressesPort.verifierUneAdresse(uneAdresse);
    }
}
