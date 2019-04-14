package fr.pe.test.infrastructure.service.personneapithorntail;

import io.opentracing.contrib.jaxrs2.client.ClientTracingFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.List;
import java.util.UUID;

public class PersonneServiceExterneAdapter {
    private static final String V_1_PERSONNES = "/personnes";

    URI urlPersonneApiThorntail;

    public PersonneServiceExterneAdapter(URI urlPersonneApiThorntail) {
        this.urlPersonneApiThorntail = urlPersonneApiThorntail;
    }

    public List<PersonneServiceApi> findAllPersonnes() {
        Client client = ClientBuilder.newBuilder().register(ClientTracingFeature.class).build();
        try {
            return client.target(this.urlPersonneApiThorntail)
                    .path(V_1_PERSONNES)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<PersonneServiceApi>>() {
                    });
        } finally {
            client.close();
        }
    }

    public PersonneServiceApi findPersonneById(UUID idPersonne) {
        Client client = ClientBuilder.newBuilder().register(ClientTracingFeature.class).build();
        //Client client = ClientTracingRegistrar.configure(ClientBuilder.newBuilder()).build();
        try {
            return client.target(this.urlPersonneApiThorntail)
                    .path(V_1_PERSONNES + "/" + idPersonne)
                    .request(MediaType.APPLICATION_JSON)
                    .get(PersonneServiceApi.class);
        } finally {
            client.close();
        }
    }

    public PersonneServiceApi savePersonne(PersonneServiceApi personneServiceApi) {
        Client client = ClientBuilder.newBuilder().register(ClientTracingFeature.class).build();
        try {
            return client.target(this.urlPersonneApiThorntail)
                    .path(V_1_PERSONNES)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(personneServiceApi, MediaType.APPLICATION_JSON),PersonneServiceApi.class);
        } finally {
            client.close();
        }
    }


}