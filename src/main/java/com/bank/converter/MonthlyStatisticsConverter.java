package com.bank.converter;

import com.bank.dto.MonthlyStatisticDTO;
import com.bank.model.Fuelling;
import org.springframework.stereotype.Service;

/**
 * Created by cetin on 4/23/2019.
 */
@Service
public class MonthlyStatisticsConverter {
    public MonthlyStatisticDTO convert(String line) {
        String[] data = line.split("\\,");
        if (data.length == 5) {
            for (int i = 0; i < data.length; i++) {
                //transforming null parameters to zero to get better representation
                if (data[i].equals("null"))
                    data[i] = "0.0";
            }
            //fuel type convert to enum back
            int fuelType = Integer.valueOf(data[2]);
            return new MonthlyStatisticDTO(data[0], data[1], Fuelling.FuelType.values()[fuelType].name(), data[3], data[4]);
        }
        return null;
    }
}
