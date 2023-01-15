package com.marco.assembleia.voto;

import com.marco.assembleia.sessao.Sessao;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface VotoRepository extends CrudRepository<Voto, Long> {

    List<Voto> findBySessao(Sessao sessao);

    List<Voto> findByUsuarioIdAndSessao(Integer usuarioId, Sessao sessao);
}
