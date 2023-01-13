package com.marco.assembleia.service;

import com.marco.assembleia.entities.Pauta;
import com.marco.assembleia.params.PautaParams;
import com.marco.assembleia.repositories.PautaRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@Transactional
public class PautaService {

    final
    private PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta findById(Long id) {
        return pautaRepository.findById(id).orElseThrow(() -> new NotFoundException("Pauta não encontrada"));
    }

    public Iterable<Pauta> findAll() {
        return pautaRepository.findAll();
    }

    public Pauta create(PautaParams params) {
        Pauta pauta = new Pauta(params.getAssunto());
        return save(pauta);
    }

    public Pauta update(Long id, PautaParams params) {
        Pauta pauta = findById(id);
        pauta.update(params.getAssunto());
        return save(pauta);
    }

    public Pauta save(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

}
