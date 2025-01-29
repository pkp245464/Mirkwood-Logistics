package com.mirkwood.logistics.features.staff.service;

import com.mirkwood.logistics.features.staff.dto.MirkwoodStaffDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MirkwoodStaffServiceImpl implements MirkwoodStaffService{

    // find
    @Override
    public MirkwoodStaffDto findByStaffId(Long staffId) {
        return null;
    }

    @Override
    public MirkwoodStaffDto findStaffByUsername(String username) {
        return null;
    }

    @Override
    public MirkwoodStaffDto findStaffByEmailId(String emailId) {
        return null;
    }

    @Override
    public List<MirkwoodStaffDto> findAllStaff() {
        return List.of();
    }

    @Override
    public List<MirkwoodStaffDto> findByStaffRole(String staffRole) {
        return List.of();
    }

    @Override
    public List<MirkwoodStaffDto> findByStaffOfficeCode(String officeCode) {
        return List.of();
    }

    @Override
    public List<MirkwoodStaffDto> findByStaffOfficeAddress(String officeAddress) {
        return List.of();
    }


    // create
    @Override
    public MirkwoodStaffService createStaff(MirkwoodStaffDto mirkwoodStaffDto) {
        return null;
    }

    @Override
    public List<MirkwoodStaffDto> createMultipleStaff(List<MirkwoodStaffDto> mirkwoodStaffDtoList) {
        return List.of();
    }


    //update
    @Override
    public MirkwoodStaffDto updateStaffByUsername(String username, MirkwoodStaffDto mirkwoodStaffDto) {
        return null;
    }

    @Override
    public MirkwoodStaffDto updateStaffByEmailId(String emailId, MirkwoodStaffDto mirkwoodStaffDto) {
        return null;
    }

    @Override
    public List<MirkwoodStaffDto> updateMultipleStaffByUsername(List<MirkwoodStaffDto> mirkwoodStaffDtoList) {
        return List.of();
    }


    // delete
    @Override
    public void deleteByStaffId(Long staffId) {

    }

    @Override
    public void deleteByStaffUsername(String username) {

    }

    @Override
    public void deleteByStaffEmailId(String emailId) {

    }

    @Override
    public void deleteMultipleStaffByUsername(List<MirkwoodStaffDto> mirkwoodStaffDtoList) {

    }


    // restore
    @Override
    public MirkwoodStaffDto restoreStaffAccountByStaffId(Long staffId, MirkwoodStaffDto mirkwoodStaffDto) {
        return null;
    }

    @Override
    public MirkwoodStaffDto restoreStaffAccountByUsername(String username, MirkwoodStaffDto mirkwoodStaffDto) {
        return null;
    }

    @Override
    public MirkwoodStaffDto restoreStaffAccountByEmailId(String emailId, MirkwoodStaffDto mirkwoodStaffDto) {
        return null;
    }
}
