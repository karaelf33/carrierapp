package com.example.carrierapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Carrier {

    private String id;

    private String name;
}
