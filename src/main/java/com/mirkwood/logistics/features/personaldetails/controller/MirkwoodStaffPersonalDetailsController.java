package com.mirkwood.logistics.features.personaldetails.controller;

import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.features.personaldetails.dto.MirkwoodStaffPersonalDetailsDTO;
import com.mirkwood.logistics.features.personaldetails.service.MirkwoodStaffPersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    @PutMapping("/update/employeeId") //only used for db manager
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

    //delete staff personal details

    @DeleteMapping("/delete/{id}") //only used for db manager
    public ResponseEntity<String> deleteStaffById(@PathVariable Long id) {
        if (id == null) {
            throw new CustomMirkwoodLogisticsExceptions("ID is required for deletion.");
        }
        mirkwoodStaffPersonalDetailsService.deleteStaffById(id);
        return new ResponseEntity<>("Staff with ID " + id + " marked as deleted.", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStaff(@RequestParam(required = false) String email,
                                              @RequestParam(required = false) String phoneNumber,
                                              @RequestParam(required = false) String employeeId) {
        if (Objects.nonNull(email)) {
            mirkwoodStaffPersonalDetailsService.deleteStaffByEmail(email);
            return new ResponseEntity<>("Staff with Email " + email + " marked as deleted.", HttpStatus.OK);
        }
        else if (Objects.nonNull(phoneNumber)) {
            mirkwoodStaffPersonalDetailsService.deleteStaffByPhoneNumber(phoneNumber);
            return new ResponseEntity<>("Staff with Phone Number " + phoneNumber + " marked as deleted.", HttpStatus.OK);
        }
        else if (Objects.nonNull(employeeId)) {
            mirkwoodStaffPersonalDetailsService.deleteStaffByEmployeeId(employeeId);
            return new ResponseEntity<>("Staff with Employee ID " + employeeId + " marked as deleted.", HttpStatus.OK);
        }
        else {
            throw new CustomMirkwoodLogisticsExceptions("Deletion failed! Please provide a valid identifier (Email, Phone Number, or Employee ID).");
        }
    }

    // all find api
    @GetMapping("/details/{employeeId}")
    public ResponseEntity<MirkwoodStaffPersonalDetailsDTO> getStaffDetailsByEmployeeId(@PathVariable String employeeId) {
        return mirkwoodStaffPersonalDetailsService.getStaffDetailsByEmployeeId(employeeId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MirkwoodStaffPersonalDetailsDTO>> getAllStaffDetails() {
        return ResponseEntity.ok(mirkwoodStaffPersonalDetailsService.getAllStaffDetails());
    }

    @GetMapping("/search/firstName")
    public ResponseEntity<List<MirkwoodStaffPersonalDetailsDTO>> searchByFirstName(@RequestParam String firstName) {
        return ResponseEntity.ok(mirkwoodStaffPersonalDetailsService.searchByFirstName(firstName));
    }

    @GetMapping("/search/lastName")
    public ResponseEntity<List<MirkwoodStaffPersonalDetailsDTO>> searchByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(mirkwoodStaffPersonalDetailsService.searchByLastName(lastName));
    }

    @GetMapping("/filter/department")
    public ResponseEntity<List<MirkwoodStaffPersonalDetailsDTO>> filterByDepartment(@RequestParam String department) {
        return ResponseEntity.ok(mirkwoodStaffPersonalDetailsService.filterByDepartment(department));
    }
}
