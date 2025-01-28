package com.mirkwood.logistics.features.personaldetails.utility;

import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.core.models.MirkwoodStaffPersonalDetails;
import com.mirkwood.logistics.features.personaldetails.dto.MirkwoodStaffPersonalDetailsDTO;

import java.util.Objects;

public class MirkwoodStaffPersonalEntityToDTOMapper {

    private MirkwoodStaffPersonalEntityToDTOMapper() {

    }

    public static MirkwoodStaffPersonalDetailsDTO mapToDto(MirkwoodStaffPersonalDetails mirkwoodStaffPersonalDetails) {
        if(Objects.isNull(mirkwoodStaffPersonalDetails)) {
            throw new CustomMirkwoodLogisticsExceptions("mirkwoodStaffPersonalDetails can't be null");
        }

        MirkwoodStaffPersonalDetailsDTO mirkwoodStaffPersonalDetailsDTO = new MirkwoodStaffPersonalDetailsDTO();

        mirkwoodStaffPersonalDetailsDTO.setFirstName(mirkwoodStaffPersonalDetails.getFirstName());
        mirkwoodStaffPersonalDetailsDTO.setLastName(mirkwoodStaffPersonalDetails.getLastName());
        mirkwoodStaffPersonalDetailsDTO.setDateOfBirth(mirkwoodStaffPersonalDetails.getDateOfBirth());
        mirkwoodStaffPersonalDetailsDTO.setGender(mirkwoodStaffPersonalDetails.getGender());
        mirkwoodStaffPersonalDetailsDTO.setNationality(mirkwoodStaffPersonalDetails.getNationality());
        mirkwoodStaffPersonalDetailsDTO.setEmailId(mirkwoodStaffPersonalDetails.getEmail());
        mirkwoodStaffPersonalDetailsDTO.setPhoneNumber(mirkwoodStaffPersonalDetails.getPhoneNumber());
        mirkwoodStaffPersonalDetailsDTO.setCurrentAddress(mirkwoodStaffPersonalDetails.getCurrentAddress());
        mirkwoodStaffPersonalDetailsDTO.setPermanentAddress(mirkwoodStaffPersonalDetails.getPermanentAddress());
        mirkwoodStaffPersonalDetailsDTO.setEmployeeId(mirkwoodStaffPersonalDetails.getEmployeeId());
        mirkwoodStaffPersonalDetailsDTO.setDateOfJoining(mirkwoodStaffPersonalDetails.getDateOfJoining());
        mirkwoodStaffPersonalDetailsDTO.setDesignation(mirkwoodStaffPersonalDetails.getDesignation());
        mirkwoodStaffPersonalDetailsDTO.setDepartment(mirkwoodStaffPersonalDetails.getDepartment());
        mirkwoodStaffPersonalDetailsDTO.setIsDeleted(mirkwoodStaffPersonalDetails.getIsDeleted());

        return mirkwoodStaffPersonalDetailsDTO;
    }
}
