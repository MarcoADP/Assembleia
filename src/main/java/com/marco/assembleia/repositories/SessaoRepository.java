package com.marco.assembleia.repositories;

import com.marco.assembleia.entities.Pauta;
import com.marco.assembleia.entities.Sessao;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface SessaoRepository extends CrudRepository<Sessao, Long> {

    List<Sessao> findByPautaAndInicioIsBeforeAndFimIsAfter(Pauta pauta, LocalDateTime data, LocalDateTime data2);

}
