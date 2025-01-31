package com.mirkwood.logistics.features.staff.utility;

import com.mirkwood.logistics.core.models.MirkwoodStaff;
import com.mirkwood.logistics.features.staff.dto.MirkwoodStaffDto;

public class MirkwoodStaffEntityToDTOMapper {
    public static MirkwoodStaffDto mapToDto(MirkwoodStaff staff) {
        return new MirkwoodStaffDto(
                staff.getStaffId(),
                staff.getStaffUsername(),
                staff.getStaffFullName(),
                staff.getStaffRole(),
                staff.getStaffOfficeCode(),
                staff.getStaffOfficeAddress(),
                staff.getStaffEmailId(),
                staff.getStaffPhoneNumber(),
                staff.getIsDeleted()
        );
    }
    public static MirkwoodStaff mapToEntity(MirkwoodStaffDto dto) {
        MirkwoodStaff staff = new MirkwoodStaff();
        staff.setStaffUsername(dto.getStaffUsername());
        staff.setStaffFullName(dto.getStaffFullName());
        staff.setStaffRole(dto.getStaffRole());
        staff.setStaffOfficeCode(dto.getStaffOfficeCode());
        staff.setStaffOfficeAddress(dto.getStaffOfficeAddress());
        staff.setStaffEmailId(dto.getStaffEmailId());
        staff.setStaffPhoneNumber(dto.getStaffPhoneNumber());
        staff.setIsDeleted(false);
        return staff;
    }
}
