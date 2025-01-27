package com.mirkwood.logistics.features.personaldetails.service;

import com.mirkwood.logistics.features.personaldetails.dto.MirkwoodStaffPersonalDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MirkwoodStaffPersonalDetailsServiceImpl implements MirkwoodStaffPersonalDetailsService{

    @Override
    public MirkwoodStaffPersonalDetailsDTO getStaffDetailsById(Long id) {
        return null;
    }

    @Override
    public List<MirkwoodStaffPersonalDetailsDTO> getAllStaffDetails() {
        return List.of();
    }

    @Override
    public List<MirkwoodStaffPersonalDetailsDTO> searchByFirstName(String firstName) {
        return List.of();
    }

    @Override
    public List<MirkwoodStaffPersonalDetailsDTO> searchBySecondName(String firstName) {
        return List.of();
    }

    @Override
    public List<MirkwoodStaffPersonalDetailsDTO> filterByDepartment(String department) {
        return List.of();
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return false;
    }

    @Override
    public boolean isPhoneNumberRegistered(String phoneNumber) {
        return false;
    }

    @Override
    public MirkwoodStaffPersonalDetailsDTO addStaffDetails(MirkwoodStaffPersonalDetailsDTO mirkwoodStaffPersonalDetailsDTO) {
        return null;
    }

    @Override
    public MirkwoodStaffPersonalDetailsDTO updateStaffById(Long id) {
        return null;
    }

    @Override
    public MirkwoodStaffPersonalDetailsDTO updateStaffByEmail(String email) {
        return null;
    }

    @Override
    public MirkwoodStaffPersonalDetailsDTO updateStaffByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public MirkwoodStaffPersonalDetailsDTO updateStaffByEmployeeId(String employeeId) {
        return null;
    }

    @Override
    public void deleteStaffById(Long id) {

    }

    @Override
    public void deleteStaffByEmail(String email) {

    }

    @Override
    public void deleteStaffByPhoneNumber(String phoneNumber) {

    }

    @Override
    public void deleteStaffByEmployeeId(String employeeId) {

    }
}
