package com.marco.assembleia;

import com.marco.assembleia.exceptions.PautaComSessaoException;
import com.marco.assembleia.pauta.Pauta;
import com.marco.assembleia.pauta.PautaParams;
import com.marco.assembleia.pauta.PautaService;
import com.marco.assembleia.sessao.Sessao;
import com.marco.assembleia.sessao.SessaoParams;
import com.marco.assembleia.sessao.SessaoService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SessaoTest {

    List<Sessao> sessoes = new ArrayList<>();
    List<Pauta> pautas = new ArrayList<>();

    @Autowired
    private PautaService pautaService;

    @Autowired
    private SessaoService sessaoService;

    @Test
    void createSuccess() {

        PautaParams params = new PautaParams("Pauta Create Success");
        Pauta pauta = pautaService.create(params);
        pautas.add(pauta);

        SessaoParams sessaoParams = new SessaoParams(100);
        Sessao sessao = sessaoService.create(pauta.getId(), sessaoParams);
        sessoes.add(sessao);

        Assertions.assertTrue(sessao.getId() > 0);
        Assertions.assertEquals(pauta.getId(), sessao.getPauta().getId());
        Assertions.assertEquals(sessaoParams.getDuracao(), sessao.getDuracao());

    }

    @Test
    void createFailed() {

        PautaParams params = new PautaParams("Pauta Create Success");
        Pauta pauta = pautaService.create(params);
        pautas.add(pauta);

        SessaoParams sessaoParams = new SessaoParams(100);
        Sessao sessao = sessaoService.create(pauta.getId(), sessaoParams);
        sessoes.add(sessao);

        Assertions.assertThrows(
                PautaComSessaoException.class,
                () -> sessaoService.create(pauta.getId(), sessaoParams));

    }

    @AfterAll
    public void clearPautas() {
        sessoes.forEach(sessao -> sessaoService.delete(sessao));
        pautas.forEach(pauta -> pautaService.delete(pauta));
    }

}
