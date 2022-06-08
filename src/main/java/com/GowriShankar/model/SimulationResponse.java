package com.GowriShankar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimulationResponse {
    private String excavatorName;
    private int noOfExcavators;
    private String truckName;
    private int noOfTrucks;
    private long costOfCombination;

}
