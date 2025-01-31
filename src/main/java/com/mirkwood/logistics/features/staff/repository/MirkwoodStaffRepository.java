package com.mirkwood.logistics.features.staff.repository;

import com.mirkwood.logistics.core.models.MirkwoodStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MirkwoodStaffRepository extends JpaRepository<MirkwoodStaff,Long> {
    Optional<MirkwoodStaff> findByStaffUsername(String staffUsername);
    Optional<MirkwoodStaff> findByStaffEmailId(String staffEmailId);
}
