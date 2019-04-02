package fr.jufab.distributed.tracing.application.rest;

import fr.jufab.distributed.tracing.application.rest.ressource.AdresseAPI;
import fr.jufab.distributed.tracing.domain.entities.Adresse;
import fr.jufab.distributed.tracing.domain.use_cases.GetDesAdressesAPartirDuNomDeLAdresse;
import org.eclipse.microprofile.opentracing.Traced;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/v1/adresses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdresseRestAdapter {


    @Inject
    GetDesAdressesAPartirDuNomDeLAdresse getDesAdressesAPartirDuNomDeLAdresse;

    @GET
    @Traced(operationName = "GET Adresses")
    public List<AdresseAPI> getAdresses(@QueryParam("q") String query) {
        return toListAdresseAPI(getDesAdressesAPartirDuNomDeLAdresse.execute(query));
    }

    private List<AdresseAPI> toListAdresseAPI(List<Adresse> adresseList) {
        List<AdresseAPI> adresses= new ArrayList<>();
        if(adresseList != null) {
            adresseList.stream().forEach(adresse -> {
                AdresseAPI adresseAPI = new AdresseAPI(adresse.getNomAdresse(),adresse.getVille(),adresse.getCodePostal());
                adresses.add(adresseAPI);
            });
        }
        return adresses;
    }
}
