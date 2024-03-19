package com.app.dto;

import com.app.model.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OperationDto {

    private String content;
    private LocalDateTime date;
    private Operation.OperationType type;
}
