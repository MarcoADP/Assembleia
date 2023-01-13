package com.marco.assembleia.dtos;

import com.marco.assembleia.enums.VotoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SituacaoVotoDTO {

    private VotoStatus status;

}
