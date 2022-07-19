package com.example.nace.job.integration;

import com.example.nace.job.JobApplication;
import com.example.nace.job.vo.JobDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.junit.Assert.assertNotEquals;
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
    ObjectMapper mapper = new ObjectMapper();
    @Test
    public void getNaceDetailsAgainstOrderId() throws Exception{
        ResponseEntity<JobDetails> responseEntity
                = this.restTemplate
                .getForEntity(baseURL + GET_URI + ORDER_NO, JobDetails.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Crop and animal production, hunting and related service activities"
                ,responseEntity.getBody().getDescription());
       String responseJson = mapper.writeValueAsString(responseEntity.getBody());
       String expectedJson = "{\"orderno\":398482,\"level\":\"2\",\"code\":\"1\",\"parent\":\"A\",\"description\":\"Crop and animal production, hunting and related service activities\",\"thisItemIncludes\":\"This division includes two basic activities, namely the production of crop products and production of animal products, covering also the forms of organic agriculture, the growing of genetically modified crops and the raising of genetically modified animals. This division includes growing of crops in open fields as well in greenhouses.\\r\\n \\r\\nGroup 01.5 (Mixed farming) breaks with the usual principles for identifying main activity. It accepts that many agricultural holdings have reasonably balanced crop and animal production, and that it would be arbitrary to classify them in one category or the other.\",\"thisItemAlsoIncludes\":\"This division also includes service activities incidental to agriculture, as well as hunting, trapping and related activities.\",\"rulings\":null,\"thisItemExcludes\":\"Agricultural activities exclude any subsequent processing of the agricultural products (classified under divisions 10 and 11 (Manufacture of food products and beverages) and division 12 (Manufacture of tobacco products)), beyond that needed to prepare them for the primary markets. The preparation of products for the primary markets is included here.\\r\\n\\r\\nThe division excludes field construction (e.g. agricultural land terracing, drainage, preparing rice paddies etc.) classified in section F (Construction) and buyers and cooperative associations engaged in the marketing of farm products classified in section G. Also excluded is the landscape care and maintenance, which is classified in class 81.30.\",\"referenceToISIC4\":\"1\"}";
       String unExpectedJson = "{\"orderno\":398482,\"level\":\"2\",\"code\":\"3\",\"parent\":\"A\",\"description\":\"Crop and animal production, hunting and related service activities\",\"thisItemIncludes\":\"This division includes two basic activities, namely the production of crop products and production of animal products, covering also the forms of organic agriculture, the growing of genetically modified crops and the raising of genetically modified animals. This division includes growing of crops in open fields as well in greenhouses.\\r\\n \\r\\nGroup 01.5 (Mixed farming) breaks with the usual principles for identifying main activity. It accepts that many agricultural holdings have reasonably balanced crop and animal production, and that it would be arbitrary to classify them in one category or the other.\",\"thisItemAlsoIncludes\":\"This division also includes service activities incidental to agriculture, as well as hunting, trapping and related activities.\",\"rulings\":null,\"thisItemExcludes\":\"Agricultural activities exclude any subsequent processing of the agricultural products (classified under divisions 10 and 11 (Manufacture of food products and beverages) and division 12 (Manufacture of tobacco products)), beyond that needed to prepare them for the primary markets. The preparation of products for the primary markets is included here.\\r\\n\\r\\nThe division excludes field construction (e.g. agricultural land terracing, drainage, preparing rice paddies etc.) classified in section F (Construction) and buyers and cooperative associations engaged in the marketing of farm products classified in section G. Also excluded is the landscape care and maintenance, which is classified in class 81.30.\",\"referenceToISIC4\":\"1\"}";
       assertEquals(expectedJson, responseJson);
       assertNotEquals(unExpectedJson, responseJson);
    }

    @Test
    public void postEconomicActivities(){
        JobDetails jobDetails = new JobDetails(498482L,"2","1","A","Fishing","Fishing on Lake","growing fish","","","A");
        ResponseEntity<JobDetails> responseEntity = this.restTemplate
                .postForEntity(baseURL +POST_URI, jobDetails, JobDetails.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(498482L, responseEntity.getBody().getOrderno());
        assertEquals(jobDetails, responseEntity.getBody());
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
        assertEquals(jobDetails, responseEntity.getBody());
        jobDetails.setCode("2");
        assertNotEquals(jobDetails, responseEntity.getBody());
    }

}
