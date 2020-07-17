package com.example.carrierapp.services;

import com.example.carrierapp.controllers.CourierLocationRequest;
import com.example.carrierapp.entity.Store;
import com.example.carrierapp.repositories.CourierLocationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

class CourierLocationServiceTest {

    @InjectMocks
    CourierLocationService courierLocationService;

    @Mock
    CourierLocationRepo courierLocationRepo;

    private CourierLocationRequest courierLocationRequest;
    private List<Store> nearestStores;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        courierLocationRequest = new CourierLocationRequest();
    }


    @Test
    public void addCourierLocationIfNotEnteredTest() {

        courierLocationRequest.setCourierId(212312312);
        courierLocationRequest.setLat(50.9923307);
        courierLocationRequest.setLng(79.1244229);
        courierLocationRequest.setTimestamp(2020 - 07 - 16);
        Mockito.when(courierLocationRepo.findNearestStores(50.9923307, 79.1244229)).thenReturn(nearestStores);
        nearestStores = courierLocationRepo.findNearestStores(50.9923307, 79.1244229);


        Mockito.verify(courierLocationRepo, Mockito.times(1)).findNearestStores(50.9923307, 79.1244229);

    }


}