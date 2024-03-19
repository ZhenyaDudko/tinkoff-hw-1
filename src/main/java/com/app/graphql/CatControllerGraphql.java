package com.app.graphql;

import com.app.model.Cat;
import com.app.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CatControllerGraphql {

    private final CatService catService;

    @QueryMapping
    public Cat catById(@Argument long id) {
        return catService.getById(id);
    }

}
