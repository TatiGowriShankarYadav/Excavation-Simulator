package com.GowriShankar.service;

import com.GowriShankar.model.Excavator;
import com.GowriShankar.model.SimulationInputs;
import com.GowriShankar.model.SimulationResponse;
import com.GowriShankar.model.Truck;
import com.GowriShankar.repository.ExcavatorRepository;
import com.GowriShankar.repository.SoilRepository;
import com.GowriShankar.repository.TruckRepository;
import lombok.Getter;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;


@Getter
@NoArgsConstructor
@Component
public class ExcavatorTruckSimulationService {

    private List<SimulationResponse> simulationCostList;
    public String[] rainCondition = {"no rain","moderate rain","heavy rain"};

    @Autowired
    ExcavatorRepository excavatorRepository;
    TruckRepository truckRepository;
    SoilRepository soilRepository;





    public List<SimulationResponse> getSimulationCostList(SimulationInputs userInputs ,
                                                          SimulationResponse sRes ){
        for (String excavatorName : userInputs.getExcavatorList()){
            Excavator ex = excavatorRepository.getExcavatorByName(excavatorName);
            for (String truckName : userInputs.getTruckList()){
                Truck tr = truckRepository.getTruckByName(truckName);

                simulationCostList.add(costCalculation (ex,tr,userInputs,sRes));
            }

        }
        return simulationCostList;
    }


    private SimulationResponse costCalculation(Excavator ex, Truck tr, SimulationInputs userInputs,
                                               SimulationResponse sRes) {

           double cost=0;
           double productivity=0;

            for (String rain : rainCondition) {

                cost = cost +
                        getCost(rain,ex,tr,userInputs)*getNumberOfMonths(rain,userInputs);
               productivity = productivity +
                       getMonthProductivity(rain,ex,tr,userInputs)*getNumberOfMonths(rain,userInputs);
            }
            int noOfExcavators = (int) Math.ceil(userInputs.getNetQuantity()/productivity);


            sRes.setExcavatorName(ex.getName());
            sRes.setNoOfExcavators(noOfExcavators);
            sRes.setTruckName(tr.getName());
            sRes.setNoOfTrucks(noOfTrucksPerExcavator("no rain",ex,tr,userInputs)*noOfExcavators);
            sRes.setCostOfCombination((long) cost);

            return sRes;

        }

    private double getCost(String rain, Excavator ex, Truck tr, SimulationInputs userInputs) {
        double maximumExcavatorHours = getMonthProductivity(rain,ex,tr,userInputs) /
                                        excavatorHourlyProductivity(ex,userInputs);

        double distanceCoveredByTrucks = ( getMonthProductivity(rain,ex,tr,userInputs) /
                                            maximumCapacityOfTruck(tr,userInputs) )
                                            * userInputs.getHaulDistance() *2;

        return (    ex.getHireCostPerMonth() +
                    maximumExcavatorHours * ex.getFuelConsumptionPerHour()*userInputs.getDieselRatePerLiter() +
                    tr.getHireCostPerMonth() * noOfTrucksPerExcavator(rain,ex,tr,userInputs) +
                    ( distanceCoveredByTrucks / tr.getMileageInKmPerLit() ) * userInputs.getDieselRatePerLiter()
                );
    }

    private double maximumCapacityOfTruck(Truck tr, SimulationInputs userInputs) {
       return Math.min( tr.getCapacityInCUM() ,
               (tr.getPayloadWeightInKG() / userInputs.getSoil().getLooseCubicDensity())
       );
    }

    private double truckHourlyProductivity(String rain,Excavator ex, Truck tr,SimulationInputs userInputs){

        double loadingTime = excavatorCycleTimeInMin(ex,userInputs)*
                              (int)  maximumCapacityOfTruck(tr,userInputs)/ex.getCapacityInCUM();


        double haulTime = userInputs.getHaulDistance() /
               Math.min (375*tr.getHorsePower()*0.7*1.61*avgSpeedFactor(userInputs) /
                (2.20462 * userInputs.getHaulRoadGrade()
                    * maximumCapacityOfTruck(tr,userInputs)*userInputs.getSoil().getLooseCubicDensity())
                       ,  55) * speedReductionFactor(rain) ; // 55 max speed;  0.7 --> gear efficiency, 1.61 --> MPH to KMPH

        return ( 60 * 0.9 * maximumCapacityOfTruck(tr,userInputs) /
                (loadingTime+haulTime*2+1.8)
                ); // 0.9 ---> truck utilisation factor;  1.8 ---> dump and Spot time
    }

    private double speedReductionFactor(String rain) {
        if (rain.equals("no rain")){
            return 1;
        } else if (rain.equals("moderate rain")){
            return 0.93;
        } else {
            return 0.9;
        }
    }

    private int noOfTrucksPerExcavator(String rain,Excavator ex,Truck tr,SimulationInputs userInputs){
        return (int) Math.ceil ( excavatorHourlyProductivity(ex,userInputs) /
                                truckHourlyProductivity(rain,ex,tr,userInputs));
    }

    private double avgSpeedFactor(SimulationInputs userInputs) {
        if (userInputs.getHaulDistance()<=0.4){
            return 0.45;
        }
        return 0.86;
    }


    private int getNumberOfMonths(String rain,SimulationInputs userInputs) {
        if (rain.equals("no rain")){
            return userInputs.getNoRainMonths();
        } else if (rain.equals("moderate rain")){
            return userInputs.getModerateRainMonths();
        } else {
            return userInputs.getHeavyRainMonths();
        }
    }

    private double getMonthProductivity(String rain,Excavator ex,Truck tr,SimulationInputs userInputs) {
            return Math.min(excavatorMonthlyProductivity(rain,ex,userInputs),
                    truckMonthlyTruckProductivity(rain,ex,tr,userInputs));
        }

    private double truckMonthlyTruckProductivity(String rain, Excavator ex, Truck tr, SimulationInputs userInputs) {
        return truckHourlyProductivity(rain,ex,tr,userInputs)*
                (availableHours(rain)*userInputs.getDaysPerMonth()*userInputs.getShifts()*10);
    }


    private double excavatorMonthlyProductivity(String rain, Excavator ex,SimulationInputs userInputs){
            return excavatorHourlyProductivity(ex,userInputs)*
                    (availableHours(rain)*userInputs.getDaysPerMonth()*userInputs.getShifts()*10);
        }

        private double excavatorHourlyProductivity(Excavator ex,SimulationInputs userInputs) {
            return ex.getCapacityInCUM()*getOperatingEfficiency(userInputs)*operatorEfficiency()*
                    userInputs.getSoil().getBucketFillFactor()*60 /
                         (userInputs.getSoil().getSwell()*excavatorCycleTimeInMin(ex,userInputs))    ;

        }

    private double operatorEfficiency() {
        return 0.9;
    }

    private double getOperatingEfficiency(SimulationInputs userInputs) {
    if (userInputs.getManagementCondition().equals("Excellent") &&
            userInputs.getSoil().getSoilCondition().equals("Average")){
        return 0.83;
        }
    return 0.75;
    }

    private double excavatorCycleTimeInMin(Excavator ex,SimulationInputs userInputs) {
            if (ex.getCapacityInCUM()<1 ||
                    userInputs.getSoil().getSoilCondition().equals("Excellent")){
                return 0.35;
            }
            else {
            return 0.56;
            }
    }

    public double availableHours(String rainCondition){
        if  (rainCondition.equals("no rain")){
            return 1;
        } else  if (rainCondition.equals("moderate rain")){
            return 0.74;
        } else  {
            return 0.5;
        }
    }




} // Class level
