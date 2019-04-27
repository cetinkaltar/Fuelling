package com.bank.dto;

import lombok.Data;

/**
 * Created by cetin on 4/23/2019.
 */
@Data
public class MonthlyStatisticDTO {
    private String year;
    private String month;
    private String fuelType;
    private double avgPrice;
    private String totalVolume;
    private String totalPrice;
    
    public MonthlyStatisticDTO(){}
    public MonthlyStatisticDTO(String year,String month,String fuelType,String totalVolume,String totalPrice){
        this.year=year;
        this.month=month;
        this.fuelType=fuelType;
        this.totalPrice=totalPrice;
        this.totalVolume=totalVolume;
        this.avgPrice=Double.valueOf(totalPrice)/Double.valueOf(totalVolume);
    }

}
