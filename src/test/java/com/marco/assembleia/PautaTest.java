package com.marco.assembleia;

import com.marco.assembleia.pauta.Pauta;
import com.marco.assembleia.pauta.PautaParams;
import com.marco.assembleia.pauta.PautaService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.webjars.NotFoundException;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PautaTest {

    List<Pauta> pautas = new ArrayList<>();

    @Autowired
    private PautaService pautaService;


    private static final String NOT_FOUND_MSG = "Pauta não encontrada";

    @Test
    void createSuccess() {

        PautaParams params = new PautaParams("Pauta Teste");
        Pauta pauta = pautaService.create(params);
        pautas.add(pauta);

        Assertions.assertTrue(pauta.getId() > 0);
        Assertions.assertEquals(params.getAssunto(), pauta.getAssunto());

    }

    @Test
    void updateSuccess() {

        PautaParams params = new PautaParams("Pauta Teste");
        Pauta pauta = pautaService.create(params);

        String assuntoUpdate = "Pauta Update Success";
        PautaParams updateParams = new PautaParams(assuntoUpdate);
        Pauta pautaUpdate = pautaService.update(pauta.getId(), updateParams);
        pautas.add(pautaUpdate);

        Assertions.assertEquals(pauta.getId(), pautaUpdate.getId());
        Assertions.assertEquals(assuntoUpdate, pautaUpdate.getAssunto());
    }

    @Test
    void findByIdSuccess() {

        PautaParams params = new PautaParams("Pauta Teste");
        Pauta pauta = pautaService.create(params);

        Pauta pautaFind = pautaService.findById(pauta.getId());
        pautas.add(pautaFind);

        Assertions.assertEquals(pauta.getId(), pautaFind.getId());
        Assertions.assertEquals(pauta.getAssunto(), pautaFind.getAssunto());

    }

    @Test
    void findByIdNotFound() {
        Exception exception = Assertions.assertThrows(
                NotFoundException.class,
                () -> pautaService.findById(-1L));
        Assertions.assertEquals(NOT_FOUND_MSG, exception.getMessage());

    }

    @AfterAll
    public void clear() {
        pautas.forEach(pauta -> pautaService.delete(pauta));
    }

}
