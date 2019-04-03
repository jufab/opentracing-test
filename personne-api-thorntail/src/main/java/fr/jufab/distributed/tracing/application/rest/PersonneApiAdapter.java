package fr.jufab.distributed.tracing.application.rest;

import fr.jufab.distributed.tracing.domain.entities.Personne;
import fr.jufab.distributed.tracing.domain.use_cases.GetAllPersonnes;
import fr.jufab.distributed.tracing.domain.use_cases.GetPersonne;
import fr.jufab.distributed.tracing.domain.use_cases.SavePersonne;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/personnes")
@Api(value="/personne" , description = "Operations sur les personnes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class PersonneApiAdapter {

    @Inject GetAllPersonnes getAllPersonnes;
    @Inject GetPersonne getPersonne;
    @Inject SavePersonne savePersonne;


    @GET
    @ApiOperation(value = "Retourne toutes les personnes",
            notes = "Retourne une liste au format json des personnes",
            response = PersonneAPI.class,
            responseContainer = "list"
    )
    public List<PersonneAPI> getAllPersonnes() {
        List<Personne> listeDePersonne = this.getAllPersonnes.execute();
        if(listeDePersonne != null)
            return listeDePersonne.stream().map(this::toPersonneAPI).collect(Collectors.toList());
        return null;
    }

    @GET
    @Path("/{id}")
    @ApiOperation(value = "retourne une personne à partir de son UUID", response = PersonneAPI.class)
    public PersonneAPI getPersonne(
            @ApiParam(value = "UUID de la personne recherché", required = true)
            @PathParam("id") UUID id) {
        return toPersonneAPI(this.getPersonne.execute(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Enregistre une personne")
    public void savePersonne(@ApiParam(value = "une personne",required = true) PersonneAPI personneAPI) {
        savePersonne.execute(toPersonne(personneAPI));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Modifie une personne")
    public void updatePersonne(@ApiParam(value = "une personne",required = true) PersonneAPI personneAPI) {
        savePersonne.execute(toPersonne(personneAPI));
    }

    private Personne toPersonne(PersonneAPI personneAPI) {
        return new Personne(personneAPI.getIdPersonne(),personneAPI.getNom(),personneAPI.getPrenom(),personneAPI.getIdAdresse());
    }

    private PersonneAPI toPersonneAPI(Personne personne) {
        return new PersonneAPI(personne.getIdPersonne(),personne.getNom(),personne.getPrenom(),personne.getAdresse());
    }

}
