package fr.jufab.distributed.tracing.infrastructure.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import fr.jufab.distributed.tracing.domain.entities.Adresse;
import fr.jufab.distributed.tracing.domain.use_cases.AdressesPort;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.opentracing.ClientTracingRegistrar;
import org.geojson.FeatureCollection;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AdressesServiceAdapter implements AdressesPort {

    static String CONTEXT_SEARCH = "search/";

    private String urlAdresseDataGouv;

    private Tracer tracer;

    private boolean useProxyPE;


    @Inject
    public AdressesServiceAdapter(@ConfigProperty(name = "url.api.adresse.base.nationale", defaultValue = "https://api-adresse.data.gouv.fr/") String urlAdresseDataGouv,
                                  @ConfigProperty(name = "use.proxy.pole-emploi", defaultValue = "false") Boolean useProxyPE,
                                  Tracer tracer) {
        this.tracer = tracer;
        this.urlAdresseDataGouv = urlAdresseDataGouv.trim();
        this.useProxyPE = useProxyPE;
    }

    @Override
    public List<Adresse> getDesAdressesAPartirDuNomDeLAdresse(String nomAdresse) {
        Span span = tracer.buildSpan("service-data-gouv").start();
        span.log("Appel via URL : " + this.urlAdresseDataGouv);
        span.setTag("nomAdresse", nomAdresse);

        Client client = getClient();

        String reponse;
        try {
            reponse = client.target(this.urlAdresseDataGouv)
                    .path(CONTEXT_SEARCH)
                    .queryParam("q", nomAdresse)
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);
        } finally {
            client.close();
        }
        span.setTag("Reponse", reponse);
        span.finish();
        if (!Strings.isNullOrEmpty(reponse)) {
            return toListAdresseFromJson(reponse);
        } else {
            return null;
        }
    }

    @Override
    public List<Adresse> verifierUneAdresse(Adresse adresse) {
        Client client = getClient();
        String reponse;
        try {
            reponse = client.target(this.urlAdresseDataGouv)
                    .path(CONTEXT_SEARCH)
                    .queryParam("q", adresse.getNomAdresse())
                    .queryParam("postCode", adresse.getCodePostal())
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);
        } finally {
            client.close();
        }
        if (!Strings.isNullOrEmpty(reponse)) {
            return toListAdresseFromJson(reponse);
        } else {
            return null;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private List<Adresse> toListAdresseFromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        FeatureCollection featureCollection = null;
        try {
            featureCollection = objectMapper.readValue(json, FeatureCollection.class);
            return featureCollection.getFeatures().stream().map(feature -> {
                Adresse adresse = new Adresse((String) feature.getProperties().get("name"),
                        (String) feature.getProperties().get("city"),
                        (String) feature.getProperties().get("postcode"));
                return adresse;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            //LOGGER.error(e.getMessage(),e);
            return null;
        }
    }

    private Client getClient() {
        ClientConfig clientConfig = new ClientConfig();

        if(useProxyPE)
            clientConfig.property(ClientProperties.PROXY_URI, "http://proxyaws.pole-emploi.intra:8080/");

        return ClientTracingRegistrar
                .configure(ClientBuilder.newBuilder().withConfig(clientConfig))
                .build();
    }
}
