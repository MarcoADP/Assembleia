package com.marco.assembleia.unidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.marco.assembleia.exceptions.SessaoAtivaException;
import com.marco.assembleia.pauta.Pauta;
import com.marco.assembleia.resultado.Resultado;
import com.marco.assembleia.resultado.ResultadoRepository;
import com.marco.assembleia.resultado.ResultadoService;
import com.marco.assembleia.sessao.Sessao;
import com.marco.assembleia.sessao.SessaoService;
import com.marco.assembleia.voto.VotoService;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ResultadoTest {

    private final ResultadoRepository resultadoRepository = Mockito.mock(ResultadoRepository.class);
    private final SessaoService sessaoService = Mockito.mock(SessaoService.class);
    private final VotoService votoService = Mockito.mock(VotoService.class);

    private ResultadoService resultadoService;

    @BeforeEach
    void initUseCase() {
        resultadoService = new ResultadoService(resultadoRepository, sessaoService, votoService);
    }

    @Test
    void createSuccess() {

        Pauta pauta = new Pauta(100L, "Pauta Teste");
        Sessao sessao = new Sessao(pauta, 10, LocalDateTime.parse("2019-03-27T10:15:30"));
        Resultado resultado = new Resultado(sessao, 10, 5);
        when(sessaoService.findById(Mockito.any())).thenReturn(sessao);
        when(resultadoRepository.findFirstBySessao(Mockito.any())).thenReturn(Optional.empty());
        when(resultadoRepository.save(Mockito.any())).thenReturn(resultado);

        Resultado resultadoSaved = resultadoService.create(100L);

        assertEquals(resultadoSaved.getFavoraveis(), resultado.getFavoraveis());
        assertEquals(resultadoSaved.getContrarios(), resultado.getContrarios());
        assertTrue(resultadoSaved.getAprovado());
    }

    @Test
    void createNotNecessary() {

        Pauta pauta = new Pauta(100L, "Pauta Teste");
        Sessao sessao = new Sessao(pauta, 10, LocalDateTime.parse("2019-03-27T10:15:30"));
        Resultado resultado = new Resultado(sessao, 10, 5);
        when(sessaoService.findById(Mockito.any())).thenReturn(sessao);
        when(resultadoRepository.findFirstBySessao(Mockito.any())).thenReturn(Optional.of(resultado));

        Resultado resultadoSaved = resultadoService.create(100L);

        assertEquals(resultadoSaved.getFavoraveis(), resultado.getFavoraveis());
        assertEquals(resultadoSaved.getContrarios(), resultado.getContrarios());
        assertTrue(resultadoSaved.getAprovado());

    }

    @Test
    void createFailed() {
        doThrow(new SessaoAtivaException("")).doNothing().when(sessaoService).checkSessaoAtiva(Mockito.any());

        Assertions.assertThrows(
                SessaoAtivaException.class,
                () -> resultadoService.create(100L));

    }

    @Test
    public void findBySessao() {

        Pauta pauta = new Pauta(100L, "Pauta Teste");
        Sessao sessao = new Sessao(pauta, 10, LocalDateTime.now());
        Resultado resultado = new Resultado(sessao, 10, 5);
        when(resultadoRepository.findFirstBySessao(Mockito.any())).thenReturn(Optional.of(resultado));

        Optional<Resultado> resultadoOptional = resultadoService.findBySessao(sessao);
        assertTrue(resultadoOptional.isPresent());
        assertEquals(resultadoOptional.get().getSessao().getPauta().getId(), resultado.getSessao().getPauta().getId());
        assertEquals(resultadoOptional.get().getFavoraveis(), resultado.getFavoraveis());
        assertEquals(resultadoOptional.get().getContrarios(), resultado.getContrarios());
        assertTrue(resultadoOptional.get().getAprovado());

    }

    @Test
    public void findByPautaFailed() {

        when(resultadoRepository.findFirstBySessao(Mockito.any())).thenReturn(Optional.empty());
        Optional<Resultado> resultadoOptional = resultadoService.findBySessao(new Sessao());
        assertFalse(resultadoOptional.isPresent());

    }
}
