package com.carwash.packageservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.carwash.packageservice.model.Package;

@Repository
public interface PackageRepository extends MongoRepository<Package, String>{


}