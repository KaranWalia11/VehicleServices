package com.techshard.graphql.dao.repository;

import com.techshard.graphql.dao.entity.Service;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
	
	@Query("SELECT s FROM Service s WHERE s.status LIKE %:status%")
	List<Service> searchByStatus(@Param("status") String status);

}
