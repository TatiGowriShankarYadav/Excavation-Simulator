package com.GowriShankar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EXCAVATOR")
@Entity(name = "EXCAVATOR")
public class Excavator {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id")
    @SequenceGenerator(name = "id",sequenceName = "number_seq")
    public long id;

    @Column(name = "NAME",nullable = false)
    private String name;

    @Column(name = "BUCKET_CAPACITY",nullable = false)
    private double capacityInCUM;

    @Column(name = "HIRE_COST",nullable = false)
    private int hireCostPerMonth;

    @Column(name = "FUEL_CONSUMPTION",nullable = false)
    private double fuelConsumptionPerHour; // lit/hr.
}
