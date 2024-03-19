package com.app.service;

import com.app.dto.ImageDto;
import com.app.dto.OperationDto;
import com.app.dto.cat.AddCatDto;
import com.app.dto.cat.CatDto;
import com.app.exception.CatNotFoundException;
import com.app.exception.ImageNotFoundException;
import com.app.mapper.CatMapper;
import com.app.mapper.ImagesMapper;
import com.app.model.Cat;
import com.app.model.Image;
import com.app.model.Operation;
import com.app.repository.CatRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RedisHash
@AllArgsConstructor
public class CatService {

    private final CatRepository catRepository;

    private final ImagesMapper imageMapper;
    private final CatMapper mapper;

    private final OperationService operationService;
    private final ImageService imageService;

    public Cat getById(long id) {
        Optional<Cat> cat = catRepository.findById(id);
        if (cat.isEmpty()) {
            throw new CatNotFoundException(id);
        }
        return cat.get();
    }

    @Cacheable(value = "CatService::getCatById", key = "#id")
    public CatDto getCatById(long id) {
        Optional<Cat> catOptional = catRepository.findById(id);
        if (catOptional.isEmpty()) {
            throw new CatNotFoundException(id);
        }

        CatDto cat = mapper.catToCatDto(catOptional.get());
        operationService.logOperation(
                new OperationDto(
                        String.format("Read cat: %s", cat),
                        LocalDateTime.now(),
                        Operation.OperationType.READ
                )
        );

        return cat;
    }

    @Cacheable(value = "CatService::getCatImage", key = "#id + '.image'")
    public ImageDto getCatImage(long id) {
        Optional<Cat> catOptional = catRepository.findById(id);
        if (catOptional.isEmpty()) {
            throw new CatNotFoundException(id);
        }

        ImageDto image = imageMapper.imageToImageDto(catOptional.get().getImage());
        operationService.logOperation(
                new OperationDto(
                        String.format("Read cat image: %s", image),
                        LocalDateTime.now(),
                        Operation.OperationType.READ
                )
        );

        return image;
    }

    public AddCatDto addCat(AddCatDto addCatDto) {
        Integer imageId = addCatDto.getImageId();

        Image image = imageId == null ? null
                : imageService.getImage(imageId).orElseThrow(ImageNotFoundException::new);

        catRepository.save(mapper.addCatDtoToCat(addCatDto, image));

        operationService.logOperation(
                new OperationDto(
                        String.format("Add cat: %s", addCatDto),
                        LocalDateTime.now(),
                        Operation.OperationType.WRITE
                )
        );

        return addCatDto;
    }
}

