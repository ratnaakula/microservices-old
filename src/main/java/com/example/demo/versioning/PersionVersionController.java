package com.example.demo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersionVersionController {

    //URI versioning
    @GetMapping("/v1/person")
    public PersonV1 getPersonV1(){
        return new PersonV1("Ratna Akula");
    }

    @GetMapping("/v2/person")
    public PersonV2 getPersonV2(){
        return new PersonV2(new Name("Ratna", "Akula"));
    }

    // Param versioning
    @GetMapping(value = "/person/param", params="version=1")
    public PersonV1 getPersonParamV1(){
        return new PersonV1("Ratna Akula");
    }

    @GetMapping(value = "/person/param", params="version=2")
    public PersonV2 getPersonParamV2(){
        return new PersonV2(new Name("Ratna", "Akula"));
    }

    // header versioning
    @GetMapping(value = "/person/header", headers="X-API-VERSION=1")
    public PersonV1 getPersonHeaderV1(){
        return new PersonV1("Ratna Akula");
    }

    @GetMapping(value = "/person/header", headers="X-API-VERSION=2")
    public PersonV2 getPersonHeaderV2(){
        return new PersonV2(new Name("Ratna", "Akula"));
    }

    //MIME type versioning
    @GetMapping(value = "/person/produces", produces="application/vnd.company.app-v1+json")
    public PersonV1 getPersonproducesV1(){
        return new PersonV1("Ratna Akula");
    }

    @GetMapping(value = "/person/produces", produces="application/vnd.company.app-v2+json")
    public PersonV2 getPersonproducesV2(){
        return new PersonV2(new Name("Ratna", "Akula"));
    }
}
