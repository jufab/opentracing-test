package fr.jufab.distributed.tracing;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@ApplicationPath("/")
@ApplicationScoped
public class AdresseDataGouvApplication extends Application {
}
