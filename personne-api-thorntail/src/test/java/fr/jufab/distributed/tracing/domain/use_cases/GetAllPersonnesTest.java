package fr.jufab.distributed.tracing.domain.use_cases;

import fr.jufab.distributed.tracing.domain.entities.Personne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


public class GetAllPersonnesTest {
    PersonnePort personnePort = mock(PersonnePort.class);
    List<Personne> personneList = new ArrayList<>();
    GetAllPersonnes getAllPersonnes = new GetAllPersonnes(personnePort);

    @BeforeEach
    public void setUp() {
        personneList.add(new Personne(UUID.randomUUID(),"Toto","Test",UUID.randomUUID()));
    }

    @Test
    public void execute() {
        given(personnePort.findAll()).willReturn(personneList);
        List<Personne> uneListeDePersonne = getAllPersonnes.execute();
        assertEquals(uneListeDePersonne,personneList);
    }
}