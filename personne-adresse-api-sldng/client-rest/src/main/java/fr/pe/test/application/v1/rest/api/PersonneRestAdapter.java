package fr.pe.test.application.v1.rest.api;

import fr.pe.sldng.api.integration.rest.OperationRestSLD;
import fr.pe.test.application.v1.rest.ressource.PersonneAPI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("/facade/personnes")
public interface PersonneRestAdapter {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @OperationRestSLD(nom="getAllPersonnes")
    List<PersonneAPI> getAllPersonnes();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @OperationRestSLD(nom="getPersonne")
    @Path("/{id}")
    PersonneAPI getPersonne(@PathParam("id") UUID idPersonne);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @OperationRestSLD(nom="savePersonne")
    PersonneAPI savePersonne(PersonneAPI personneAPI);

}
