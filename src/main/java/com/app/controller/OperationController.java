package com.app.controller;

import com.app.dto.OperationDto;
import com.app.model.Operation;
import com.app.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService service;

    @GetMapping("/{type}")
    public List<OperationDto> getOperations(@PathVariable Operation.OperationType type) {
        return service.getOperationsByType(type);
    }
}
