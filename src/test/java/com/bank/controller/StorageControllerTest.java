package com.bank.controller;

import com.bank.service.StorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by cetin on 4/24/2019.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(StorageController.class)
public class StorageControllerTest {
    @MockBean
    StorageService storageService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void fileUpload() throws Exception {
        MockMultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
        when(storageService.uploadFile(any(MultipartFile.class))).thenReturn(true);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/upload/file")
                .file("file", file.getBytes())).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(true, storageService.uploadFile(file));
        assertEquals("file successfully uploaded", response.getContentAsString());
    }

    @Test
    public void singleUpload() throws Exception {
        Mockito.when(
                storageService.uploadSingleRecord(Mockito.anyInt(), Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyString(), Mockito.anyString()
                )).thenReturn(true);

        // Send single record
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/upload/single")
                .param("driverID", "12345")
                .param("price", "1.00")
                .param("volume", "1.00")
                .param("date", "12.03.2005")
                .param("fuelType", "D")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("record successfully uploaded", response.getContentAsString());
    }

}