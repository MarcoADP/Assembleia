package com.marco.assembleia.resultado;

import com.marco.assembleia.sessao.Sessao;
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
@Table(name = "resultado")
@Getter
@NoArgsConstructor
public class Resultado {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

    @NotNull
    private Integer favoraveis;

    @NotNull
    private Integer contrarios;

    @NotNull
    private Boolean aprovado;

    public Resultado(Sessao sessao, Integer favoraveis, Integer contrarios) {
        this.sessao = sessao;
        this.favoraveis = favoraveis;
        this.contrarios = contrarios;
        this.aprovado = favoraveis > contrarios;
    }
}
