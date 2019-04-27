package com.bank.converter;

import com.bank.dto.MonthlyExpenseDTO;
import org.springframework.stereotype.Service;

/**
 * Created by cetin on 4/23/2019.
 */
@Service
public class MonthlyExpenseConverter {
    public MonthlyExpenseDTO convert(String line) {
        String[] data = line.split("\\,");
        if (data.length == 13) {
            for (int i = 0; i < data.length; i++) {
                //transforming null parameters to zero to get better representation
                if (data[i].equals("null"))
                    data[i] = "0.0";
            }
            return new MonthlyExpenseDTO(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10], data[11], data[12]);
        }
        return null;
    }
}
