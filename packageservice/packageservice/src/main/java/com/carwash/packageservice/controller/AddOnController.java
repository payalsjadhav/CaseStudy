package com.carwash.packageservice.controller;

import com.carwash.packageservice.model.AddOn;
import com.carwash.packageservice.service.AddOnServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/add-on")
public class AddOnController {

    @Autowired
    AddOnServiceImpl addOnService;

    @PostMapping("/add")
   public ResponseEntity<String> createAddOn(@RequestBody AddOn addOn){
     AddOn addOn1 = addOnService.createAddOn(addOn);
     String name=addOn1.getAddOnName();
     return new ResponseEntity<>("add-on created "+name, HttpStatus.OK);
   }

   @PutMapping("/edit")
   public ResponseEntity<AddOn> editAddOn(@RequestBody AddOn addOn){
       AddOn addOn1= addOnService.updateAddon(addOn);
        return new ResponseEntity<>(addOn1,HttpStatus.OK);
   }

   @GetMapping("/all")
    public ResponseEntity<List<AddOn>> getAllAddOn(){
      List<AddOn> addOns=  addOnService.getAddonList();
        return new ResponseEntity<>(addOns,HttpStatus.OK);
   }

   @GetMapping("/{addOnName}")
    public ResponseEntity<AddOn> getAddOnByName(@PathVariable String addOnName){
     AddOn addOn =   addOnService.getAddOnByName(addOnName);
        return new ResponseEntity<>(addOn,HttpStatus.OK);
   }

   @DeleteMapping("/delete{addOnName}")
    public ResponseEntity<String> deleteAddOn(@PathVariable String addOnName){
        addOnService.deleteAddOn(addOnName);
        return new ResponseEntity<>("deleted add-on" ,HttpStatus.OK);
   }
}
