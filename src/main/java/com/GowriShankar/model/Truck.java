package com.GowriShankar.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRUCK")
@Entity(name = "TRUCK")
public class Truck {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id")
    @SequenceGenerator(name = "id",sequenceName = "number_seq")
    public long id;

    @Column(name="NAME",nullable = false)
    private String name;

    @Column(name="HIRE_COST",nullable = false)
    private int hireCostPerMonth;

    @Column(name="MILEAGE",nullable = false)
    private double mileageInKmPerLit;

    @Column(name="CAPACITY",nullable = false)
    private double CapacityInCUM;

    @Column(name="BODY_WEIGHT",nullable = false)
    private int bodyWeightInKG; // in kg

    @Column(name="PAYLOAD_WEIGHT",nullable = false)
    private int payloadWeightInKG; // in kg

    @Column(name="HP",nullable = false)
    private int horsePower;




}
