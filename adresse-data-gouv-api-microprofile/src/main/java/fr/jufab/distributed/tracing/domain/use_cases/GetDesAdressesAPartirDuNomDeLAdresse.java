package fr.jufab.distributed.tracing.domain.use_cases;

import fr.jufab.distributed.tracing.domain.entities.Adresse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GetDesAdressesAPartirDuNomDeLAdresse {

    AdressesPort adressesPort;

    GetDesAdressesAPartirDuNomDeLAdresse() {
    }

    @Inject
    public GetDesAdressesAPartirDuNomDeLAdresse(AdressesPort adressesPort) {
        this.adressesPort = adressesPort;
    }

    public List<Adresse> execute(String nomAdresse) {
        return this.adressesPort.getDesAdressesAPartirDuNomDeLAdresse(nomAdresse);
    }

}
