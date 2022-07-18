package com.example.nace.Job.controller;

import com.example.nace.Job.entities.Job;
import com.example.nace.Job.exceptions.NaceAlreadyExistException;
import com.example.nace.Job.services.NmcaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("nace")
public class NaceController {
    private final NmcaService nmcaService;

    NaceController(NmcaService service){
        this.nmcaService = service;
    }

    @GetMapping("/{id}")
    Job getNaceDetails(@PathVariable Long id){
        return nmcaService.getNaceDetails(id);
    }

    @GetMapping("/")
    String greet(){
        return "Hello World!";
    }

    @PostMapping(path="/add", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    Job postNaceDetails(@RequestBody Job job) throws NaceAlreadyExistException {
        if(nmcaService.isExists(job)) {
            throw new NaceAlreadyExistException();
        }
        return nmcaService.addNaceDetails(job);
    }

}
