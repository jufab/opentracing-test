package fr.pe.test.application.tracing;

import fr.pe.sldng.api.parametrage.ParametreExterne;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
@ParametreExterne(
        nomPropriete = "sldng.tracing.agent.port"
)
@Target({ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface TracingAgentPort {
}
