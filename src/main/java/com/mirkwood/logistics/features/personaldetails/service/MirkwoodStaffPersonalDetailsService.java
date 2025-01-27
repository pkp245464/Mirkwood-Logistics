package com.mirkwood.logistics.features.personaldetails.service;

import com.mirkwood.logistics.features.personaldetails.dto.MirkwoodStaffPersonalDetailsDTO;

import java.util.List;

public interface MirkwoodStaffPersonalDetailsService {

    //find operation
    MirkwoodStaffPersonalDetailsDTO getStaffDetailsById(Long id);
    List<MirkwoodStaffPersonalDetailsDTO> getAllStaffDetails();
    List<MirkwoodStaffPersonalDetailsDTO> searchByFirstName(String firstName);
    List<MirkwoodStaffPersonalDetailsDTO> searchBySecondName(String firstName);
    List<MirkwoodStaffPersonalDetailsDTO> filterByDepartment(String department);
    boolean isEmailRegistered(String email);
    boolean isPhoneNumberRegistered(String phoneNumber);

    //create operation
    MirkwoodStaffPersonalDetailsDTO addStaffDetails(MirkwoodStaffPersonalDetailsDTO mirkwoodStaffPersonalDetailsDTO);

    //update operation
    MirkwoodStaffPersonalDetailsDTO  updateStaffById(Long id);
    MirkwoodStaffPersonalDetailsDTO updateStaffByEmail(String email);
    MirkwoodStaffPersonalDetailsDTO updateStaffByPhoneNumber(String phoneNumber);
    MirkwoodStaffPersonalDetailsDTO updateStaffByEmployeeId(String employeeId);

    //delete operation
    void  deleteStaffById(Long id);
    void deleteStaffByEmail(String email);
    void deleteStaffByPhoneNumber(String phoneNumber);
    void deleteStaffByEmployeeId(String employeeId);
}
