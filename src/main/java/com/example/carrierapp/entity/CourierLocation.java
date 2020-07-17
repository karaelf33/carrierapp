package com.example.carrierapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Document
public class CourierLocation {

    @Id
    public String id;
    @Field
    public Geo geo;
    @Field
    public String timestamp;
}

