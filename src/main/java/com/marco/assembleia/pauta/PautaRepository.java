package com.marco.assembleia.pauta;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PautaRepository extends CrudRepository<Pauta, Long> {

    @Override
    List<Pauta> findAll();

}
