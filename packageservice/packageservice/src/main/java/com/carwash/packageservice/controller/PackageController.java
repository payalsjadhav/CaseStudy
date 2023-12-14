package com.carwash.packageservice.controller;

import com.carwash.packageservice.model.Package;
import com.carwash.packageservice.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/package")
public class PackageController {

	@Autowired
	PackageService packageService;

	@PostMapping
	public ResponseEntity<String> addPackage(@RequestBody Package aPackage){
		String packageId=packageService.addPackage(aPackage);
		return new ResponseEntity<>("Plan added ID: "+packageId,HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<String> updatePackage(@RequestBody Package aPackage){
		packageService.updatePackage(aPackage);
		return new ResponseEntity<String>("Plan updated",HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{packageName}")
	public ResponseEntity<Optional<Package>> getPackageByPackageId(@PathVariable String packageName){
		Optional<Package> aPackage=packageService.getPackageByPackageId(packageName);
		return new ResponseEntity<>(aPackage,HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<Package>> getAllPlans(){
		List<Package> packages=packageService.getAllPackages();
		return new ResponseEntity<>(packages,HttpStatus.OK);
	}

	@DeleteMapping("/{packageName}")
	public ResponseEntity<String> deletePlan(@PathVariable String packageName){
		packageService.deletePackage(packageName);
		return new ResponseEntity<>("Plan deleted",HttpStatus.OK);
	}

}
