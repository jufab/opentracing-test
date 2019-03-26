package fr.jufab.distributed.tracing.infrastructure.database;

import fr.jufab.distributed.tracing.domain.entities.Personne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PersonneRedisAdapterTest {

    PersonneRedisRepository personneRedisRepository = mock(PersonneRedisRepository.class);
    PersonneRedisAdapter personneRedisAdapter;
    PersonneRedis personneRedis;
    Personne personne;
    List<PersonneRedis> personneRedisList = new ArrayList<>();
    List<Personne> personneList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        personneRedisAdapter = new PersonneRedisAdapter(personneRedisRepository);
        personneRedis = new PersonneRedis(UUID.randomUUID(),"TOTO", "Tata", UUID.randomUUID());
        personne = new Personne(personneRedis.getIdPersonne(),personneRedis.getNom(), personneRedis.getPrenom(), personneRedis.getIdAdresse());
        personneRedisList.add(personneRedis);
        personneList.add(personne);
    }

    @Test
    void save() {
        this.personneRedisAdapter.save(personne);
        verify(personneRedisRepository).save(personneRedis);
    }

    @Test
    void findAll() {
        given(this.personneRedisRepository.findAllPersonnes()).willReturn(personneRedisList);
        List<Personne> listeDePersonne = this.personneRedisAdapter.findAll();
        assertEquals(listeDePersonne,personneList);
    }

    @Test
    void findById() {
        given(this.personneRedisRepository.findPersonneById(this.personneRedis.getIdPersonne())).willReturn(this.personneRedis);
        Personne unePersonne = this.personneRedisAdapter.findById(this.personne.getIdPersonne());
        assertEquals(unePersonne,personne);
    }
}