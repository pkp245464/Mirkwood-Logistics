package com.mirkwood.logistics.features.personaldetails.utility;

import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.core.models.MirkwoodStaffPersonalDetails;
import com.mirkwood.logistics.features.personaldetails.dto.MirkwoodStaffPersonalDetailsDTO;

import java.util.Objects;

public class MirkwoodStaffUpdateUtility {

    public MirkwoodStaffUpdateUtility() {
    }

    public static void updateEntityFromDto(MirkwoodStaffPersonalDetails staff, MirkwoodStaffPersonalDetailsDTO dto) {
        if (Objects.nonNull(dto.getFirstName())) {
            staff.setFirstName(dto.getFirstName());
        }
        if (Objects.nonNull(dto.getLastName())) {
            staff.setLastName(dto.getLastName());
        }
        if (Objects.nonNull(dto.getDateOfBirth())) {
            staff.setDateOfBirth(dto.getDateOfBirth());
        }
        if (Objects.nonNull(dto.getGender())) {
            staff.setGender(dto.getGender());
        }
        if (Objects.nonNull(dto.getNationality())) {
            staff.setNationality(dto.getNationality());
        }
        if (Objects.nonNull(dto.getEmailId())) {
            staff.setEmailId(dto.getEmailId());
        }
        if (Objects.nonNull(dto.getPhoneNumber())) {
            staff.setPhoneNumber(dto.getPhoneNumber());
        }
        if (Objects.nonNull(dto.getCurrentAddress())) {
            staff.setCurrentAddress(dto.getCurrentAddress());
        }
        if (Objects.nonNull(dto.getPermanentAddress())) {
            staff.setPermanentAddress(dto.getPermanentAddress());
        }
        if (Objects.nonNull(dto.getDesignation())) {
            staff.setDesignation(dto.getDesignation());
        }
        if (Objects.nonNull(dto.getDepartment())) {
            staff.setDepartment(dto.getDepartment());
        }
    }
}
