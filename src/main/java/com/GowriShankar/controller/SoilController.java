package com.GowriShankar.controller;


import com.GowriShankar.model.Soil;
import com.GowriShankar.service.SoilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soil")
public class SoilController {

    @Autowired
    SoilService soilService;

    @GetMapping("/")
    public String getController() {
        return "You entered soil class";
    }

    //----------------Add SOil (POST)--------------------------------------------------------------
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Soil addSoil(@RequestBody Soil soil) {
        return soilService.addSoil(soil);
    }

    //----------------get Soil (GET)--------------------------------------------------------------
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Soil> getAllSoils() {
        return soilService.getAllSoils();
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Soil getSoilById(@PathVariable(value = "id") Long id) {
        return soilService.getSoilById(id);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public Soil getSoilByName(@PathVariable String name) {
        return soilService.getSoilByName(name);
    }

    //----------------Modify soil (PUT)--------------------------------------------------------------
    @RequestMapping(value = "/name/{name}", method = RequestMethod.PUT)
    public Soil modifySoilByName(@PathVariable String name, @RequestBody Soil soil) {
        return soilService.modifySoilByName(name, soil);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.PUT)
    public Soil getSoilByName(@PathVariable(value = "id") Long id, @RequestBody Soil soil) {
        return soilService.modifySoilById(id, soil);
    }


    //----------------Delete soil (DELETE)--------------------------------------------------------------

    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    public String deleteSoilById(@PathVariable(value = "id") Long id) {
        return soilService.deleteSoilById(id);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.DELETE)
    public String delete(@PathVariable String name) {
        return soilService.deleteSoilByName(name);
    }

}
