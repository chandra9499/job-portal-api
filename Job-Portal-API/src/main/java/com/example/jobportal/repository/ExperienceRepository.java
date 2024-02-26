package com.example.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobportal.entity.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Integer> {
	
//	@Query("select trunc(months_between(e.endDate,e.startingDate)/12) from Experience e")
//  public Double findWorkExperience(LocalDate endDate,LocalDate startingDate);
	
//	 @Query("SELECT SUM(e.endDate - e.startingDate) FROM Experience e WHERE e.startingDate <= :endDate AND (e.endDate IS NULL OR e.endDate >= :startingDate)")
//	    Double findWorkExperience(@Param("endDate") LocalDate endDate, @Param("startingDate") LocalDate startingDate);
	
//	@Query("SELECT COALESCE(SUM(CAST((e.endDate - e.startingDate) AS DOUBLE)), 0.0) FROM Experience e WHERE e.startingDate <= :endDate AND (e.endDate IS NULL OR e.endDate >= :startingDate)")
//    Double findWorkExperience(@Param("endDate") LocalDate endDate, @Param("startingDate") LocalDate startingDate);

//	@Query("SELECT COALESCE(SUM(MONTHS_BETWEEN(e.endDate, e.startingDate)), 0.0) FROM Experience e WHERE e.startingDate <= :endDate AND (e.endDate IS NULL OR e.endDate >= :startingDate)")
//    Double findWorkExperience(@Param("endDate") LocalDate endDate, @Param("startingDate") LocalDate startingDate);
	
//	@Query("SELECT COALESCE(SUM(TIMESTAMPDIFF(DAY, e.startingDate, e.endDate)), 0.0) FROM Experience e WHERE e.startingDate <= :endDate AND (e.endDate IS NULL OR e.endDate >= :startingDate)")
//    Double findWorkExperience(@Param("endDate") LocalDate endDate, @Param("startingDate") LocalDate startingDate);
	
//	@Query("SELECT COALESCE(SUM(TIMESTAMPDIFF(DAY, e.startingDate, e.endDate)) / 365.25, 0.0) FROM Experience e WHERE e.startingDate <= :endDate AND (e.endDate IS NULL OR e.endDate >= :startingDate)")
//    Double findWorkExperience(@Param("endDate") LocalDate endDate, @Param("startingDate") LocalDate startingDate);

//	@Query("SELECT COALESCE(SUM(TIMESTAMPDIFF(MONTH, e.startingDate, e.endDate)), 0.0) FROM Experience e WHERE e.startingDate <= :endDate AND (e.endDate IS NULL OR e.endDate >= :startingDate)")
//    Double findWorkExperience(@Param("endDate") LocalDate endDate, @Param("startingDate") LocalDate startingDate);



}
