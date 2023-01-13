package com.marco.assembleia.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "voto")
@Getter
@NoArgsConstructor
public class Voto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer usuarioId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

    @NotNull
    private Boolean voto;

    public Voto(Integer usuarioId, Sessao sessao, Boolean voto) {
        this.usuarioId = usuarioId;
        this.sessao = sessao;
        this.voto = voto;
    }
}
