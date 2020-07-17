package com.example.carrierapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class DailyCourierTrackingInfo {

    private String id;

    private Long courierId;

    private String storeId;

    private long timestamp;
}
