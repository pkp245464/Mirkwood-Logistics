package com.mirkwood.logistics.features.staff.controller;

import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.features.staff.dto.MirkwoodStaffDto;
import com.mirkwood.logistics.features.staff.service.MirkwoodStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mirkwood-logistics/staff")
public class MirkwoodStaffController {

    @Autowired
    private MirkwoodStaffService mirkwoodStaffService;

    @PostMapping("/signup")
    public ResponseEntity<MirkwoodStaffDto> createNewStaff(@RequestBody MirkwoodStaffDto mirkwoodStaffDto) {
        MirkwoodStaffDto creatingNewStaff = mirkwoodStaffService.createStaff(mirkwoodStaffDto);
        return new ResponseEntity<>(creatingNewStaff, HttpStatus.CREATED);
    }

    @PostMapping("/signup/mul")
    public ResponseEntity<List<MirkwoodStaffDto>> createMultipleStaff(@RequestBody List<MirkwoodStaffDto> mirkwoodStaffDtoList) {
        try {
            List<MirkwoodStaffDto> createdStaffList = mirkwoodStaffService.createMultipleStaff(mirkwoodStaffDtoList);
            return new ResponseEntity<>(createdStaffList, HttpStatus.CREATED);
        } catch (CustomMirkwoodLogisticsExceptions ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
