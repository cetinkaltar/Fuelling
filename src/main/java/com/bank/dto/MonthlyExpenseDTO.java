package com.bank.dto;

import lombok.Data;

/**
 * Created by cetin on 4/23/2019.
 */
@Data
public class MonthlyExpenseDTO {
    private String year;
    private String january;
    private String february;
    private String march;
    private String april;
    private String may;
    private String june;
    private String july;
    private String agust;
    private String september;
    private String october;
    private String november;
    private String december;

    public MonthlyExpenseDTO() {
    }

    public MonthlyExpenseDTO(String year, String january, String february, String march, String april, String may, String june, String july, String agust, String september, String november, String october, String december) {
        this.year=year;
        this.january = january;
        this.february = february;
        this.march = march;
        this.april = april;
        this.may = may;
        this.june = june;
        this.july = july;
        this.agust = agust;
        this.september = september;
        this.october = october;
        this.november = november;
        this.december = december;
    }
}
