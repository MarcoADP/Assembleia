package com.marco.assembleia.resultado;

import com.marco.assembleia.exceptions.SessaoAtivaException;
import com.marco.assembleia.sessao.Sessao;
import com.marco.assembleia.sessao.SessaoService;
import com.marco.assembleia.voto.Voto;
import com.marco.assembleia.voto.VotoService;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@Transactional
public class ResultadoService {

    final
    private ResultadoRepository resultadoRepository;

    final
    private SessaoService sessaoService;

    final
    private VotoService votoService;

    public ResultadoService(
            ResultadoRepository resultadoRepository,
            SessaoService sessaoService,
            VotoService votoService
    ) {
        this.resultadoRepository = resultadoRepository;
        this.sessaoService = sessaoService;
        this.votoService = votoService;
    }

    public Resultado findById(Long id) {
        return resultadoRepository.findById(id).orElseThrow(() -> new NotFoundException("Resultado não encontrado"));
    }

    public Iterable<Resultado> findAll() {
        return resultadoRepository.findAll();
    }

    public Optional<Resultado> findBySessao(Sessao sessao) {
        return resultadoRepository.findFirstBySessao(sessao);
    }

    public Resultado save(Resultado resultado) {
        return resultadoRepository.save(resultado);
    }

    public Resultado create(Long sessaoId) {
        Sessao sessao = sessaoService.findById(sessaoId);

        if (sessao.isAtiva()) {
            throw new SessaoAtivaException(sessao.getPauta().getAssunto());
        }

        Optional<Resultado> resultadoOpt = findBySessao(sessao);
        Resultado resultado;
        if (resultadoOpt.isPresent()) {
            resultado = resultadoOpt.get();
        } else {
            List<Voto> votos = votoService.findBySessao(sessao);
            int favoraveis = Math.toIntExact(votos.stream().filter(Voto::getVoto).count());
            int contrarios = votos.size() - favoraveis;
            Boolean aprovado = favoraveis > contrarios;
            resultado = new Resultado(sessao, favoraveis, contrarios, aprovado);
            resultado = save(resultado);
        }
        return resultado;
    }

    public void delete(Resultado resultado) {
        resultadoRepository.delete(resultado);
    }
}
