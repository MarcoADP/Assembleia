package com.marco.assembleia.resultado;

import com.marco.assembleia.sessao.Sessao;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ResultadoRepository extends CrudRepository<Resultado, Long> {

    Optional<Resultado> findFirstBySessao(Sessao sessao);

}
