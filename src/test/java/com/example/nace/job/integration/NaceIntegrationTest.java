package com.example.nace.job.integration;

import com.example.nace.job.JobApplication;
import com.example.nace.job.vo.JobDetails;
import org.junit.BeforeClass;
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
public class NaceIntegrationTest {
    @LocalServerPort
    private int port;
    private String baseURL="";
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeClass
    void setPort(){
       baseURL  = "http://localhost:" + port;
    }

    String POST_URI = "/nace/add";
    String GET_URI = "/nace/";
    String ORDER_NO = "398482";



    @Test
    public void checkNaceDetailsAgainstOrderId() throws Exception{
        ResponseEntity<JobDetails> responseEntity
                = this.restTemplate
                .getForEntity(baseURL + GET_URI + ORDER_NO, JobDetails.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Crop and animal production, hunting and related service activities"
                ,responseEntity.getBody().getDescription());
    }

    @Test
    public void postEconomicActivities(){
        JobDetails jobDetails = new JobDetails(498482L,"2","1","A","Fishing","Fishing on Lake","growing fish","","","A");
        ResponseEntity<JobDetails> responseEntity = this.restTemplate
                .postForEntity(baseURL +POST_URI, jobDetails, JobDetails.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(498482L, responseEntity.getBody().getOrderno());
    }

    @Test
    public void putEconomicActivities(){
        String PUT_URI = "/nace/update/";
        JobDetails jobDetails = new JobDetails(498482L,"2","1","A","Boating","Fishing on Lake","growing fish","","","A");
        this.restTemplate
                .put(baseURL + PUT_URI +"498482",jobDetails);
        ResponseEntity<JobDetails> responseEntity
                = this.restTemplate
                .getForEntity(baseURL + GET_URI + "498482", JobDetails.class);
        assertEquals("Boating", responseEntity.getBody().getDescription());
    }

}
