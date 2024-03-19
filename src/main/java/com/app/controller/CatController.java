package com.app.controller;

import com.app.dto.ImageDto;
import com.app.dto.cat.AddCatDto;
import com.app.dto.cat.CatDto;
import com.app.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cat")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping("/{id}")
    public CatDto getCat(@PathVariable long id) {
        return catService.getCatById(id);
    }

    @GetMapping("/{id}/image")
    public ImageDto getCatImage(@PathVariable long id) {
        return catService.getCatImage(id);
    }

    @PostMapping("/add")
    public AddCatDto addCat(@RequestBody AddCatDto catDto) {
        return catService.addCat(catDto);
    }
}
