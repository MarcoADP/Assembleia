package com.marco.assembleia.service;

import com.marco.assembleia.entities.Pauta;
import com.marco.assembleia.entities.Sessao;
import com.marco.assembleia.exceptions.PautaComSessaoAtivaException;
import com.marco.assembleia.exceptions.SessaoFinalizadaException;
import com.marco.assembleia.params.SessaoParams;
import com.marco.assembleia.repositories.SessaoRepository;
import java.time.LocalDateTime;
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

    public Iterable<Sessao> findAll() {
        return sessaoRepository.findAll();
    }

    public Sessao create(SessaoParams params) {
        Pauta pauta = pautaService.findById(params.getPautaId());

        LocalDateTime inicio = LocalDateTime.now();
        checkPautaComSessaoAtiva(pauta, inicio);

        Sessao sessao = new Sessao(pauta, params.getDuracao(), inicio);
        return save(sessao);
    }

    private void checkPautaComSessaoAtiva(Pauta pauta, LocalDateTime inicio) {
        var sessoes = sessaoRepository.findByPautaAndInicioIsBeforeAndFimIsAfter(pauta, inicio, inicio);
        System.out.println(sessoes.size());
        if (sessaoRepository.findByPautaAndInicioIsBeforeAndFimIsAfter(pauta, inicio, inicio).size() > 0) {
            throw new PautaComSessaoAtivaException(pauta.getAssunto());
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
