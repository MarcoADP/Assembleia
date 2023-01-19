package com.marco.assembleia.pauta;

import com.marco.assembleia.exceptions.BusinessException;
import java.util.List;
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

    public List<Pauta> findAll() {
        return pautaRepository.findAll();
    }

    public Pauta create(PautaParams params) {

        if (params.getAssunto().isBlank()) {
            throw new BusinessException("É necessário informar o assunto da pauta");
        }

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

    public void delete(Pauta pauta) {
        pautaRepository.delete(pauta);
    }
}
