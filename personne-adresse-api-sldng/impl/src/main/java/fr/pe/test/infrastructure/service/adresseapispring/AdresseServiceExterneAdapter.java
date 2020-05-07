package fr.pe.test.infrastructure.service.adresseapispring;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.net.URI;

public class AdresseServiceExterneAdapter {
    private static final String V_1_ADRESSES = "/v1/adresses";

    URI urlAdressesApiSpring;

    public AdresseServiceExterneAdapter(URI urlAdressesApiSpring) {
        this.urlAdressesApiSpring = urlAdressesApiSpring;
    }

    private Client getClient() {
        //return Client client = ClientTracingRegistrar.configure(ClientBuilder.newBuilder()).build();
        return ClientBuilder.newBuilder().build();
    }

    public AdresseServiceApi findAdresse(String idAdresse) {
        //Span span = tracer.buildSpan("adresse-api").start();
        Client client = getClient();
        try {
            return client.target(this.urlAdressesApiSpring)
                    .path(V_1_ADRESSES + "/" + idAdresse)
                    .request(MediaType.APPLICATION_JSON)
                    .get(AdresseServiceApi.class);
        } finally {
            client.close();
            //span.finish();
        }
    }

    public AdresseServiceApi saveAdresse(AdresseServiceApi adresseServiceApi) {
        Client client = getClient();
        try {
            return client.target(this.urlAdressesApiSpring)
                    .path(V_1_ADRESSES)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(adresseServiceApi, MediaType.APPLICATION_JSON),AdresseServiceApi.class);
        } finally {
            client.close();
        }
    }

}
