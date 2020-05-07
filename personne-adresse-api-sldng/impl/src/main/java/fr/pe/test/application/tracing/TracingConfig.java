package fr.pe.test.application.tracing;

import fr.pe.sldng.api.chargement.PreChargement;
//import io.jaegertracing.Configuration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@PreChargement
public class TracingConfig {

    private String serviceName;
    private String serviceHost;
    private String servicePort;

    public TracingConfig() {
    }

    @Inject
    public TracingConfig(@TracingAgentHost String serviceHost,
                         @TracingServiceName String serviceName,
                         @TracingAgentPort String servicePort) {
        this.serviceHost = serviceHost;
        this.serviceName = serviceName;
        this.servicePort = servicePort;
    }

    @PostConstruct
    void initConfigTracer() {
/*        System.setProperty(Configuration.JAEGER_SERVICE_NAME,this.serviceName);
        System.setProperty(Configuration.JAEGER_AGENT_HOST,this.serviceHost);
        System.setProperty(Configuration.JAEGER_AGENT_PORT,this.servicePort);
        System.setProperty(Configuration.JAEGER_SAMPLER_TYPE,"const");
        System.setProperty(Configuration.JAEGER_SAMPLER_PARAM, "1");
        System.setProperty(Configuration.JAEGER_REPORTER_LOG_SPANS, "true");*/
    }

}
