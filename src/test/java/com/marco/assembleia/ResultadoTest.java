package com.marco.assembleia;

import com.marco.assembleia.exceptions.SessaoAtivaException;
import com.marco.assembleia.pauta.Pauta;
import com.marco.assembleia.pauta.PautaParams;
import com.marco.assembleia.pauta.PautaService;
import com.marco.assembleia.resultado.Resultado;
import com.marco.assembleia.resultado.ResultadoService;
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
public class ResultadoTest {

    List<Pauta> pautas = new ArrayList<>();
    List<Sessao> sessoes = new ArrayList<>();
    List<Resultado> resultados = new ArrayList<>();

    @Autowired
    private PautaService pautaService;

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private ResultadoService resultadoService;

    @Test
    void createSuccess() {

        PautaParams params = new PautaParams("Pauta Teste");
        Pauta pauta = pautaService.create(params);
        pautas.add(pauta);

        SessaoParams sessaoParams = new SessaoParams(0);
        Sessao sessao = sessaoService.create(pauta.getId(), sessaoParams);
        sessoes.add(sessao);

        Resultado resultado = resultadoService.create(sessao.getId());
        resultados.add(resultado);

        Assertions.assertTrue(resultado.getId() > 0);
        Assertions.assertEquals(sessao.getId(), resultado.getSessao().getId());

    }

    @Test
    void createNotNecessary() {

        PautaParams params = new PautaParams("Pauta Teste");
        Pauta pauta = pautaService.create(params);
        pautas.add(pauta);

        SessaoParams sessaoParams = new SessaoParams(0);
        Sessao sessao = sessaoService.create(pauta.getId(), sessaoParams);
        sessoes.add(sessao);

        Resultado resultado = resultadoService.create(sessao.getId());
        resultados.add(resultado);

        Resultado resultadoSameSessao = resultadoService.create(sessao.getId());

        Assertions.assertEquals(resultadoSameSessao.getId(), resultado.getId());
        Assertions.assertEquals(resultadoSameSessao.getSessao().getId(), resultado.getSessao().getId());

    }

    @Test
    void createFailed() {

        PautaParams params = new PautaParams("Pauta Teste");
        Pauta pauta = pautaService.create(params);
        pautas.add(pauta);

        SessaoParams sessaoParams = new SessaoParams(100);
        Sessao sessao = sessaoService.create(pauta.getId(), sessaoParams);
        sessoes.add(sessao);

        Assertions.assertThrows(
                SessaoAtivaException.class,
                () -> resultadoService.create(sessao.getId()));

    }

    @AfterAll
    public void clear() {
        resultados.forEach(resultado -> resultadoService.delete(resultado));
        sessoes.forEach(sessao -> sessaoService.delete(sessao));
        pautas.forEach(pauta -> pautaService.delete(pauta));
    }

}
