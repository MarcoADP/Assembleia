package com.marco.assembleia.sessao;

import com.marco.assembleia.exceptions.PautaComSessaoException;
import com.marco.assembleia.exceptions.SessaoFinalizadaException;
import com.marco.assembleia.pauta.Pauta;
import com.marco.assembleia.pauta.PautaService;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@Transactional
public class SessaoService {

    final
    private SessaoRepository sessaoRepository;

    final PautaService pautaService;

    public SessaoService(SessaoRepository sessaoRepository, PautaService pautaService) {
        this.sessaoRepository = sessaoRepository;
        this.pautaService = pautaService;
    }

    public Sessao findById(Long id) {
        return sessaoRepository.findById(id).orElseThrow(() -> new NotFoundException("Sessão não encontrada"));
    }

    public Optional<Sessao> findByPauta(Pauta pauta) {
        return Optional.ofNullable(sessaoRepository.findFirstByPauta(pauta));
    }

    public Iterable<Sessao> findAll() {
        return sessaoRepository.findAll();
    }

    public Sessao create(Long pautaId, SessaoParams params) {
        Pauta pauta = pautaService.findById(pautaId);

        LocalDateTime inicio = LocalDateTime.now();
        checkPautaComSessao(pauta);

        Sessao sessao = new Sessao(pauta, params.getDuracao(), inicio);
        return save(sessao);
    }

    public void checkPautaComSessao(Pauta pauta) {
        if (findByPauta(pauta).isPresent()) {
            throw new PautaComSessaoException(pauta.getAssunto());
        }
    }

    public Sessao save(Sessao sessao) {
        return sessaoRepository.save(sessao);
    }

    public void checkSessaoFinalizada(Sessao sessao) {
        if (!sessao.isAtiva()) {
            throw new SessaoFinalizadaException(sessao.getPauta().getAssunto());
        }
    }

}
