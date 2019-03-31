package fr.jufab.distributed.tracing.application.rest;

import fr.jufab.distributed.tracing.domain.entities.Adresse;
import fr.jufab.distributed.tracing.domain.use_cases.GetAdresseById;
import fr.jufab.distributed.tracing.domain.use_cases.SaveAdresse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/adresses",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdresseApiAdapter {

    GetAdresseById getAdresseById;
    SaveAdresse saveAdresse;


    public AdresseApiAdapter(GetAdresseById getAdresseById, SaveAdresse saveAdresse) {
        this.getAdresseById = getAdresseById;
        this.saveAdresse = saveAdresse;
    }

    @GetMapping("{idAdresse}")
    public AdresseApi getAdresse(@PathVariable UUID idAdresse) {
        return toAdresseApi(getAdresseById.execute(idAdresse));
    }

    @PostMapping
    public void saveAdresse(@RequestBody AdresseApi adresseApi) {
        saveAdresse.execute(toAdresse(adresseApi));
    }

    private AdresseApi toAdresseApi(Adresse adresse) {
        return new AdresseApi(
                adresse.getIdAdresse(),
                adresse.getLigneAdresse1(),
                adresse.getLigneAdresse2(),
                adresse.getLigneAdresse3(),
                adresse.getCodePostal(),
                adresse.getVille());
    }

    private Adresse toAdresse(AdresseApi adresseApi) {
        return new Adresse(
                adresseApi.getIdAdresse(),
                adresseApi.getLigneAdresse1(),
                adresseApi.getLigneAdresse2(),
                adresseApi.getLigneAdresse3(),
                adresseApi.getCodePostal(),
                adresseApi.getVille());
    }
}
