package io.github.mananshr.crud;

import io.micronaut.http.annotation.*;

@Controller("/mcronaut")
public class McronautController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}