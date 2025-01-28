package com.mirkwood.logistics.features.personaldetails.controller;

import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.features.personaldetails.dto.MirkwoodStaffPersonalDetailsDTO;
import com.mirkwood.logistics.features.personaldetails.service.MirkwoodStaffPersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/mirkwood-logistics/staff-personal")
public class MirkwoodStaffPersonalDetailsController {

    @Autowired
    private MirkwoodStaffPersonalDetailsService mirkwoodStaffPersonalDetailsService;

    // create api
    @PostMapping("/signup")
    public ResponseEntity<MirkwoodStaffPersonalDetailsDTO> createNewStaff(@RequestBody MirkwoodStaffPersonalDetailsDTO mirkwoodStaffPersonalDetailsDTO) {
        MirkwoodStaffPersonalDetailsDTO creatingNewStaff = mirkwoodStaffPersonalDetailsService.addStaffDetails(mirkwoodStaffPersonalDetailsDTO);
        return new ResponseEntity<>(creatingNewStaff, HttpStatus.CREATED);
    }

    //update api
    @PutMapping("/update/employeeId")
    public ResponseEntity<MirkwoodStaffPersonalDetailsDTO> updateStaffByEmployeeId(@RequestBody MirkwoodStaffPersonalDetailsDTO mirkwoodStaffPersonalDetailsDTO) {
        if (Objects.isNull(mirkwoodStaffPersonalDetailsDTO.getEmployeeId())) {
            throw new CustomMirkwoodLogisticsExceptions("Employee ID is required to update staff details.");
        }
        MirkwoodStaffPersonalDetailsDTO updatedStaff = mirkwoodStaffPersonalDetailsService.updateStaffByEmployeeId(mirkwoodStaffPersonalDetailsDTO.getEmployeeId(), mirkwoodStaffPersonalDetailsDTO);
        return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
    }

    @PutMapping("/update/email")
    public ResponseEntity<MirkwoodStaffPersonalDetailsDTO> updateStaffByEmail(@RequestBody MirkwoodStaffPersonalDetailsDTO mirkwoodStaffPersonalDetailsDTO) {
        if (Objects.isNull(mirkwoodStaffPersonalDetailsDTO.getEmailId())) {
            throw new CustomMirkwoodLogisticsExceptions("Email ID is required to update staff details.");
        }
        MirkwoodStaffPersonalDetailsDTO updatedStaff = mirkwoodStaffPersonalDetailsService.updateStaffByEmail(mirkwoodStaffPersonalDetailsDTO.getEmailId(), mirkwoodStaffPersonalDetailsDTO);
        return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
    }

    @PutMapping("/update/phone")
    public ResponseEntity<MirkwoodStaffPersonalDetailsDTO> updateStaffByPhone(@RequestBody MirkwoodStaffPersonalDetailsDTO mirkwoodStaffPersonalDetailsDTO) {
        if (Objects.isNull(mirkwoodStaffPersonalDetailsDTO.getPhoneNumber())) {
            throw new CustomMirkwoodLogisticsExceptions("Phone number is required to update staff details.");
        }
        MirkwoodStaffPersonalDetailsDTO updatedStaff = mirkwoodStaffPersonalDetailsService.updateStaffByPhoneNumber(mirkwoodStaffPersonalDetailsDTO.getPhoneNumber(), mirkwoodStaffPersonalDetailsDTO);
        return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<MirkwoodStaffPersonalDetailsDTO> updateStaff(@RequestBody MirkwoodStaffPersonalDetailsDTO mirkwoodStaffPersonalDetailsDTO) {
        if (Objects.isNull(mirkwoodStaffPersonalDetailsDTO.getId())) {
            throw new CustomMirkwoodLogisticsExceptions("ID is required to update staff details. Please provide a valid ID.");
        }

        MirkwoodStaffPersonalDetailsDTO updatedStaff = mirkwoodStaffPersonalDetailsService.updateStaffById(mirkwoodStaffPersonalDetailsDTO.getId(), mirkwoodStaffPersonalDetailsDTO);
        return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
    }
}
