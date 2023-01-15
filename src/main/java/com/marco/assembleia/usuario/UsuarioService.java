package com.marco.assembleia.usuario;

import com.marco.assembleia.voto.SituacaoVotoDTO;
import com.marco.assembleia.voto.VotoStatus;
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
