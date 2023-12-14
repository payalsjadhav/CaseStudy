package com.carwash.packageservice.service;

import java.util.List;
import java.util.Optional;

import com.carwash.packageservice.model.Package;

public interface PackageService {

	String addPackage(Package apackage);
	void updatePackage(Package apackage );
	Optional<Package> getPackageByPackageId(String packageId);
	List<Package> getAllPackages();
	void deletePackage(String packageId);
}
