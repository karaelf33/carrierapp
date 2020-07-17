package com.example.carrierapp.services;

import com.example.carrierapp.entity.DailyCourierTrackingInfo;
import com.example.carrierapp.entity.Store;
import com.example.carrierapp.repositories.CourierLocationRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierLocationService {
    final
    CourierLocationRepo courierLocationRepo;

    public CourierLocationService(CourierLocationRepo courierLocationRepo) {
        this.courierLocationRepo = courierLocationRepo;
    }

    public List<Store> addCourierLocationIfNotEntered(long courierId, double lat, double lng, long timestamp) {

        List<Store> nearestStores = courierLocationRepo.findNearestStores(lat, lng);

        nearestStores.forEach(store -> {
            if (!courierLocationRepo.isEnteredBefore(courierId, store.getId(), timestamp)) {
                DailyCourierTrackingInfo dailyCourierTrackingInfo = new DailyCourierTrackingInfo();
                dailyCourierTrackingInfo.setCourierId(courierId);
                dailyCourierTrackingInfo.setStoreId(store.getId());

                courierLocationRepo.insertCourierTrackingInfo(dailyCourierTrackingInfo);
            }
        });
        return nearestStores;
    }
}
