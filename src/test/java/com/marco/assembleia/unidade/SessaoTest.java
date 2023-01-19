package com.marco.assembleia.unidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.marco.assembleia.exceptions.PautaComSessaoException;
import com.marco.assembleia.pauta.Pauta;
import com.marco.assembleia.pauta.PautaService;
import com.marco.assembleia.sessao.Sessao;
import com.marco.assembleia.sessao.SessaoParams;
import com.marco.assembleia.sessao.SessaoRepository;
import com.marco.assembleia.sessao.SessaoService;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SessaoTest {

    private final SessaoRepository sessaoRepository = Mockito.mock(SessaoRepository.class);
    private final PautaService pautaService = Mockito.mock(PautaService.class);

    private SessaoService sessaoService;

    @BeforeEach
    void initUseCase() {
        sessaoService = new SessaoService(sessaoRepository, pautaService);
    }

    @Test
    void createSuccess() {

        Pauta pauta = new Pauta(100L, "Pauta Teste");
        Sessao sessao = new Sessao(pauta, 10, LocalDateTime.now());

        when(sessaoRepository.save(Mockito.any())).thenReturn(sessao);

        Sessao sessaoSaved = sessaoService.create(pauta.getId(), new SessaoParams(10));
        assertEquals(sessaoSaved.getId(), sessao.getId());
        assertEquals(sessaoSaved.getPauta().getId(), sessao.getPauta().getId());
        assertEquals(sessaoSaved.getDuracao(), sessao.getDuracao());
        assertEquals(sessaoSaved.getFim(), sessao.getFim());
    }

    @Test
    void createFailed() {

        when(sessaoRepository.findFirstByPauta(Mockito.any())).thenReturn(new Sessao());
        when(pautaService.findById(Mockito.any())).thenReturn(new Pauta(100L , "Pauta Teste"));

        Assertions.assertThrows(
                PautaComSessaoException.class,
                () -> sessaoService.create(100L, new SessaoParams()));

    }

    @Test
    public void findByPauta() {

        Pauta pauta = new Pauta(100L, "Pauta Teste");
        Sessao sessao = new Sessao(pauta, 10, LocalDateTime.now());
        when(sessaoRepository.findFirstByPauta(Mockito.any())).thenReturn(sessao);

        Optional<Sessao> sessaoOptional = sessaoService.findByPauta(pauta);
        assertTrue(sessaoOptional.isPresent());
        assertEquals(sessaoOptional.get().getId(), sessao.getId());
        assertEquals(sessaoOptional.get().getPauta().getId(), sessao.getPauta().getId());

    }

    @Test
    public void findByPautaFailed() {

        when(sessaoRepository.findFirstByPauta(Mockito.any())).thenReturn(null);
        Optional<Sessao> sessaoOptional = sessaoService.findByPauta(new Pauta());
        assertFalse(sessaoOptional.isPresent());

    }
}
