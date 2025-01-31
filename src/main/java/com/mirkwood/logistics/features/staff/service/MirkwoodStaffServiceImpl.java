package com.mirkwood.logistics.features.staff.service;

import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.core.models.MirkwoodStaff;
import com.mirkwood.logistics.features.staff.dto.MirkwoodStaffDto;
import com.mirkwood.logistics.features.staff.repository.MirkwoodStaffRepository;
import com.mirkwood.logistics.features.staff.utility.MirkwoodStaffEntityToDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        if (mirkwoodStaffDtoList == null || mirkwoodStaffDtoList.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("Staff list cannot be empty.");
        }

        Set<String> providedUsernames = new HashSet<>();
        List<String> failedValidations = new ArrayList<>();

        for (MirkwoodStaffDto mirkwoodStaffDto : mirkwoodStaffDtoList) {
            String username = mirkwoodStaffDto.getStaffUsername();

            if (Objects.isNull(username) || username.trim().isEmpty()) {
                failedValidations.add("Staff username is empty for: " + mirkwoodStaffDto.getStaffFullName());
            }

            if (!providedUsernames.add(username)) {
                failedValidations.add("Duplicate username found for: " + mirkwoodStaffDto.getStaffFullName() + " (Username: " + username + ")");
            }
        }

        if (!failedValidations.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions(String.join("; ", failedValidations));
        }

        List<String> providedUsernamesList = new ArrayList<>(providedUsernames);
        List<String> existingUsernames = mirkwoodStaffRepository.findByStaffUsername(String.join(",", providedUsernamesList))
                .stream().map(MirkwoodStaff::getStaffUsername).collect(Collectors.toList());

        if (!existingUsernames.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("The following usernames already exist: " + String.join(", ", existingUsernames));
        }

        for (MirkwoodStaffDto mirkwoodStaffDto : mirkwoodStaffDtoList) {
            if (Objects.isNull(mirkwoodStaffDto.getStaffFullName()) || mirkwoodStaffDto.getStaffFullName().trim().isEmpty()) {
                failedValidations.add("Staff full name is empty for: " + mirkwoodStaffDto.getStaffUsername());
            }

            if (Objects.isNull(mirkwoodStaffDto.getStaffRole())) {
                failedValidations.add("Staff role is required for: " + mirkwoodStaffDto.getStaffUsername());
            }

            if (Objects.isNull(mirkwoodStaffDto.getStaffOfficeCode()) || mirkwoodStaffDto.getStaffOfficeCode().trim().isEmpty()) {
                failedValidations.add("Staff office code is empty for: " + mirkwoodStaffDto.getStaffUsername());
            }

            if (Objects.isNull(mirkwoodStaffDto.getStaffOfficeAddress()) || mirkwoodStaffDto.getStaffOfficeAddress().trim().isEmpty()) {
                failedValidations.add("Staff office address is empty for: " + mirkwoodStaffDto.getStaffUsername());
            }
        }

        if (!failedValidations.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions(String.join("; ", failedValidations));
        }

        List<MirkwoodStaffDto> savedStaffList = new ArrayList<>();
        for (MirkwoodStaffDto mirkwoodStaffDto : mirkwoodStaffDtoList) {
            MirkwoodStaff newStaff = MirkwoodStaffEntityToDTOMapper.mapToEntity(mirkwoodStaffDto);
            MirkwoodStaff savedStaff = mirkwoodStaffRepository.save(newStaff);
            savedStaffList.add(MirkwoodStaffEntityToDTOMapper.mapToDto(savedStaff));
        }

        return savedStaffList;
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
