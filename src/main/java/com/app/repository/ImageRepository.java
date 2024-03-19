package com.app.repository;

import com.app.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    boolean existsImageById(Integer id);

    boolean existsImagesByLink(String link);

    Optional<Image> findImageById(int id);
}
