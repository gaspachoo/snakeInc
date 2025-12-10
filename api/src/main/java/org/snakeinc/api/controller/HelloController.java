package org.snakeinc.api.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/hello")
public class HelloController {

    @GetMapping
    public String sayHello(@RequestParam String name) {
        return "Hello " + name;
    }

    @PostMapping
    public String postHello(@RequestBody BodyParam name){
        return "post " + name.name();
    }

    private record BodyParam(String name){}
}
