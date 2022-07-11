package com.example.nace.Job.services;

import com.example.nace.Job.dao.JobRepository;
import com.example.nace.Job.entities.Job;
import com.example.nace.Job.exceptions.NaceDetailNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class NmcaService {

    private final JobRepository jobRepository;

    public NmcaService(JobRepository repository){
        this.jobRepository = repository;
    }

    public void initializeDatabase() {
        if(jobRepository.count() == 0){
            Job job = new Job(1L,"2","3","Hello");

            jobRepository.save(job);
        }
    }

    public Job getNaceDetails(Long id){
        return jobRepository.findById(id).orElseThrow( () -> new NaceDetailNotFoundException(id));
    }

    public Job addNaceDetails(Job job){
        return jobRepository.save(job);
    }
    public boolean isExists(Job job){
        return  jobRepository.findById(job.getId()).isPresent();
    }

}
