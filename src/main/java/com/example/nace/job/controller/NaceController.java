package com.example.nace.job.controller;

import com.example.nace.job.entities.Job;
import com.example.nace.job.exceptions.NaceAlreadyExistException;
import com.example.nace.job.mapper.Mapper;
import com.example.nace.job.services.NmcaService;
import com.example.nace.job.vo.JobDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/nace")
public class NaceController {
    @Autowired
    private final NmcaService nmcaService;
    @Autowired
    @Qualifier("ecnaMapper")
    private Mapper mapper;


    NaceController(NmcaService service, Mapper mapper){
        this.nmcaService = service;
        this.mapper = mapper;
    }

    @GetMapping("/{orderno}")
    ResponseEntity<JobDetails> getNaceDetails(@PathVariable Long orderno){
        Job job =  nmcaService.getNaceDetails(orderno);
        JobDetails nace = new JobDetails();
        mapper.map(job, nace);
        return new ResponseEntity<JobDetails>(nace, HttpStatus.OK);
    }

    @PostMapping(path="/add", consumes = "application/json")
    ResponseEntity<Void> postNaceDetails(@RequestBody Job job, UriComponentsBuilder builder) throws NaceAlreadyExistException {
        if(nmcaService.isExists(job)) {
            throw new NaceAlreadyExistException();
        }
        nmcaService.addNaceDetails(job);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/nace/{orderno}").buildAndExpand(job.getOrderno()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping(path="/update/{orderno}", consumes = "application/json")
    Job updateNaceDetails(@RequestBody Job job, @PathVariable("orderno") Long id) {
         return nmcaService.updateNaceDetails(job, id);
    }
}