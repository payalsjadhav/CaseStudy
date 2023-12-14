package com.carwash.washerservice.repository;

import com.carwash.washerservice.model.Washer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface WasherRepository extends MongoRepository<Washer,Integer> {

    @Query("{userName:'?0'}")
    Optional<Washer> findByUserName(String userName);

    @Query("{phoneNo:'?0'}")
    Optional<Washer> findByPhoneNo(String phoneNo);

    @Query(value = "{userName:'?0'}",exists = true)
    Boolean existByUserName(String userName);

    @Query(value="{userName:'?0'}", delete = true)
    void deleteByUserName(String userName);

    @Query("{role: '?0' }")
    Optional<List<Washer>> findUserByRole(String role);

    @Query("{userName:'?0'}")
    Washer getByUserName(String userName);

}
