package com.app.service;

import com.app.dto.OperationDto;
import com.app.mapper.OperationMapper;
import com.app.model.Operation;
import com.app.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository repository;
    private final OperationMapper mapper;

    public void logOperation(OperationDto operationDto) {
        repository.save(mapper.operationDtoToOperation(operationDto));
    }

    public List<OperationDto> getOperationsByType(Operation.OperationType type) {
        return mapper.operationsToOperationDtos(repository.findAllByType(type));
    }
}
