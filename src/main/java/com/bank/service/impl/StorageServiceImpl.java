package com.bank.service.impl;


import com.bank.dao.FuellingRepository;
import com.bank.exception.DataValidationException;
import com.bank.model.Fuelling;
import com.bank.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cetin on 4/23/2019.
 */
@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    FuellingRepository fuellingRepository;

    public boolean uploadFile(MultipartFile file) throws ParseException, DataValidationException {
        try {
            // Get the file and take data to save database
            byte[] bytes = file.getBytes();
            String completeData = new String(bytes);
            String[] rows = completeData.split("\r\n");
            //index to get problematic row number
            int rowIndex = 0;
            for (String row : rows) {
                rowIndex++;
                // data in each line is divided by symbol "|"
                String[] line = row.split("\\|");
                // set the price and amount to the proper format
                String priceFormatted = line[2].replace(",", ".");
                Double price = Double.valueOf(priceFormatted);
                String volumeFormatted = line[3].replace(",", ".");
                Double volume = Double.valueOf(volumeFormatted);
                // if negative value, show an error
                if (checkNegativeDataValue(Double.parseDouble(priceFormatted)))
                    throw new DataValidationException("price value cannot be minus, please check row " + rowIndex);
                // if negative value, show an error
                if (checkNegativeDataValue(Double.parseDouble(volumeFormatted)))
                    throw new DataValidationException("volume value cannot be minus, please check row " + rowIndex);
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                Date date = format.parse(line[4]);
                if (!line[1].equals("D") && !line[1].equals("G98") && !line[1].equals("G95"))
                    throw new DataValidationException("Fuel type must be D,G95 or G98 please check your fuel type entry at the row " + rowIndex);
                fuellingRepository.save(new Fuelling(Integer.valueOf(line[0]), price, volume, date, line[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean uploadSingleRecord(int driverID, double price, double volume, String date, String fuelType) throws ParseException, DataValidationException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        if (checkNegativeDataValue(price))
            throw new DataValidationException("price value cannot be minus");
        if (checkNegativeDataValue(volume))
            throw new DataValidationException("volume value cannot be minus");
        Date date1 = format.parse(date);
        fuellingRepository.save(new Fuelling(driverID, price, volume, date1, fuelType));
        return true;
    }

    private boolean checkNegativeDataValue(Double data) {
        if (data < 0)
            return true;
        return false;
    }
}
