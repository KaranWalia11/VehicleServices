package com.techshard.graphql.dao.repository;

import com.techshard.graphql.dao.entity.Vehicle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	
	@Query("SELECT v FROM Vehicle v WHERE v.name LIKE %:name%")
	List<Vehicle> searchByNameLike(@Param("name") String name);
	
}
