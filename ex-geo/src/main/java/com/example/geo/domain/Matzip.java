package com.example.geo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Matzip extends BaseEntity{

    @Column(nullable = false)
    String lat;

    @Column(nullable = false)
    String lon;

}
