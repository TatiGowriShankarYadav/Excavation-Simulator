package com.GowriShankar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SimulationInputs {

    private String excavator;
    private String truck;

    private List<String> excavatorList;
    private List<String> truckList;


    private double haulDistance; // in km
    private int haulRoadGrade;

    private String soilName;
    private Soil soil;

    private int netQuantity;
    private int daysPerMonth;
    private int shifts;

    private int noRainMonths;
    private int moderateRainMonths;
    private int heavyRainMonths;

    private int dieselRatePerLiter;

    private String managementCondition;

}
