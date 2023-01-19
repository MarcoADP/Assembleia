package com.marco.assembleia.unidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.marco.assembleia.exceptions.SessaoFinalizadaException;
import com.marco.assembleia.exceptions.UsuarioVotouException;
import com.marco.assembleia.pauta.Pauta;
import com.marco.assembleia.sessao.Sessao;
import com.marco.assembleia.sessao.SessaoService;
import com.marco.assembleia.voto.Voto;
import com.marco.assembleia.voto.VotoParams;
import com.marco.assembleia.voto.VotoRepository;
import com.marco.assembleia.voto.VotoService;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VotoTest {

    private final VotoRepository votoRepository = Mockito.mock(VotoRepository.class);
    private final SessaoService sessaoService = Mockito.mock(SessaoService.class);

    private VotoService votoService;

    @BeforeEach
    void initUseCase() {
        votoService = new VotoService(votoRepository, sessaoService);
    }

    @Test
    void createSuccess() {

        Pauta pauta = new Pauta(100L, "Pauta Teste");
        Sessao sessao = new Sessao(pauta, 10, LocalDateTime.now());
        Voto voto = new Voto(100L, 100, sessao, true);

        when(votoRepository.save(Mockito.any())).thenReturn(voto);

        Voto votoSaved = votoService.create(pauta.getId(), new VotoParams(100, true));
        assertEquals(votoSaved.getId(), voto.getId());
        assertEquals(votoSaved.getSessao().getId(), voto.getSessao().getId());
        assertEquals(votoSaved.getUsuarioId(), voto.getUsuarioId());
        assertEquals(votoSaved.getVoto(), voto.getVoto());
    }

    @Test
    void createUserVotedException() {

        List<Voto> votos = List.of(new Voto(100, new Sessao(), true));
        when(votoRepository.findByUsuarioIdAndSessao(Mockito.any(), Mockito.any())).thenReturn(votos);

        Assertions.assertThrows(
                UsuarioVotouException.class,
                () -> votoService.create(100L, new VotoParams(100, true)));

    }

    @Test
    void createSessionFinished() {
        doThrow(new SessaoFinalizadaException("")).doNothing().when(sessaoService).checkSessaoFinalizada(Mockito.any());

        Assertions.assertThrows(
                SessaoFinalizadaException.class,
                () -> votoService.create(100L, new VotoParams(100, true)));

    }

    @Test
    public void findBySessao() {

        Pauta pauta = new Pauta(100L, "Pauta Teste");
        Sessao sessao = new Sessao(pauta, 10, LocalDateTime.now());
        Voto voto = new Voto(100L, 100, sessao, true);
        when(votoRepository.findBySessao(Mockito.any())).thenReturn(List.of(voto));

        List<Voto> votos = votoService.findBySessao(sessao);

        assertEquals(votos.size(), 1);
        assertEquals(votos.get(0).getId(), voto.getId());
        assertEquals(votos.get(0).getUsuarioId(), voto.getUsuarioId());

    }
}
