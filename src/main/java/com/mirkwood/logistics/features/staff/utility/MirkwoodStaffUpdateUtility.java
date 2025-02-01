package com.mirkwood.logistics.features.staff.utility;

import com.mirkwood.logistics.core.models.MirkwoodStaff;
import com.mirkwood.logistics.features.staff.dto.MirkwoodStaffDto;

import java.util.Objects;
import java.util.function.Consumer;

public class MirkwoodStaffUpdateUtility {

    public MirkwoodStaffUpdateUtility() {

    }

    public static void updateStaffFields(MirkwoodStaff staff, MirkwoodStaffDto mirkwoodStaffDto) {
        if (Objects.nonNull(mirkwoodStaffDto.getStaffFullName())) {
            staff.setStaffFullName(mirkwoodStaffDto.getStaffFullName());
        }
        if (Objects.nonNull(mirkwoodStaffDto.getStaffEmailId())) {
            staff.setStaffEmailId(mirkwoodStaffDto.getStaffEmailId());
        }
        if (Objects.nonNull(mirkwoodStaffDto.getStaffPhoneNumber())) {
            staff.setStaffPhoneNumber(mirkwoodStaffDto.getStaffPhoneNumber());
        }
        if (Objects.nonNull(mirkwoodStaffDto.getStaffOfficeCode())) {
            staff.setStaffOfficeCode(mirkwoodStaffDto.getStaffOfficeCode());
        }
        if (Objects.nonNull(mirkwoodStaffDto.getStaffOfficeAddress())) {
            staff.setStaffOfficeAddress(mirkwoodStaffDto.getStaffOfficeAddress());
        }

    }
}
