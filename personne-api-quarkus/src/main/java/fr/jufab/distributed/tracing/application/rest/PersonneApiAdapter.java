package fr.jufab.distributed.tracing.application.rest;

import fr.jufab.distributed.tracing.domain.entities.Personne;
import fr.jufab.distributed.tracing.domain.use_cases.GetAllPersonnes;
import fr.jufab.distributed.tracing.domain.use_cases.GetPersonne;
import fr.jufab.distributed.tracing.domain.use_cases.SavePersonne;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/personnes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class PersonneApiAdapter {

    private GetAllPersonnes getAllPersonnes;
    private GetPersonne getPersonne;
    private SavePersonne savePersonne;

    public PersonneApiAdapter(GetAllPersonnes getAllPersonnes, GetPersonne getPersonne, SavePersonne savePersonne) {
        this.getAllPersonnes = getAllPersonnes;
        this.getPersonne = getPersonne;
        this.savePersonne = savePersonne;
    }

    @GET
    public List<PersonneAPI> getAllPersonnes() {
        List<Personne> listeDePersonne = this.getAllPersonnes.execute();
        if(listeDePersonne != null)
            return listeDePersonne.stream().map(this::toPersonneAPI).collect(Collectors.toList());
        return null;
    }

    @GET
    @Path("/{id}")
    public PersonneAPI getPersonne(@PathParam("id") UUID id) {
        return toPersonneAPI(this.getPersonne.execute(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void savePersonne(PersonneAPI personneAPI) {
        savePersonne.execute(toPersonne(personneAPI));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePersonne(PersonneAPI personneAPI) {
        savePersonne.execute(toPersonne(personneAPI));
    }

    private Personne toPersonne(PersonneAPI personneAPI) {
        return new Personne(personneAPI.getIdPersonne(),personneAPI.getNom(),personneAPI.getPrenom(),personneAPI.getIdAdresse());
    }

    private PersonneAPI toPersonneAPI(Personne personne) {
        return new PersonneAPI(personne.getIdPersonne(),personne.getNom(),personne.getPrenom(),personne.getAdresse());
    }

}
