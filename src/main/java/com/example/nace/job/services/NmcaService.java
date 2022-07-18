package com.example.nace.job.services;

import com.example.nace.job.dao.JobRepository;
import com.example.nace.job.entities.Job;
import com.example.nace.job.exceptions.NaceDetailNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NmcaService {

    private final JobRepository jobRepository;
    public NmcaService(JobRepository repository){
        this.jobRepository = repository;
    }
    public Job getNaceDetails(Long id){
        return jobRepository.findById(id).orElseThrow( () -> new NaceDetailNotFoundException(id));
    }

    public Job addNaceDetails(Job job){
        return jobRepository.save(job);
    }
    public boolean isExists(Job job){
        return  jobRepository.findById(job.getOrderno()).isPresent();
    }
    public Job updateNaceDetails(Job job, Long id) {
        Optional<Job> jobOptional = Optional.ofNullable(jobRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("OrderID not Found!" + id)));
        return jobRepository.save(job);
    }

    public Optional<Job> get(Long id){
        return  jobRepository.findById(id);
    }

}
