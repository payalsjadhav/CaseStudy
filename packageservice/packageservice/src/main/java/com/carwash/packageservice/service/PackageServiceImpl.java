package com.carwash.packageservice.service;

import com.carwash.packageservice.model.Package;
import com.carwash.packageservice.exception.PackageNotFoundException;
import com.carwash.packageservice.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageServiceImpl implements PackageService {

	@Autowired
	PackageRepository packageRepository;

	@Override
	public String addPackage(Package apackage) {
		packageRepository.save(apackage);
		return apackage.getPackageName();
	}

	@Override
	public void updatePackage(Package aPackage) {
		if(packageRepository.existsById(aPackage.getPackageName()))
			packageRepository.save(aPackage);
		else
			throw new PackageNotFoundException("Package does not exists...");
	}

    @Override
	public Optional<Package> getPackageByPackageId(String aPackageId) {
		Optional<Package> aPackage;
		if(packageRepository.existsById(aPackageId))
			aPackage =packageRepository.findById(aPackageId);
		else
			throw new PackageNotFoundException("Package does not exists...");
		return aPackage;
	}

	@Override
	public List<Package> getAllPackages() {
		return packageRepository.findAll();
	}

	@Override
	public void deletePackage(String aPackageId) {
		if(packageRepository.existsById(aPackageId))
			packageRepository.deleteById(aPackageId);
		else
			throw new PackageNotFoundException("Package does not exists...");
	}

}
