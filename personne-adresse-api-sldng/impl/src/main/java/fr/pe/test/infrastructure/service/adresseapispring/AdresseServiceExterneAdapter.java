package fr.pe.test.infrastructure.service.adresseapispring;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.UUID;

public class AdresseServiceExterneAdapter {
    private static final String V_1_ADRESSES = "/v1/adresses";

    URI urlAdressesApiSpring;

    public AdresseServiceExterneAdapter(URI urlAdressesApiSpring) {
        this.urlAdressesApiSpring = urlAdressesApiSpring;
    }

    public AdresseServiceApi findAdresse(UUID idAdresse) {
        Client client = ClientBuilder.newBuilder().build();
        //Client client = ClientTracingRegistrar.configure(ClientBuilder.newBuilder()).build();
        try {
            return client.target(this.urlAdressesApiSpring)
                    .path(V_1_ADRESSES + "/" + idAdresse)
                    .request(MediaType.APPLICATION_JSON)
                    .get(AdresseServiceApi.class);
        } finally {
            client.close();
        }
    }

    public AdresseServiceApi saveAdresse(AdresseServiceApi adresseServiceApi) {
        Client client = ClientBuilder.newBuilder().build();
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
