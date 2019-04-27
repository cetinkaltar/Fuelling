package com.bank.controller;

import com.bank.dto.MonthlyExpenseDTO;
import com.bank.dto.MonthlyStatisticDTO;
import com.bank.model.Fuelling;
import com.bank.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by cetin on 4/23/2019.
 */
@RestController
@RequestMapping(value = "/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @GetMapping(value = "/monthly")
    public List<MonthlyExpenseDTO> getMonthlyExpense() {
        return reportService.getMonthlyExpense();
    }

    @GetMapping(value = "/monthly/{driverID}")
    public List<MonthlyExpenseDTO> getMonthlyExpense(@PathVariable("driverID") int driverID) {
        return reportService.getMonthlyExpense(driverID);
    }

    @GetMapping(value = "/month/{monthIndex}")
    public List<Fuelling> getReportByMonth(@PathVariable("monthIndex") int monthIndex) {
        return reportService.getFuellingsByMonth(monthIndex);
    }

    @GetMapping(value = "/month/{monthIndex}/{driverID}")
    public List<Fuelling> getReportByMonthAndID(@PathVariable("monthIndex") int monthIndex, @PathVariable("driverID") int driverID) {
        return reportService.getFuellingsByMonth(monthIndex, driverID);
    }

    @GetMapping(value = "/monthly/statistics")
    public List<MonthlyStatisticDTO> getMonthlyStatistics() {
        return reportService.getMonthlyStatistics();
    }

    @GetMapping(value = "/monthly/statistics/{driverID}")
    public List<MonthlyStatisticDTO> getMonthlyStatistics(@PathVariable("driverID") int driverID) {
        return reportService.getMonthlyStatistics(driverID);
    }
}
