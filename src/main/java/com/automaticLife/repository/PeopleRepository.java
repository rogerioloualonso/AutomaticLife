package com.automaticLife.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.automaticLife.repository.entity.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {

	@Query(value = "SELECT * FROM people p WHERE DAY(p.birthday) = :day AND MONTH(p.birthday) = :month", nativeQuery = true)
	List<People> searchBirthdays(@Param("month") String month, @Param("day") String day);
}