package com.mirkwood.logistics.features.staff.service;

import com.mirkwood.logistics.core.enums.StaffRole;
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
        MirkwoodStaff staff = mirkwoodStaffRepository.findByStaffIdAndIsDeletedFalse(staffId)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff not found with ID: " + staffId));
        return MirkwoodStaffEntityToDTOMapper.mapToDto(staff);
    }

    @Override
    public MirkwoodStaffDto findStaffByUsername(String username) {
        MirkwoodStaff staff = mirkwoodStaffRepository.findByStaffUsernameAndIsDeletedFalse(username)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff not found with username: " + username));
        return MirkwoodStaffEntityToDTOMapper.mapToDto(staff);
    }

    @Override
    public MirkwoodStaffDto findStaffByEmailId(String emailId) {
        MirkwoodStaff staff = mirkwoodStaffRepository.findByStaffEmailIdAndIsDeletedFalse(emailId)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff not found with email ID: " + emailId));
        return MirkwoodStaffEntityToDTOMapper.mapToDto(staff);
    }


    @Override
    public List<MirkwoodStaffDto> findAllStaff() {
        List<MirkwoodStaff> staffList = mirkwoodStaffRepository.findByIsDeletedFalse();
        if (staffList.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("No staff found");
        }
        return staffList.stream()
                .map(MirkwoodStaffEntityToDTOMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MirkwoodStaffDto> findByStaffRole(String staffRole) {
        try {
            StaffRole roleEnum = StaffRole.valueOf(staffRole.toUpperCase());
            List<MirkwoodStaff> staffList = mirkwoodStaffRepository.findByStaffRoleAndIsDeletedFalse(roleEnum);
            if (staffList.isEmpty()) {
                throw new CustomMirkwoodLogisticsExceptions("No staff found for the given role.");
            }
            return staffList.stream()
                    .map(MirkwoodStaffEntityToDTOMapper::mapToDto)
                    .collect(Collectors.toList());
        }
        catch (IllegalArgumentException e) {
            throw new CustomMirkwoodLogisticsExceptions("Invalid staff role provided: " + staffRole);
        }
    }

    @Override
    public List<MirkwoodStaffDto> findByStaffOfficeCode(String officeCode) {
        List<MirkwoodStaff> staffList = mirkwoodStaffRepository.findByStaffOfficeCodeAndIsDeletedFalse(officeCode);
        if (staffList.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("No staff found for the given office code.");
        }
        return staffList.stream()
                .map(MirkwoodStaffEntityToDTOMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MirkwoodStaffDto> findByStaffOfficeAddress(String officeAddress) {
        List<MirkwoodStaff> staffList = mirkwoodStaffRepository.findByStaffOfficeAddressContainingIgnoreCaseAndIsDeletedFalse(officeAddress);
        if (staffList.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("No staff found for the given office address.");
        }
        return staffList.stream()
                .map(MirkwoodStaffEntityToDTOMapper::mapToDto)
                .collect(Collectors.toList());
    }


    // create
    @Override
    public MirkwoodStaffDto createStaff(MirkwoodStaffDto mirkwoodStaffDto) {
        Optional<MirkwoodStaff> existingStaff = mirkwoodStaffRepository.findByStaffUsernameAndIsDeletedFalse(mirkwoodStaffDto.getStaffUsername());
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
        List<String> existingUsernames = mirkwoodStaffRepository.findByStaffUsernameAndIsDeletedFalse(String.join(",", providedUsernamesList))
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
    @Override //for db admin
    public void deleteByStaffId(Long staffId) {
        MirkwoodStaff staff = mirkwoodStaffRepository.findById(staffId)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff not found with id: " + staffId));

        staff.setIsDeleted(true);
        mirkwoodStaffRepository.save(staff);
    }

    @Override
    public void deleteByStaffUsername(String username) {
        MirkwoodStaff staff = mirkwoodStaffRepository.findByStaffUsernameAndIsDeletedFalse(username)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff not found with username: " + username));

        staff.setIsDeleted(true);
        mirkwoodStaffRepository.save(staff);
    }

    @Override
    public void deleteByStaffEmailId(String emailId) {
        MirkwoodStaff staff = mirkwoodStaffRepository.findByStaffEmailIdAndIsDeletedFalse(emailId)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff not found with email id: " + emailId));

        staff.setIsDeleted(true);
        mirkwoodStaffRepository.save(staff);
    }

    @Override
    public void deleteMultipleStaffByUsername(List<String> usernames) {
        List<MirkwoodStaff> staffToDelete = new ArrayList<>();
        List<String> missingUsernames = new ArrayList<>();

        for (String username : usernames) {
            MirkwoodStaff staff = mirkwoodStaffRepository.findByStaffUsernameAndIsDeletedFalse(username)
                    .orElse(null);

            if (staff != null) {
                if (staff.getIsDeleted() == null || !staff.getIsDeleted()) {
                    staff.setIsDeleted(true);
                    staffToDelete.add(staff);
                }
            } else {
                missingUsernames.add(username);
            }
        }

        if (!missingUsernames.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("The following usernames do not exist: " + String.join(", ", missingUsernames));
        }

        if (!staffToDelete.isEmpty()) {
            mirkwoodStaffRepository.saveAll(staffToDelete);
        }
    }


    // restore
    @Override
    public MirkwoodStaffDto restoreStaffAccountByStaffId(Long staffId) {
        Optional<MirkwoodStaff> staffOptional = mirkwoodStaffRepository.findById(staffId);

        if (staffOptional.isPresent()) {
            MirkwoodStaff staff = staffOptional.get();
            staff.setIsDeleted(false);
            MirkwoodStaff updatedStaff = mirkwoodStaffRepository.save(staff);
            return MirkwoodStaffEntityToDTOMapper.mapToDto(updatedStaff);
        } else {
            throw new CustomMirkwoodLogisticsExceptions("Staff not found with id: " + staffId);
        }
    }

    @Override
    public MirkwoodStaffDto restoreStaffAccountByUsername(String username) {
        MirkwoodStaff staff = mirkwoodStaffRepository.findByStaffUsernameAndIsDeletedFalse(username)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff not found with username: " + username));

        staff.setIsDeleted(false);
        MirkwoodStaff updatedStaff = mirkwoodStaffRepository.save(staff);
        return MirkwoodStaffEntityToDTOMapper.mapToDto(updatedStaff);
    }

    @Override
    public MirkwoodStaffDto restoreStaffAccountByEmailId(String emailId) {
        MirkwoodStaff staff = mirkwoodStaffRepository.findByStaffEmailIdAndIsDeletedFalse(emailId)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff not found with email: " + emailId));

        staff.setIsDeleted(false);
        MirkwoodStaff updatedStaff = mirkwoodStaffRepository.save(staff);
        return MirkwoodStaffEntityToDTOMapper.mapToDto(updatedStaff);
    }

}
