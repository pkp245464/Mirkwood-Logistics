package com.mirkwood.logistics.features.personaldetails.repository;

import com.mirkwood.logistics.core.models.MirkwoodStaffPersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MirkwoodStaffPersonalDetailsRepository extends JpaRepository<MirkwoodStaffPersonalDetails,Long> {
    Optional<MirkwoodStaffPersonalDetails> findByEmployeeIdAndIsDeletedFalse(String employeeId);
    Optional<MirkwoodStaffPersonalDetails> findByEmailIdAndIsDeletedFalse(String emailId);
    Optional<MirkwoodStaffPersonalDetails> findByPhoneNumberAndIsDeletedFalse(String phoneNumber);

    List<MirkwoodStaffPersonalDetails> findByIsDeletedFalse();
    List<MirkwoodStaffPersonalDetails> findByFirstNameContainingIgnoreCaseAndIsDeletedFalse(String firstName);
    List<MirkwoodStaffPersonalDetails> findByLastNameContainingIgnoreCaseAndIsDeletedFalse(String secondName);
    List<MirkwoodStaffPersonalDetails> findByDepartmentContainingIgnoreCaseAndIsDeletedFalse(String department);
}
