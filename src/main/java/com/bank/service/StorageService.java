package com.bank.service;

import com.bank.exception.DataValidationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;

/**
 * Created by cetin on 4/23/2019.
 */
@Service
public interface StorageService{
    boolean uploadFile(MultipartFile file) throws ParseException, DataValidationException ;
    boolean uploadSingleRecord(int driverID,double price,double volume,String date,String fuelType) throws ParseException, DataValidationException;
}
