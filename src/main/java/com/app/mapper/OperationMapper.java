package com.app.mapper;

import com.app.dto.OperationDto;
import com.app.model.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface OperationMapper {

    List<OperationDto> operationsToOperationDtos(List<Operation> operations);

    @Mapping(target = "id", expression = "java(null)")
    Operation operationDtoToOperation(OperationDto operationDto);
}
