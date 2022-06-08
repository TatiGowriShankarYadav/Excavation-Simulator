package com.GowriShankar.controller;



import com.GowriShankar.service.ExcavatorService;
import com.GowriShankar.model.Excavator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/excavator")
public class ExcavatorController {

    @Autowired
    ExcavatorService excavatorService;

    @GetMapping("/")
    public String getController(){
        return "your are in Excavator Controller Class";
    }

    //----------------Add Excavator (POST)--------------------------------------------------------------
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Excavator addExcavator(@RequestBody Excavator excavator){
        return excavatorService.addExcavator(excavator);
    }

    //--------------------REQUEST (GET)--------------------------------------------------------------
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Excavator> getDetails(){
        return excavatorService.getAllExcavators();
    }

    @RequestMapping(value = "/id/{id}",method = RequestMethod.GET)
    public Excavator getExcavatorById(@PathVariable(value = "id") Long id){
        return excavatorService.getExcavatorById(id);
    }

    @RequestMapping(value = "/name/{name}",method = RequestMethod.GET)
    public Excavator getExcavatorByName(@PathVariable String name){
        return excavatorService.getExcavatorByName(name);
    }

    //----------------------------------MODIFY (PUT)----------------------------------------
    @RequestMapping(value = "/name/{name}",method = RequestMethod.PUT)
    public Excavator modifyExcavator (@PathVariable String name,@RequestBody Excavator excavator){
        return excavatorService.modifyExcavatorByName(name,excavator);
    }

    @RequestMapping(value = "/id/{id}",method = RequestMethod.PUT)
    public Excavator modifyExcavator (@PathVariable(value = "id") Long id,@RequestBody Excavator excavator){
        return excavatorService.modifyExcavatorById(id,excavator);
    }

    //------------------------------------DELETE (DELETE)----------------------------------------
    @RequestMapping(value = "/name/{name}",method = RequestMethod.DELETE)
    public String deleteExcavator (@PathVariable String name){
        return excavatorService.deleteExcavatorByName(name);
    }

    @RequestMapping(value = "/id/{id}",method = RequestMethod.DELETE)
    public String deleteExcavator (@PathVariable(value = "id") Long id){
        return excavatorService.deleteExcavatorId(id);
    }

}
