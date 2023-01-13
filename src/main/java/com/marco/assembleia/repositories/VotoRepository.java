package com.marco.assembleia.repositories;

import com.marco.assembleia.entities.Sessao;
import com.marco.assembleia.entities.Voto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VotoRepository extends CrudRepository<Voto, Long> {

    @Query("select v from Voto v where v.sessao.id = :sessaoId")
    List<Voto> findBySessaoId(Long sessaoId);

    List<Voto> findByUsuarioIdAndSessao(Integer usuarioId, Sessao sessao);
}
