package com.GowriShankar.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SOIL_PROPERTIES")
@Entity(name ="SOIL_PROPERTIES" )
public class Soil {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id")
    @SequenceGenerator(name = "id",sequenceName = "number_seq")
    public long id;

    @Column(name = "SOIL",nullable = false)
    public String name;

    @Column(name="LOOSE_DENSITY",nullable = false)
    private int looseCubicDensity;

    @Column(name="BUCKET_FILL_FACTOR",nullable = false)
    private double bucketFillFactor;

    @Column(name = "SWELL",nullable = false)
    private double swell;

    @Column(name = "SOIL_CONDITION",nullable = false)
    private String soilCondition;

    @Column(name = "BANK_DENSITY",nullable = false)
    private double bankCubicDensity;
}
