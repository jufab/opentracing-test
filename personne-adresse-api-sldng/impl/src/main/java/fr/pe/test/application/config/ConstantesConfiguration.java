// -----------------------------------------------------------------------------
// Projet : NetAdmin
// Client : Pôle Emploi
// Auteur : OPA
// -----------------------------------------------------------------------------
package fr.pe.test.application.config;

import javax.enterprise.inject.Produces;

import fr.pe.sldng.api.integration.annotation.NomDomaine;
import fr.pe.sldng.api.integration.annotation.NomFichierEntetes;
import fr.pe.sldng.api.parametrage.NomFichierProprietes;

/**
 * Constantes de configuration obligatoire à associer à un domaine
 */
public final class ConstantesConfiguration
{
   /** Nom du fichier de propriétés */
   @Produces
   @NomFichierProprietes
   public static final String NOM_FICHIER_PROPRIETE = "domaine.properties";

   /** Nom de domaine */ 
   @Produces
   @NomDomaine
   public static final String NOM_DOMAINE = "personne-adresse-api-sldng";

   /** Nom de domaine */
   @Produces
   @NomFichierEntetes
   public static final String NOM_FICHIER_ENTETES = "entetes.properties";




   /**
    * Constructeur
    */
   private ConstantesConfiguration()
   {
      // Classe utilitaire
   }
}
