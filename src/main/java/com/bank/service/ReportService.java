package com.bank.service;

import com.bank.dto.MonthlyExpenseDTO;
import com.bank.dto.MonthlyStatisticDTO;
import com.bank.model.Fuelling;

import java.util.List;

/**
 * Created by cetin on 4/23/2019.
 */
public interface ReportService {
    List<MonthlyExpenseDTO> getMonthlyExpense();

    List<MonthlyExpenseDTO> getMonthlyExpense(int driverID);

    List<Fuelling> getFuellingsByMonth(int monthIndex);

    List<Fuelling> getFuellingsByMonth(int monthIndex, int driverID);

    List<MonthlyStatisticDTO> getMonthlyStatistics();

    List<MonthlyStatisticDTO> getMonthlyStatistics(int driverID);
}
