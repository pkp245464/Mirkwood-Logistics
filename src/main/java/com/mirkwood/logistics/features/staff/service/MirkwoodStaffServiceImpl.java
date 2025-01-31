package com.mirkwood.logistics.features.staff.service;

import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.core.models.MirkwoodStaff;
import com.mirkwood.logistics.features.staff.dto.MirkwoodStaffDto;
import com.mirkwood.logistics.features.staff.repository.MirkwoodStaffRepository;
import com.mirkwood.logistics.features.staff.utility.MirkwoodStaffEntityToDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MirkwoodStaffServiceImpl implements MirkwoodStaffService{

    @Autowired
    private MirkwoodStaffRepository mirkwoodStaffRepository;

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
    public MirkwoodStaffDto createStaff(MirkwoodStaffDto mirkwoodStaffDto) {
        Optional<MirkwoodStaff> existingStaff = mirkwoodStaffRepository.findByStaffUsername(mirkwoodStaffDto.getStaffUsername());
        if(existingStaff.isPresent()) {
            throw new CustomMirkwoodLogisticsExceptions("Staff username already exists.");
        }
        if (Objects.isNull(mirkwoodStaffDto.getStaffFullName()) || mirkwoodStaffDto.getStaffFullName().trim().isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("Staff full name cannot be empty.");
        }
        if (Objects.isNull(mirkwoodStaffDto.getStaffRole())) {
            throw new CustomMirkwoodLogisticsExceptions("Staff role is required.");
        }
        if (Objects.isNull(mirkwoodStaffDto.getStaffOfficeCode()) || mirkwoodStaffDto.getStaffOfficeCode().trim().isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("Staff office code cannot be empty.");
        }
        if (Objects.isNull(mirkwoodStaffDto.getStaffOfficeAddress()) || mirkwoodStaffDto.getStaffOfficeAddress().trim().isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("Staff office address cannot be empty.");
        }

        MirkwoodStaff newStaff = MirkwoodStaffEntityToDTOMapper.mapToEntity(mirkwoodStaffDto);
        MirkwoodStaff savedStaff = mirkwoodStaffRepository.save(newStaff);
        return MirkwoodStaffEntityToDTOMapper.mapToDto(savedStaff);
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
