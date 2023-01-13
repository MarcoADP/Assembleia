package com.marco.assembleia.repositories;

import com.marco.assembleia.entities.Resultado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ResultadoRepository extends CrudRepository<Resultado, Long> {

    @Query(value = "select max(r) from Resultado r where r.sessao.id = :sessaoId")
    Resultado findFirstBySessaoId(Long sessaoId);

}
