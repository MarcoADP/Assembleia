package com.marco.assembleia.voto;


import com.marco.assembleia.exceptions.UsuarioVotouException;
import com.marco.assembleia.resultado.ResultadoRepository;
import com.marco.assembleia.sessao.Sessao;
import com.marco.assembleia.sessao.SessaoService;
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

    private final ResultadoRepository resultadoRepository;

    public VotoService(VotoRepository votoRepository, SessaoService sessaoService,
                       ResultadoRepository resultadoRepository) {
        this.votoRepository = votoRepository;
        this.sessaoService = sessaoService;
        this.resultadoRepository = resultadoRepository;
    }

    public Voto findById(Long id) {
        return votoRepository.findById(id).orElseThrow(() -> new NotFoundException("Voto não encontrado"));
    }

    public Iterable<Voto> findAll() {
        return votoRepository.findAll();
    }

    public Voto create(Long sessaoId, VotoParams params) {

        Sessao sessao = sessaoService.findById(sessaoId);
        sessaoService.checkSessaoFinalizada(sessao);
        checkUsuarioPodeVotar(params.getUsuarioId(), sessao);

        Voto voto = new Voto(params.getUsuarioId(), sessao, params.getVoto());
        return save(voto);
    }

    private void checkUsuarioPodeVotar(Integer usuarioId, Sessao sessao) {
        if (votoRepository.findByUsuarioIdAndSessao(usuarioId, sessao).size() > 0) {
            throw new UsuarioVotouException(usuarioId);
        }
    }

    public Voto save(Voto voto) {
        return votoRepository.save(voto);
    }

    public List<Voto> findBySessaoId(Long sessaoId) {
        Sessao sessao = sessaoService.findById(sessaoId);
        return votoRepository.findBySessao(sessao);
    }

    public List<Voto> findBySessao(Sessao sessao) {
        return votoRepository.findBySessao(sessao);
    }
}
