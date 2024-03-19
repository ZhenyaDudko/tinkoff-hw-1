package com.app.dto.cat;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CatDto implements Serializable {

    private long id;
    private String name;
    private String kind;
    private int age;
    private String masterName;
}
