package com.arachcorp.smartkitchen.rest.dto.notificacao;

import com.arachcorp.smartkitchen.entities.Notificacao;
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
public class NotificacaoDTO {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime timestamp;

    public static NotificacaoDTO of(Notificacao source){
        if (Objects.isNull(source)) { return null; }
        return NotificacaoDTO.builder()
                .id(source.getId())
                .mensagem(source.getMensagem())
                .titulo(source.getTitulo())
                .timestamp(source.getTimestamp())
                .build();
    }
}
