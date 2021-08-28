package com.arachcorp.smartkitchen.rest.dto.dispositivo;

import com.arachcorp.smartkitchen.entities.UserDispositivo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispositivoDTO {

    private Long id;
    private String key;
    private String descricao;
    private boolean principal;
    private LocalDateTime timestamp;

    public static DispositivoDTO fromUserDispositivo(UserDispositivo source) {
        if (Objects.isNull(source) || Objects.isNull(source.getId()) || Objects.isNull(source.getId().getDispositivo())) {
            return null;
        }
        return DispositivoDTO.builder()
                .id(source.getId().getDispositivo().getId())
                .key(source.getId().getDispositivo().getKey())
                .descricao(source.getId().getDispositivo().getDescricao())
                .principal(source.isPrincipal())
                .timestamp(source.getId().getDispositivo().getTimestamp())
                .build();
    }
}
