package com.marco.assembleia.service;


import com.marco.assembleia.entities.Sessao;
import com.marco.assembleia.entities.Voto;
import com.marco.assembleia.params.VotoParams;
import com.marco.assembleia.repositories.VotoRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@Transactional
public class VotoService {

    final
    private VotoRepository votoRepository;

    final SessaoService sessaoService;

    public VotoService(VotoRepository votoRepository, SessaoService sessaoService) {
        this.votoRepository = votoRepository;
        this.sessaoService = sessaoService;
    }

    public Voto findById(Long id) {
        return votoRepository.findById(id).orElseThrow(() -> new NotFoundException("Voto não encontrado"));
    }

    public Iterable<Voto> findAll() {
        return votoRepository.findAll();
    }

    public Voto create(VotoParams params) {
        Sessao sessao = sessaoService.findById(params.getSessaoId());
        Voto voto = new Voto(params.getUsuarioId(), sessao, params.getVoto());
        return save(voto);
    }

    public Voto save(Voto voto) {
        return votoRepository.save(voto);
    }

    public List<Voto> findBySessao(Long sessaoId) {
        return votoRepository.findBySessaoId(sessaoId);
    }
}
