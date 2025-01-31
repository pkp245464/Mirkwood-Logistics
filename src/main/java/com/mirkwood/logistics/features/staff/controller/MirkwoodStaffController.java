package com.mirkwood.logistics.features.staff.controller;

import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.features.staff.dto.MirkwoodStaffDto;
import com.mirkwood.logistics.features.staff.service.MirkwoodStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/mirkwood-logistics/staff")
public class MirkwoodStaffController {

    @Autowired
    private MirkwoodStaffService mirkwoodStaffService;

    // create
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

    // delete
    @DeleteMapping("/delete/{staffId}")
    public ResponseEntity<String> deleteByStaffId(@PathVariable Long staffId) {
        try {
            mirkwoodStaffService.deleteByStaffId(staffId);
            return ResponseEntity.ok("Staff with ID " + staffId + " has been deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Staff not found with ID " + staffId);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteByUsernameOrEmail(@RequestParam(required = false) String username,
                                                          @RequestParam(required = false) String emailId) {
        if (username != null) {
            try {
                mirkwoodStaffService.deleteByStaffUsername(username);
                return ResponseEntity.ok("Staff with username " + username + " has been deleted.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Staff not found with username " + username);
            }
        } else if (emailId != null) {
            try {
                mirkwoodStaffService.deleteByStaffEmailId(emailId);
                return ResponseEntity.ok("Staff with email ID " + emailId + " has been deleted.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Staff not found with email ID " + emailId);
            }
        }
        else {
            return ResponseEntity.badRequest().body("Please provide either username or emailId.");
        }
    }

    @DeleteMapping("/delete-multiple")
    public ResponseEntity<String> deleteMultipleStaffByUsername(@RequestBody List<String> usernames) {
        try {
            mirkwoodStaffService.deleteMultipleStaffByUsername(usernames);
            return ResponseEntity.ok("Staff members with the provided usernames have been deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error occurred while deleting staff members.");
        }
    }

    @PutMapping("/restore")
    public ResponseEntity<MirkwoodStaffDto> restoreStaffAccount(
            @RequestParam(value = "staffId", required = false) Long staffId,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "emailId", required = false) String emailId) {

        if (Objects.isNull(staffId) && Objects.isNull(username) && Objects.isNull(emailId)) {
            throw new CustomMirkwoodLogisticsExceptions("At least one parameter (staffId, username, or emailId) is required.");
        }

        try {
            MirkwoodStaffDto restoredStaff = null;
            if (Objects.nonNull(staffId)) {
                restoredStaff = mirkwoodStaffService.restoreStaffAccountByStaffId(staffId);
            }
            else if (Objects.nonNull(username)) {
                restoredStaff = mirkwoodStaffService.restoreStaffAccountByUsername(username);
            }
            else {
                restoredStaff = mirkwoodStaffService.restoreStaffAccountByEmailId(emailId);
            }

            return new ResponseEntity<>(restoredStaff, HttpStatus.OK);

        }
        catch (CustomMirkwoodLogisticsExceptions e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
