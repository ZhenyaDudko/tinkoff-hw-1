package com.app.mapper;

import com.app.dto.cat.AddCatDto;
import com.app.dto.cat.CatDto;
import com.app.model.Cat;
import com.app.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CatMapper {

    CatDto catToCatDto(Cat cat);

    @Mapping(target = "image", source = "image")
    @Mapping(target = "name", source = "cat.name")
    @Mapping(target = "id", ignore = true)
    Cat addCatDtoToCat(AddCatDto cat, Image image);
}
