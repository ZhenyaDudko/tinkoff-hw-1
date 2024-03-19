package com.app.mapper;

import com.app.dto.ImageDto;
import com.app.model.Image;
import org.mapstruct.Mapper;

@Mapper
public interface ImagesMapper {

    ImageDto imageToImageDto(Image image);

    Image imageDtoToImage(ImageDto imageDto);
}
