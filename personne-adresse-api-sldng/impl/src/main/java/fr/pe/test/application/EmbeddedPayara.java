package fr.pe.test.application;

import fish.payara.micro.BootstrapException;
import fish.payara.micro.PayaraMicro;

public class EmbeddedPayara {

    public static void main(String[] args) throws
            BootstrapException {
        PayaraMicro.getInstance()
                .addDeployment("impl/target/personne-adresse-api-sldng.war")
                .bootStrap();
    }
}
