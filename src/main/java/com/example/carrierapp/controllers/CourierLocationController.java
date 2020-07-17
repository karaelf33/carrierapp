package com.example.carrierapp.controllers;

import com.example.carrierapp.entity.Store;
import com.example.carrierapp.services.CourierLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@RequestMapping("/courier-location")
@RestController()
public class CourierLocationController {

    @Autowired
    CourierLocationService courierLocationService;

    @PostMapping
    public List<Store> addCourierLocationIfNotEntered(@RequestBody CourierLocationRequest courierLocationRequest) {
        System.out.println(courierLocationRequest.getTimestamp());
        return courierLocationService.addCourierLocationIfNotEntered(courierLocationRequest.courierId, courierLocationRequest.getLat(), courierLocationRequest.getLng(), courierLocationRequest.getTimestamp());
    }
}
