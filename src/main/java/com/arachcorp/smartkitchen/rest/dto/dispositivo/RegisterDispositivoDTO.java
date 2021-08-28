package com.arachcorp.smartkitchen.rest.dto.dispositivo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDispositivoDTO {

    @NotBlank(message = "{dispositivo.dispositivo-dto.key.empty}")
    private String key;

    @NotBlank(message = "{dispositivo.dispositivo-dto.descricao.empty}")
    private String descricao;

}
