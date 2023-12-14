package com.carwash.packageservice.repository;

import com.carwash.packageservice.model.AddOn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddonRepository extends MongoRepository<AddOn,String> {


}
