package com.app.service;

import com.app.dto.ImageDto;
import com.app.dto.OperationDto;
import com.app.exception.ImageNotFoundException;
import com.app.mapper.ImagesMapper;
import com.app.model.Image;
import com.app.model.Operation;
import com.app.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RedisHash
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository repository;
    private final ImagesMapper mapper;

    private final OperationService operationService;

    private final MinioService service;

    public Optional<Image> getImage(int imageId) {
        return repository.findImageById(imageId);
    }

    @Cacheable(value = "ImageService::getImageMeta", key = "#id")
    public ImageDto getImageMeta(int id) {
        Optional<Image> imageOptional = repository.findImageById(id);
        if (imageOptional.isEmpty()) {
            throw new ImageNotFoundException(id);
        }

        ImageDto image = mapper.imageToImageDto(imageOptional.get());

        operationService.logOperation(
                new OperationDto(
                        String.format("Read image metadata: %s", image),
                        LocalDateTime.now(),
                        Operation.OperationType.READ
                )
        );

        return image;
    }

    public byte[] downloadImage(String link) throws Exception {
        if (!repository.existsImagesByLink(link)) {
            throw new ImageNotFoundException(link);
        }

        return service.downloadImage(link);
    }

    @Cacheable(value = "ImageService::getImageMeta", key = "#file.originalFilename")
    public ImageDto uploadImage(MultipartFile file) throws Exception {
        ImageDto image = service.uploadImage(file);
        repository.save(mapper.imageDtoToImage(image));

        operationService.logOperation(
                new OperationDto(
                        String.format("Upload image: %s", image),
                        LocalDateTime.now(),
                        Operation.OperationType.WRITE
                )
        );

        return image;
    }
}
