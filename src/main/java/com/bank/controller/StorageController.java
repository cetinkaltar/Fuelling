package com.bank.controller;

import com.bank.exception.DataValidationException;
import com.bank.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;

/**
 * Created by cetin on 4/23/2019.
 */
@RestController
@RequestMapping(value = "/upload")
public class StorageController {

    @Autowired
    StorageService storageService;

    @PostMapping("/file")
    public String fileUpload(@RequestParam("file") MultipartFile file) throws ParseException, DataValidationException {
        if (file.isEmpty()) {
            return "upload:ERROR file is empty!";
        }
        storageService.uploadFile(file);
        return "file successfully uploaded";
    }

    @PostMapping("/single")
    public String singleUpload(@RequestParam("driverID") int driverID,
                               @RequestParam("price") double price,
                               @RequestParam("volume") double volume,
                               @RequestParam("date") String date,
                               @RequestParam("fuelType") String fuelType) throws ParseException, DataValidationException {
        storageService.uploadSingleRecord(driverID, price, volume, date, fuelType);
        return "record successfully uploaded";
    }
}
