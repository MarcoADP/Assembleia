package com.marco.assembleia.pauta;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pauta")
@Getter
@NoArgsConstructor
public class Pauta {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String assunto;

    public Pauta(String assunto) {
        this.assunto = assunto;
    }

    public void update(String assunto) {
        this.assunto = assunto;
    }
}
