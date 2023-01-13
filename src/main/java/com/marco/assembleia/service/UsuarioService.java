package com.marco.assembleia.service;

import com.marco.assembleia.dtos.SituacaoVotoDTO;
import com.marco.assembleia.enums.VotoStatus;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {

    public SituacaoVotoDTO verificarSituacao(Long cpf) {
        return Math.random() > 0.5
                ? new SituacaoVotoDTO(VotoStatus.ABLE_TO_VOTO)
                : new SituacaoVotoDTO(VotoStatus.UNABLE_TO_VOTE);
    }
}
