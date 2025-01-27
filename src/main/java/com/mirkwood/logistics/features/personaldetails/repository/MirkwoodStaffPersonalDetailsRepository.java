package com.mirkwood.logistics.features.personaldetails.repository;

import com.mirkwood.logistics.core.models.MirkwoodStaffPersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MirkwoodStaffPersonalDetailsRepository extends JpaRepository<MirkwoodStaffPersonalDetails,Long> {
    Optional<MirkwoodStaffPersonalDetails> findByEmployeeIdAndIsDeletedFalse(String employeeId);
}
