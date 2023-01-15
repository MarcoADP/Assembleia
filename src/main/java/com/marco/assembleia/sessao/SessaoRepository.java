package com.marco.assembleia.sessao;

import com.marco.assembleia.pauta.Pauta;
import org.springframework.data.repository.CrudRepository;

public interface SessaoRepository extends CrudRepository<Sessao, Long> {

    Sessao findFirstByPauta(Pauta pauta);

}
