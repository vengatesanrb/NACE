package com.example.nace.job.controller;

import com.example.nace.job.entities.Job;
import com.example.nace.job.exceptions.NaceAlreadyExistException;
import com.example.nace.job.mapper.Mapper;
import com.example.nace.job.service.NmcaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@WebMvcTest(NaceController.class)
public class NaceControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    NmcaService service;
    @SpyBean
    Mapper mapper;
    @Autowired
    private ObjectMapper objectMapper;
    String POST_URI = "/nace/add";
    String PUT_URI = "/nace/update/";
    String GET_URI = "/nace";

    Job agri;

    @BeforeEach
    void mockService(){
        agri = new Job(598481L, "A","2","AGRICULTURE, FORESTRY AND FISHING","","","","","","B" );
        when(service.getNaceDetails(598481L)).thenReturn(agri);
        when(service.addNaceDetails(isA(Job.class))).thenThrow(new NaceAlreadyExistException());
        when(service.updateNaceDetails(isA(Job.class), anyLong())).thenReturn(agri);
    }

    @Test
    public void checkNaceAgainstId() throws Exception{
        MvcResult mvcResult =this.mvc.perform(MockMvcRequestBuilders.get(GET_URI+"/598481")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("AGRICULTURE") && content.contains("FORESTRY AND FISHING"));
    }

    @Test
    public void postAlreadyExistingOne() throws Exception{
        MvcResult mvcResult =this.mvc.perform(MockMvcRequestBuilders.post(POST_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(agri))).andReturn();
        assertEquals(HttpStatus.CONFLICT.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void postNewNace() throws Exception{
        agri = new Job(598481L, "A","2","ANIMAL CARE","","","","","","A");
        when(service.addNaceDetails(isA(Job.class))).thenReturn(agri);
        MvcResult mvcResult =this.mvc.perform(MockMvcRequestBuilders.post(POST_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(agri))).andReturn();
        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void putNewNace() throws Exception{
        agri = new Job(598481L, "A","2","FISH CARE","","","","","","A");
        when(service.addNaceDetails(isA(Job.class))).thenReturn(agri);
        MvcResult mvcResult =this.mvc.perform(MockMvcRequestBuilders.put(PUT_URI+"598481")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(agri))).andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
}
