package com.marco.assembleia;

import com.marco.assembleia.exceptions.SessaoFinalizadaException;
import com.marco.assembleia.exceptions.UsuarioVotouException;
import com.marco.assembleia.pauta.Pauta;
import com.marco.assembleia.pauta.PautaParams;
import com.marco.assembleia.pauta.PautaService;
import com.marco.assembleia.sessao.Sessao;
import com.marco.assembleia.sessao.SessaoParams;
import com.marco.assembleia.sessao.SessaoService;
import com.marco.assembleia.voto.Voto;
import com.marco.assembleia.voto.VotoParams;
import com.marco.assembleia.voto.VotoService;
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
public class VotoTest {

    List<Pauta> pautas = new ArrayList<>();
    List<Sessao> sessoes = new ArrayList<>();
    List<Voto> votos = new ArrayList<>();

    @Autowired
    private PautaService pautaService;

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private VotoService votoService;

    @Test
    void createSuccess() {

        PautaParams params = new PautaParams("Pauta Teste");
        Pauta pauta = pautaService.create(params);
        pautas.add(pauta);

        SessaoParams sessaoParams = new SessaoParams(100);
        Sessao sessao = sessaoService.create(pauta.getId(), sessaoParams);
        sessoes.add(sessao);

        VotoParams votoParams = new VotoParams(100, true);
        Voto voto = votoService.create(sessao.getId(), votoParams);
        votos.add(voto);

        Assertions.assertTrue(voto.getId() > 0);
        Assertions.assertEquals(sessao.getId(), voto.getSessao().getId());
        Assertions.assertEquals(votoParams.getVoto(), voto.getVoto());

    }

    @Test
    void createUserVotedException() {

        PautaParams params = new PautaParams("Pauta Teste");
        Pauta pauta = pautaService.create(params);
        pautas.add(pauta);

        SessaoParams sessaoParams = new SessaoParams(100);
        Sessao sessao = sessaoService.create(pauta.getId(), sessaoParams);
        sessoes.add(sessao);

        VotoParams votoParams = new VotoParams(100, true);
        Voto voto = votoService.create(sessao.getId(), votoParams);
        votos.add(voto);

        Assertions.assertThrows(
                UsuarioVotouException.class,
                () -> votoService.create(sessao.getId(), votoParams));

    }

    @Test
    void createSessionFinished() {

        PautaParams params = new PautaParams("Pauta Teste");
        Pauta pauta = pautaService.create(params);
        pautas.add(pauta);

        SessaoParams sessaoParams = new SessaoParams(0);
        Sessao sessao = sessaoService.create(pauta.getId(), sessaoParams);
        sessoes.add(sessao);

        VotoParams votoParams = new VotoParams(100, true);
        Assertions.assertThrows(
                SessaoFinalizadaException.class,
                () -> votoService.create(sessao.getId(), votoParams));

    }

    @AfterAll
    public void clear() {
        votos.forEach(voto -> votoService.delete(voto));
        sessoes.forEach(sessao -> sessaoService.delete(sessao));
        pautas.forEach(pauta -> pautaService.delete(pauta));
    }

}
