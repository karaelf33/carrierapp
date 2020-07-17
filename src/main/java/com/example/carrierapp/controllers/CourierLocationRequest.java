package com.example.carrierapp.controllers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourierLocationRequest {
    double lat;
    double lng;
    long courierId;
    long timestamp;
}
