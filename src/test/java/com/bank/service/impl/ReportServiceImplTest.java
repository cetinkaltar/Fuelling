package com.bank.service.impl;

import com.bank.converter.MonthlyExpenseConverter;
import com.bank.converter.MonthlyStatisticsConverter;
import com.bank.dao.FuellingRepository;
import com.bank.dto.MonthlyExpenseDTO;
import com.bank.dto.MonthlyStatisticDTO;
import com.bank.model.Fuelling;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cetin on 4/24/2019.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReportServiceImplTest {
    @Mock
    FuellingRepository fuellingRepository;
    @Mock
    MonthlyExpenseConverter monthlyExpenseConverter;
    @Mock
    MonthlyStatisticsConverter monthlyStatisticsConverter;

    @Test
    public void getMonthlyExpense() throws Exception {
        List<MonthlyExpenseDTO> expenseDTOS = new ArrayList<>();
        String expense = "2018,100,null,null,null,null,null,null,null,null,null,null,null";
        MonthlyExpenseDTO monthlyExpenseDTO = new MonthlyExpenseDTO("2018", "100", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null");
        List<String> expenses = Arrays.asList(expense);
        Mockito.when(monthlyExpenseConverter.convert(Mockito.anyString())).thenReturn(monthlyExpenseDTO);
        for (String line : expenses) {
            expenseDTOS.add(monthlyExpenseConverter.convert(line));
        }
        MonthlyExpenseDTO monthlyExpense = expenseDTOS.get(0);
        assertEquals("2018", monthlyExpense.getYear());
    }

    @Test
    public void getFuellingsByMonth() throws Exception {
        Fuelling fuelling = new Fuelling(1324, 1.10D, 100.00D, new Date(), "D");
        Mockito.when(fuellingRepository.findFuellingsByMonth(Mockito.anyInt())).thenReturn(Arrays.asList(fuelling));
        List<Fuelling> fuellings = fuellingRepository.findFuellingsByMonth(1);
        assertEquals(fuelling, fuellings.get(0));
    }

    @Test
    public void getMonthlyStatistics() throws Exception {
        String line = "2018,1,D,100.00,100.00";
        MonthlyStatisticDTO dto = new MonthlyStatisticDTO("20018", "1", "D", "100.00", "100.00");
        Mockito.when(monthlyStatisticsConverter.convert(Mockito.anyString())).thenReturn(dto);
        MonthlyStatisticDTO dto1 = monthlyStatisticsConverter.convert(line);
        assertEquals(dto.getYear(), dto1.getYear());
    }
}