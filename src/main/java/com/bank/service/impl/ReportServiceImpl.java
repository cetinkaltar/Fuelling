package com.bank.service.impl;

import com.bank.converter.MonthlyExpenseConverter;
import com.bank.converter.MonthlyStatisticsConverter;
import com.bank.dao.FuellingRepository;
import com.bank.dto.MonthlyExpenseDTO;
import com.bank.dto.MonthlyStatisticDTO;
import com.bank.model.Fuelling;
import com.bank.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cetin on 4/23/2019.
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    FuellingRepository fuellingRepository;
    @Autowired
    MonthlyExpenseConverter monthlyExpenseConverter;
    @Autowired
    MonthlyStatisticsConverter monthlyStatisticsConverter;

    public List<MonthlyExpenseDTO> getMonthlyExpense() {
        //converting new string parametres to meaningful response 
        List<MonthlyExpenseDTO> expenseDTOS = new ArrayList<>();
        List<String> expenses = fuellingRepository.findMonthlyExpense();
        for (String line : expenses) {
            expenseDTOS.add(monthlyExpenseConverter.convert(line));
        }
        return expenseDTOS;
    }

    public List<MonthlyExpenseDTO> getMonthlyExpense(int driverID) {
        //converting new string parametres to meaningful response 
        List<MonthlyExpenseDTO> expenseDTOS = new ArrayList<>();
        List<String> expenses = fuellingRepository.findMonthlyExpense(driverID);
        for (String line : expenses) {
            expenseDTOS.add(monthlyExpenseConverter.convert(line));
        }
        return expenseDTOS;
    }
    public List<Fuelling> getFuellingsByMonth(int monthIndex) {
        return fuellingRepository.findFuellingsByMonth(monthIndex);
    }

    public List<Fuelling> getFuellingsByMonth(int monthIndex, int driverID) {
        return fuellingRepository.findFuellingsByMonth(monthIndex, driverID);
    }

    public List<MonthlyStatisticDTO> getMonthlyStatistics() {
        List<MonthlyStatisticDTO> statisticDTOS = new ArrayList<>();
        List<String> statistics = fuellingRepository.findMonthlyStatistics();
        for (String line : statistics) {
            statisticDTOS.add(monthlyStatisticsConverter.convert(line));
        }
        return statisticDTOS;
    }

    public List<MonthlyStatisticDTO> getMonthlyStatistics(int driverID) {
        List<MonthlyStatisticDTO> statisticDTOS = new ArrayList<>();
        List<String> statistics = fuellingRepository.findMonthlyStatistics(driverID);
        for (String line : statistics) {
            statisticDTOS.add(monthlyStatisticsConverter.convert(line));
        }
        return statisticDTOS;
    }
}
