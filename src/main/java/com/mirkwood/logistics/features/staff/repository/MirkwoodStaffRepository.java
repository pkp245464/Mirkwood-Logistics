package com.mirkwood.logistics.features.staff.repository;

import com.mirkwood.logistics.core.models.MirkwoodStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MirkwoodStaffRepository extends JpaRepository<MirkwoodStaff,Long> {

}
