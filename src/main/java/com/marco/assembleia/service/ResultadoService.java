package com.marco.assembleia.service;

import com.marco.assembleia.entities.Resultado;
import com.marco.assembleia.repositories.ResultadoRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@Transactional
public class ResultadoService {

    final
    private ResultadoRepository resultadoRepository;

    public ResultadoService(ResultadoRepository resultadoRepository) {
        this.resultadoRepository = resultadoRepository;
    }

    public Resultado findById(Long id) {
        return resultadoRepository.findById(id).orElseThrow(() -> new NotFoundException("Resultado não encontrado"));
    }

    public Iterable<Resultado> findAll() {
        return resultadoRepository.findAll();
    }


    public Resultado findBySessao(Long sessaoId) {
        return resultadoRepository.findFirstBySessaoId(sessaoId);
    }

    public Resultado save(Resultado resultado) {
        return resultadoRepository.save(resultado);
    }

}
