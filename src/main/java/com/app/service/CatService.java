package com.app.service;

import com.app.model.Cat;
import com.app.repository.CatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CatService {

    private final CatRepository catRepository;

    public Cat getById(long id) {
        Optional<Cat> cat = catRepository.findById(id);
        if (cat.isEmpty()) {
            throw new NoSuchElementException("Cat with id " + id + " not found");
        }
        return cat.get();
    }
}
