package com.marco.assembleia.sessao;

import com.marco.assembleia.pauta.Pauta;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sessao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Sessao {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @NotNull
    private Integer duracao;

    @NotNull
    private LocalDateTime inicio;

    @NotNull
    private LocalDateTime fim;

    public Sessao(Pauta pauta, Integer duracao, LocalDateTime inicio) {
        this.pauta = pauta;
        this.duracao = duracao;
        this.inicio = inicio;
        this.fim = this.inicio.plusMinutes(this.duracao);
    }

    public Boolean isAtiva() {
        return LocalDateTime.now().isBefore(this.fim);
    }

}
