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
    public Job updateNaceDetails(Job newJob, Long id) {
        return jobRepository
                .findById(id)
                        .map( job -> {
                            job.setLevel(newJob.getLevel());
                            job.setCode(newJob.getCode());
                            job.setDescription(newJob.getDescription());
                            job.setParent(newJob.getParent());
                            job.setReferenceToISIC4(newJob.getReferenceToISIC4());
                            job.setThisItemIncludes(newJob.getThisItemIncludes());
                            job.setThisItemAlsoIncludes(newJob.getThisItemAlsoIncludes());
                            job.setThisItemExcludes(newJob.getThisItemExcludes());
                            job.setRulings(newJob.getRulings());
                            job.setParent(newJob.getParent());
                            return jobRepository.save(newJob);
                        })
                .orElseGet(()-> {
                    newJob.setOrderno(id);
                    return jobRepository.save(newJob);
                });
    }

    public Optional<Job> get(Long id){
        return  jobRepository.findById(id);
    }

}
