package fr.jufab.distributed.tracing.domain.use_cases;

import fr.jufab.distributed.tracing.domain.entities.Adresse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetAdresseById {
    AdressePort adressePort;

    public GetAdresseById(AdressePort adressePort) {
        this.adressePort = adressePort;
    }

    public Adresse execute(UUID idAdresse) {
        return adressePort.getAdresseById(idAdresse);
    }
}
