package com.bank.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by cetin on 4/22/2019.
 */
@Data
@Entity
@Table(name = "FUELLING")
public class Fuelling {
    @Id
    @GeneratedValue
    private long id;
    private Integer driverId;
    private Double price;
    private Double volume;
    @Type(type="date")
    private Date date;
    private FuelType fuelType;

    public Fuelling(Integer driverId, Double price, Double volume, Date date, String fuelType) {
        this.driverId = driverId;
        this.price = price;
        this.volume = volume;
        this.date = date;
        this.fuelType = FuelType.valueOf(fuelType);
    }

    public Fuelling() {
    }

    public enum FuelType {
        D,
        G95,
        G98;
    }
}
