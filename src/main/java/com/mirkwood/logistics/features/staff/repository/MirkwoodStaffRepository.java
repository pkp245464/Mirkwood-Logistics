package com.mirkwood.logistics.features.staff.repository;

import com.mirkwood.logistics.core.enums.StaffRole;
import com.mirkwood.logistics.core.models.MirkwoodStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface MirkwoodStaffRepository extends JpaRepository<MirkwoodStaff,Long> {
    List<MirkwoodStaff> findByIsDeletedFalse();
    Optional<MirkwoodStaff> findByStaffIdAndIsDeletedFalse(Long staffId);
    Optional<MirkwoodStaff> findByStaffUsernameAndIsDeletedFalse(String staffUsername);
    Optional<MirkwoodStaff> findByStaffEmailIdAndIsDeletedFalse(String staffEmailId);
    List<MirkwoodStaff> findByStaffRoleAndIsDeletedFalse(StaffRole staffRole);
    List<MirkwoodStaff> findByStaffOfficeCodeAndIsDeletedFalse(String officeCode);
    List<MirkwoodStaff> findByStaffOfficeAddressContainingIgnoreCaseAndIsDeletedFalse(String officeAddress);

}
