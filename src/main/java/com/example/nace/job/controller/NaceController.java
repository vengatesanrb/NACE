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
    private final NmcaService nmcaService;

    private Mapper mapper;


    NaceController(NmcaService service, Mapper mapper){
        this.nmcaService = service;
        this.mapper = mapper;
    }

    @GetMapping("/{orderno}")
    JobDetails getNaceDetails(@PathVariable Long orderno){
        Job job =  nmcaService.getNaceDetails(orderno);
        JobDetails jobDetails = new JobDetails();
        mapper.map(job, jobDetails);
        return jobDetails;
    }

    @PostMapping(path="/add", consumes = "application/json")
    ResponseEntity<JobDetails> postNaceDetails(@RequestBody Job job, UriComponentsBuilder builder) throws NaceAlreadyExistException {
        if(nmcaService.isExists(job)) {
            throw new NaceAlreadyExistException();
        }
        Job details = nmcaService.addNaceDetails(job);
        JobDetails jobDetailsView = new JobDetails();
        mapper.map(details, jobDetailsView);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/nace/{orderno}").buildAndExpand(job.getOrderno()).toUri());
        return new ResponseEntity<JobDetails>(jobDetailsView, headers, HttpStatus.CREATED);
    }

    @PutMapping(path="/update/{orderno}", consumes = "application/json")
    JobDetails updateNaceDetails(@RequestBody Job job, @PathVariable("orderno") Long id) {
         Job updatedNaceDetails =  nmcaService.updateNaceDetails(job, id);
         JobDetails jobDetails = new JobDetails();
         mapper.map(updatedNaceDetails, jobDetails);
        return jobDetails;
    }
}