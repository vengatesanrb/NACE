package com.example.nace.job.service;

import com.example.nace.job.dao.JobRepository;
import com.example.nace.job.entities.Job;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NaceServiceTest {
    @Mock(name = "repository") private JobRepository jobRepository;
    @InjectMocks private NmcaService nmcaService;
    Job fish;
    Job cattle;
    @Before
    public void mock(){
        fish = new Job(598481L, "A","2","FISHING ","","","","","","A");
        cattle = new Job(698481L, "A","2","GROWING COW ","","","","","","A");
        when(jobRepository.save(isA(Job.class))).thenReturn(fish);
        when(jobRepository.findById(598481L)).thenReturn(Optional.of(fish));
        when(jobRepository.findById(698481L)).thenReturn(Optional.of(cattle));
    }

    @Test
    public void addNaceDetails(){
        Job savedJob = nmcaService.addNaceDetails(fish);
        assertEquals("2", savedJob.getCode());
        assertEquals("A", savedJob.getLevel());
        assertEquals(savedJob.getDescription(), fish.getDescription());
    }

    @Test
    public void updateNaceDetails(){
        cattle.setLevel("B");
        when(jobRepository.save(cattle)).thenReturn(cattle);
        Job savedJob = nmcaService.updateNaceDetails(cattle, cattle.getOrderno());
        assertNotEquals("A", savedJob.getLevel());
    }
}
