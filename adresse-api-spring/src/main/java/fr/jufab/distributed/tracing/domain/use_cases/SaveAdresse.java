package fr.jufab.distributed.tracing.domain.use_cases;

import fr.jufab.distributed.tracing.domain.entities.Adresse;
import org.springframework.stereotype.Component;

@Component
public class SaveAdresse {

    AdressePort adressePort;

    public SaveAdresse(AdressePort adressePort) {
        this.adressePort = adressePort;
    }

    public void execute(Adresse adresse) {
        adressePort.save(adresse);
    }
}
