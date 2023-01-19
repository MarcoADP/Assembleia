package com.marco.assembleia.unidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.marco.assembleia.exceptions.BusinessException;
import com.marco.assembleia.pauta.Pauta;
import com.marco.assembleia.pauta.PautaParams;
import com.marco.assembleia.pauta.PautaRepository;
import com.marco.assembleia.pauta.PautaService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;

@ExtendWith(MockitoExtension.class)
public class PautaTest {

    private static final String PAUTA_SEM_ASSUNTO = "É necessário informar o assunto da pauta";
    private static final String PAUTA_NAO_ENCONTRADA = "Pauta não encontrada";

    private final PautaRepository pautaRepository = Mockito.mock(PautaRepository.class);

    private PautaService pautaService;

    @BeforeEach
    void initUseCase() {
        pautaService = new PautaService(pautaRepository);
    }

    @Test
    void createSuccess() {
        Pauta pauta = new Pauta(100L, "Pauta Teste");
        when(pautaRepository.save(Mockito.any())).thenReturn(pauta);
        Pauta pautaSaved = pautaService.create(new PautaParams("Pauta Teste"));
        assertEquals(pautaSaved.getId(), pauta.getId());
        assertEquals(pautaSaved.getAssunto(), pauta.getAssunto());
    }

    @Test
    void createFailed() {
        Exception exception = Assertions.assertThrows(
                BusinessException.class,
                () -> pautaService.create(new PautaParams("")));
        Assertions.assertEquals(PAUTA_SEM_ASSUNTO, exception.getMessage());

    }

    @Test
    public void findById() {

        Long id = 100L;
        Pauta pauta = new Pauta(id, "Pauta Teste");
        when(pautaRepository.findById(Mockito.any())).thenReturn(Optional.of(pauta));
        Pauta pautaFind = pautaService.findById(id);
        assertEquals(pautaFind.getId(), pauta.getId());
        assertEquals(pautaFind.getAssunto(), pauta.getAssunto());

    }

    @Test
    public void findByIdFailed() {

        when(pautaRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Exception exception = Assertions.assertThrows(
                NotFoundException.class,
                () -> pautaService.findById(-1L));
        Assertions.assertEquals(PAUTA_NAO_ENCONTRADA, exception.getMessage());

    }

    @Test
    public void findAll() {

        Pauta pauta = new Pauta(100L, "Pauta Teste");
        given(pautaRepository.findAll()).willReturn(List.of(pauta));
        List<Pauta> pautas = pautaService.findAll();

        assertEquals(pautas.size(), 1);
        assertEquals(pautas.get(0).getId(), pauta.getId());

    }

}
