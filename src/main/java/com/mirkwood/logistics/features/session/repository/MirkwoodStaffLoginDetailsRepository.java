package com.mirkwood.logistics.features.session.repository;

import com.mirkwood.logistics.core.models.MirkwoodStaffLoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MirkwoodStaffLoginDetailsRepository extends JpaRepository<MirkwoodStaffLoginDetails,Long> {
    Optional<MirkwoodStaffLoginDetails> findByStaffUsername(String staffUsername);
}
