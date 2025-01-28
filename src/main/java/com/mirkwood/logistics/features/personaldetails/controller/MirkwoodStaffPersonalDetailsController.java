package com.mirkwood.logistics.features.personaldetails.controller;

import com.mirkwood.logistics.features.personaldetails.dto.MirkwoodStaffPersonalDetailsDTO;
import com.mirkwood.logistics.features.personaldetails.service.MirkwoodStaffPersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mirkwood-logistics/staff-personal")
public class MirkwoodStaffPersonalDetailsController {

    @Autowired
    private MirkwoodStaffPersonalDetailsService mirkwoodStaffPersonalDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<MirkwoodStaffPersonalDetailsDTO> createNewStaff(@RequestBody MirkwoodStaffPersonalDetailsDTO mirkwoodStaffPersonalDetailsDTO) {
        MirkwoodStaffPersonalDetailsDTO creatingNewStaff = mirkwoodStaffPersonalDetailsService.addStaffDetails(mirkwoodStaffPersonalDetailsDTO);
        return new ResponseEntity<>(creatingNewStaff, HttpStatus.CREATED);
    }
}
