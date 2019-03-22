package fr.jufab.distributed.tracing.domain.use_cases;

import fr.jufab.distributed.tracing.domain.entities.Personne;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPersonneTest {
    PersonnePort personnePort = mock(PersonnePort.class);
    GetPersonne getPersonne = new GetPersonne(personnePort);
    UUID idPersonne = UUID.randomUUID();
    Personne personne = new Personne(idPersonne,"Toto", "Tata", UUID.randomUUID());
    @Test
    void execute() {
        given(personnePort.findById(idPersonne)).willReturn(personne);
        Personne unePersonne = getPersonne.execute(idPersonne);
        assertEquals(unePersonne,personne);
    }
}