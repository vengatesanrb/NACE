package com.example.nace.job.integration;

import com.example.nace.job.JobApplication;
import com.example.nace.job.vo.JobDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = JobApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:application-inttest.properties")
public class NaceIntegrationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    String POST_URI = "/nace/add";
    String GET_URI = "/nace/";
    String ORDER_NO = "398482";
    @Test
    public void checkNaceDetailsAgainstOrderId() throws Exception{
        ResponseEntity<JobDetails> responseEntity
                = this.restTemplate
                .getForEntity("http://localhost:" + port + GET_URI + ORDER_NO, JobDetails.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Crop and animal production, hunting and related service activities"
                ,responseEntity.getBody().getDescription());
    }

    @Test
    public void postEconomicActivities(){
        JobDetails jobDetails = new JobDetails(498482L,"2","1","A","Fishing","Fishing on Lake","growing fish","","","A");
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port +POST_URI, jobDetails, String.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }


}
