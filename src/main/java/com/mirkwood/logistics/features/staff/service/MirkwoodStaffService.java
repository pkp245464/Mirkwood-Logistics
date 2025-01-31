package com.mirkwood.logistics.features.staff.service;

import com.mirkwood.logistics.features.staff.dto.MirkwoodStaffDto;

import java.util.List;

public interface MirkwoodStaffService {

    //find
    MirkwoodStaffDto findByStaffId(Long staffId); //only for db manager
    MirkwoodStaffDto findStaffByUsername(String username);
    MirkwoodStaffDto findStaffByEmailId(String emailId);
    List<MirkwoodStaffDto> findAllStaff();
    List<MirkwoodStaffDto> findByStaffRole(String staffRole);
    List<MirkwoodStaffDto> findByStaffOfficeCode(String officeCode);
    List<MirkwoodStaffDto> findByStaffOfficeAddress(String officeAddress);


    //create
    MirkwoodStaffDto createStaff(MirkwoodStaffDto mirkwoodStaffDto);
    List<MirkwoodStaffDto> createMultipleStaff(List<MirkwoodStaffDto> mirkwoodStaffDtoList);

    //update (you can't update is_delete attributes here)
    MirkwoodStaffDto updateStaffByUsername(String username,MirkwoodStaffDto mirkwoodStaffDto);
    MirkwoodStaffDto updateStaffByEmailId(String emailId,MirkwoodStaffDto mirkwoodStaffDto);
    List<MirkwoodStaffDto> updateMultipleStaffByUsername(List<MirkwoodStaffDto> mirkwoodStaffDtoList);

    //delete
    void deleteByStaffId(Long staffId); //only for db manager
    void deleteByStaffUsername(String username);
    void deleteByStaffEmailId(String emailId);
    void deleteMultipleStaffByUsername(List<String> usernames);

    //restore
    MirkwoodStaffDto restoreStaffAccountByStaffId(Long staffId); //only for db manager
    MirkwoodStaffDto restoreStaffAccountByUsername(String username);
    MirkwoodStaffDto restoreStaffAccountByEmailId(String emailId);
}
