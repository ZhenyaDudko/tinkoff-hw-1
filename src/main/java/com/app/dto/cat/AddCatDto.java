package com.app.dto.cat;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddCatDto {

    private String name;
    private String kind;
    private int age;
    private String masterName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer imageId;
}
