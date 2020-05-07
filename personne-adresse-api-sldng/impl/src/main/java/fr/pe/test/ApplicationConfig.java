// -----------------------------------------------------------------------------
// Projet : NetAdmin
// Client : Pôle Emploi
// Auteur : OPA
// -----------------------------------------------------------------------------
package fr.pe.test;

import fr.pe.sldng.api.integration.rest.RestSLDFeature;
import fr.pe.test.application.v1.rest.PersonneRestAdapterImpl;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@ApplicationPath("/")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        // Ne pas oublier d'activer cette feature dans le cas d'une façade avec sécurité
        //classes.add(SecuriteRestSLDFeature.class);
        // Activation interception REST pour intégration SI PE
        classes.add(RestSLDFeature.class);
        // Déclaratif des classes exposant des ressources REST dans l'application
        classes.add(PersonneRestAdapterImpl.class);
        return classes;
    }


}