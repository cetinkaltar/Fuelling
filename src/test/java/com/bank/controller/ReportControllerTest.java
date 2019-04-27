package com.bank.controller;

import com.bank.dto.MonthlyExpenseDTO;
import com.bank.dto.MonthlyStatisticDTO;
import com.bank.model.Fuelling;
import com.bank.service.ReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by cetin on 4/24/2019.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ReportController.class)
public class ReportControllerTest {
    @MockBean
    ReportService reportService;

    @Autowired
    private MockMvc mockMvc;

    MonthlyExpenseDTO monthlyExpenseDTO = new MonthlyExpenseDTO("2018", "100", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null");
    String expectedExpense = "[{\"year\":\"2018\",\"january\":\"100\",\"february\":\"null\",\"march\":\"null\",\"april\":\"null\",\"may\":\"null\",\"june\":\"null\",\"july\":\"null\",\"agust\":\"null\",\"september\":\"null\",\"october\":\"null\",\"november\":\"null\",\"december\":\"null\"}]";
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    String dateString = format.format(new Date());
    Fuelling fuelling = new Fuelling(13245, 1.00D, 10.00D, format.parse(dateString), "D");
    String expectedReport = "[{\"id\":0,\"driverId\":13245,\"price\":1.0,\"volume\":10.0,\"date\":\"2019-04-24T21:00:00.000+0000\",\"fuelType\":\"D\"}]";
    MonthlyStatisticDTO monthlyStatisticDTO = new MonthlyStatisticDTO("2018", "1", "G95", "100.00", "200.00");
    String expectedStatistics = "[{\"year\":\"2018\",\"month\":\"1\",\"fuelType\":\"G95\",\"avgPrice\":2.0,\"totalVolume\":\"100.00\",\"totalPrice\":\"200.00\"}]";

    public ReportControllerTest() throws ParseException {
    }

    @Test
    public void getMonthlyExpense() throws Exception {
        Mockito.when(reportService.getMonthlyExpense()).thenReturn(Arrays.asList(monthlyExpenseDTO));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/report/monthly")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedExpense, response.getContentAsString());
    }

    @Test
    public void getMonthlyExpense1() throws Exception {
        Mockito.when(reportService.getMonthlyExpense(Mockito.anyInt())).thenReturn(Arrays.asList(monthlyExpenseDTO));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/report/monthly/1234")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedExpense, response.getContentAsString());
    }

    @Test
    public void getReportByMonth() throws Exception {
        Mockito.when(reportService.getFuellingsByMonth(Mockito.anyInt())).thenReturn(Arrays.asList(fuelling));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/report/month/1")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedReport, response.getContentAsString());
    }

    @Test
    public void getReportByMonthAndID() throws Exception {
        Mockito.when(reportService.getFuellingsByMonth(Mockito.anyInt(), Mockito.anyInt())).thenReturn(Arrays.asList(fuelling));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/report/month/1/13245")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedReport, response.getContentAsString());
    }

    @Test
    public void getMonthlyStatistics() throws Exception {
        Mockito.when(reportService.getMonthlyStatistics()).thenReturn(Arrays.asList(monthlyStatisticDTO));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/report/monthly/statistics")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedStatistics, response.getContentAsString());
    }

    @Test
    public void getMonthlyStatistics1() throws Exception {
        Mockito.when(reportService.getMonthlyStatistics()).thenReturn(Arrays.asList(monthlyStatisticDTO));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/report/monthly/statistics")
                .param("driverID", "12345")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedStatistics, response.getContentAsString());
    }
}