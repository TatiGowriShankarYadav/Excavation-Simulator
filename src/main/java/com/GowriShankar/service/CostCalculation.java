package com.GowriShankar.service;


import com.GowriShankar.model.SimulationInputs;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class CostCalculation {


    public List<String> costCalculationList(SimulationInputs userInputs) {
        List<String> listCostCalculation = new ArrayList<>();
        getCost(userInputs);
        listCostCalculation.add(userInputs.getExcavator());
        listCostCalculation.add(String.valueOf(noOfExcavators(userInputs)));
        listCostCalculation.add(userInputs.getTruck());
        listCostCalculation.add(String.valueOf(noOfTrucks(userInputs)));
        listCostCalculation.add(String.valueOf(getCost(userInputs)));
        return listCostCalculation;
    }
    private double getCost(SimulationInputs userInputs){
        return noOfExcavators(userInputs)*500+noOfTrucks(userInputs)*120;
    }

    private int noOfExcavators(SimulationInputs userInputs) {
        return (int) userInputs.getHaulDistance()*userInputs.getHaulRoadGrade();
    }

    private int noOfTrucks(SimulationInputs userInputs) {
        return userInputs.getNetQuantity() /userInputs.getNoRainMonths();
    }


}
