package fr.pe.test.application.config;

import fr.pe.sldng.api.parametrage.ParametreExterne;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
@ParametreExterne(nomPropriete = "url.adresse.api")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UrlAdresseApi {
}
