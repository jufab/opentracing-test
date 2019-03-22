package fr.jufab.distributed.tracing.domain.use_cases;

import fr.jufab.distributed.tracing.domain.entities.Personne;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class SavePersonneTest {
    PersonnePort personnePort = mock(PersonnePort.class);
    SavePersonne savePersonne = new SavePersonne(personnePort);
    UUID idPersonne = UUID.randomUUID();
    Personne personne = new Personne(idPersonne,"TOTO","TATA", UUID.randomUUID());

    @Test
    void execute() {
        savePersonne.execute(personne);
        verify(personnePort, times(1)).save(personne);
    }

}