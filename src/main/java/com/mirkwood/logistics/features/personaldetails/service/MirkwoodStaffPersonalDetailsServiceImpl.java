package com.mirkwood.logistics.features.personaldetails.service;

import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.core.models.MirkwoodStaffPersonalDetails;
import com.mirkwood.logistics.features.personaldetails.dto.MirkwoodStaffPersonalDetailsDTO;
import com.mirkwood.logistics.features.personaldetails.repository.MirkwoodStaffPersonalDetailsRepository;
import com.mirkwood.logistics.features.personaldetails.utility.MirkwoodStaffPersonalEntityToDTOMapper;
import com.mirkwood.logistics.features.personaldetails.utility.MirkwoodStaffUpdateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MirkwoodStaffPersonalDetailsServiceImpl implements MirkwoodStaffPersonalDetailsService{

    @Autowired
    private MirkwoodStaffPersonalDetailsRepository mirkwoodStaffPersonalDetailsRepository;

    @Override
    public Optional<MirkwoodStaffPersonalDetailsDTO> getStaffDetailsByEmployeeId(String employeeId) {
        Optional<MirkwoodStaffPersonalDetails>optionalMirkwoodStaffPersonalDetails =
                mirkwoodStaffPersonalDetailsRepository.findByEmployeeIdAndIsDeletedFalse(employeeId);

        if(optionalMirkwoodStaffPersonalDetails.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("Staff details not found for employeeId: " + employeeId);
        }

        MirkwoodStaffPersonalDetailsDTO mirkwoodStaffPersonalDetailsDTO =
                MirkwoodStaffPersonalEntityToDTOMapper.mapToDto(optionalMirkwoodStaffPersonalDetails.get());

        return Optional.of(mirkwoodStaffPersonalDetailsDTO);
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

    //TODO implement using bloom filter
    @Override
    public MirkwoodStaffPersonalDetailsDTO addStaffDetails(MirkwoodStaffPersonalDetailsDTO mirkwoodStaffPersonalDetailsDTO) {

        if (Objects.isNull(mirkwoodStaffPersonalDetailsDTO)) {
            throw new CustomMirkwoodLogisticsExceptions("Staff details cannot be null.");
        }

        // check if employee is unique
        String staffEmployeeId = mirkwoodStaffPersonalDetailsDTO.getEmployeeId();

        if (Objects.isNull(staffEmployeeId) || staffEmployeeId.trim().isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("Employee ID cannot be null or empty.");
        }

        Optional<MirkwoodStaffPersonalDetails>optionalMirkwoodStaffPersonalDetails =
                mirkwoodStaffPersonalDetailsRepository.findByEmployeeIdAndIsDeletedFalse(staffEmployeeId);

        if (optionalMirkwoodStaffPersonalDetails.isPresent()) {
            throw new CustomMirkwoodLogisticsExceptions("Staff details already exist for employeeId: " + staffEmployeeId);
        }

        // check if emailId is unique
        String staffEmailId = mirkwoodStaffPersonalDetailsDTO.getEmailId();

        if (Objects.isNull(staffEmailId) || staffEmailId.trim().isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("Email ID cannot be null or empty.");
        }

        Optional<MirkwoodStaffPersonalDetails> optionalMirkwoodStaffPersonalDetailsByEmail =
                mirkwoodStaffPersonalDetailsRepository.findByEmailIdAndIsDeletedFalse(staffEmailId);

        if (optionalMirkwoodStaffPersonalDetailsByEmail.isPresent()) {
            throw new CustomMirkwoodLogisticsExceptions("Staff details already exist for emailId: " + staffEmailId);
        }

        // Check if phoneNumber is unique
        String staffPhoneNumber = mirkwoodStaffPersonalDetailsDTO.getPhoneNumber();

        if (Objects.isNull(staffPhoneNumber) || staffPhoneNumber.trim().isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("Phone number cannot be null or empty.");
        }

        Optional<MirkwoodStaffPersonalDetails> optionalMirkwoodStaffPersonalDetailsByPhone =
                mirkwoodStaffPersonalDetailsRepository.findByPhoneNumberAndIsDeletedFalse(staffPhoneNumber);

        if (optionalMirkwoodStaffPersonalDetailsByPhone.isPresent()) {
            throw new CustomMirkwoodLogisticsExceptions("Staff details already exist for phoneNumber: " + staffPhoneNumber);
        }

        // check name is present or not
        String staffFirstName = mirkwoodStaffPersonalDetailsDTO.getFirstName();
        if (Objects.isNull(staffFirstName) || staffFirstName.trim().isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("First name can't be null or empty.");
        }

        String staffPermanentAddress = mirkwoodStaffPersonalDetailsDTO.getPermanentAddress();
        if (Objects.isNull(staffPermanentAddress) || staffPermanentAddress.trim().isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("Permanent address can't be null or empty.");
        }

        MirkwoodStaffPersonalDetails mirkwoodStaffPersonalDetails = new MirkwoodStaffPersonalDetails();

        mirkwoodStaffPersonalDetails.setFirstName(mirkwoodStaffPersonalDetailsDTO.getFirstName());
        mirkwoodStaffPersonalDetails.setLastName(mirkwoodStaffPersonalDetailsDTO.getLastName());
        mirkwoodStaffPersonalDetails.setDateOfBirth(mirkwoodStaffPersonalDetailsDTO.getDateOfBirth());
        mirkwoodStaffPersonalDetails.setGender(mirkwoodStaffPersonalDetailsDTO.getGender());
        mirkwoodStaffPersonalDetails.setNationality(mirkwoodStaffPersonalDetailsDTO.getNationality());
        mirkwoodStaffPersonalDetails.setEmailId(mirkwoodStaffPersonalDetailsDTO.getEmailId());
        mirkwoodStaffPersonalDetails.setPhoneNumber(mirkwoodStaffPersonalDetailsDTO.getPhoneNumber());
        mirkwoodStaffPersonalDetails.setCurrentAddress(mirkwoodStaffPersonalDetailsDTO.getCurrentAddress());
        mirkwoodStaffPersonalDetails.setPermanentAddress(mirkwoodStaffPersonalDetailsDTO.getPermanentAddress());
        mirkwoodStaffPersonalDetails.setEmployeeId(mirkwoodStaffPersonalDetailsDTO.getEmployeeId());
        mirkwoodStaffPersonalDetails.setDateOfJoining(mirkwoodStaffPersonalDetailsDTO.getDateOfJoining());
        mirkwoodStaffPersonalDetails.setDesignation(mirkwoodStaffPersonalDetailsDTO.getDesignation());
        mirkwoodStaffPersonalDetails.setDepartment(mirkwoodStaffPersonalDetailsDTO.getDepartment());
        mirkwoodStaffPersonalDetails.setIsDeleted(false);

        MirkwoodStaffPersonalDetails mirkwoodStaffPersonalDetailsSaveEntity = mirkwoodStaffPersonalDetailsRepository.save(mirkwoodStaffPersonalDetails);

        return MirkwoodStaffPersonalEntityToDTOMapper.mapToDto(mirkwoodStaffPersonalDetailsSaveEntity);
    }


    //update all method

    @Override
    public MirkwoodStaffPersonalDetailsDTO updateStaffById(Long id, MirkwoodStaffPersonalDetailsDTO mirkwoodStaffPersonalDetailsDTO) {
        if (id == null) {
            throw new CustomMirkwoodLogisticsExceptions("ID cannot be null.");
        }

        MirkwoodStaffPersonalDetails staff = mirkwoodStaffPersonalDetailsRepository.findById(id)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff not found for ID: " + id));

        MirkwoodStaffUpdateUtility.updateEntityFromDto(staff, mirkwoodStaffPersonalDetailsDTO);

        MirkwoodStaffPersonalDetails updatedStaff = mirkwoodStaffPersonalDetailsRepository.save(staff);
        return MirkwoodStaffPersonalEntityToDTOMapper.mapToDto(updatedStaff);
    }

    @Override
    public MirkwoodStaffPersonalDetailsDTO updateStaffByEmail(String email, MirkwoodStaffPersonalDetailsDTO mirkwoodStaffPersonalDetailsDTO) {
        if (email == null || email.trim().isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("Email cannot be null or empty.");
        }

        MirkwoodStaffPersonalDetails staff = mirkwoodStaffPersonalDetailsRepository.findByEmailIdAndIsDeletedFalse(email)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff not found for email: " + email));

        MirkwoodStaffUpdateUtility.updateEntityFromDto(staff, mirkwoodStaffPersonalDetailsDTO);

        MirkwoodStaffPersonalDetails updatedStaff = mirkwoodStaffPersonalDetailsRepository.save(staff);
        return MirkwoodStaffPersonalEntityToDTOMapper.mapToDto(updatedStaff);
    }

    @Override
    public MirkwoodStaffPersonalDetailsDTO updateStaffByPhoneNumber(String phoneNumber, MirkwoodStaffPersonalDetailsDTO mirkwoodStaffPersonalDetailsDTO) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("Phone number cannot be null or empty.");
        }

        MirkwoodStaffPersonalDetails staff = mirkwoodStaffPersonalDetailsRepository.findByPhoneNumberAndIsDeletedFalse(phoneNumber)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff not found for phone number: " + phoneNumber));

        MirkwoodStaffUpdateUtility.updateEntityFromDto(staff, mirkwoodStaffPersonalDetailsDTO);

        MirkwoodStaffPersonalDetails updatedStaff = mirkwoodStaffPersonalDetailsRepository.save(staff);
        return MirkwoodStaffPersonalEntityToDTOMapper.mapToDto(updatedStaff);
    }

    @Override
    public MirkwoodStaffPersonalDetailsDTO updateStaffByEmployeeId(String employeeId, MirkwoodStaffPersonalDetailsDTO mirkwoodStaffPersonalDetailsDTO) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("Employee ID cannot be null or empty.");
        }

        MirkwoodStaffPersonalDetails staff = mirkwoodStaffPersonalDetailsRepository.findByEmployeeIdAndIsDeletedFalse(employeeId)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff not found for employee ID: " + employeeId));

        MirkwoodStaffUpdateUtility.updateEntityFromDto(staff, mirkwoodStaffPersonalDetailsDTO);

        MirkwoodStaffPersonalDetails updatedStaff = mirkwoodStaffPersonalDetailsRepository.save(staff);
        return MirkwoodStaffPersonalEntityToDTOMapper.mapToDto(updatedStaff);
    }


    // delete all method

    @Override
    public void deleteStaffById(Long id) {
        MirkwoodStaffPersonalDetails mirkwoodStaffPersonalDetails = mirkwoodStaffPersonalDetailsRepository.findById(id)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff with ID " + id + " not found."));
        mirkwoodStaffPersonalDetails.setIsDeleted(true);
        mirkwoodStaffPersonalDetailsRepository.save(mirkwoodStaffPersonalDetails);
    }

    @Override
    public void deleteStaffByEmail(String email) {
        MirkwoodStaffPersonalDetails mirkwoodStaffPersonalDetails = mirkwoodStaffPersonalDetailsRepository.findByEmailIdAndIsDeletedFalse(email)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff with email " + email + " not found."));
        mirkwoodStaffPersonalDetails.setIsDeleted(true);
        mirkwoodStaffPersonalDetailsRepository.save(mirkwoodStaffPersonalDetails);
    }

    @Override
    public void deleteStaffByPhoneNumber(String phoneNumber) {
        MirkwoodStaffPersonalDetails mirkwoodStaffPersonalDetails = mirkwoodStaffPersonalDetailsRepository.findByPhoneNumberAndIsDeletedFalse(phoneNumber)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff with phone number " + phoneNumber + " not found."));
        mirkwoodStaffPersonalDetails.setIsDeleted(true);
        mirkwoodStaffPersonalDetailsRepository.save(mirkwoodStaffPersonalDetails);
    }

    @Override
    public void deleteStaffByEmployeeId(String employeeId) {
        MirkwoodStaffPersonalDetails mirkwoodStaffPersonalDetails = mirkwoodStaffPersonalDetailsRepository.findByEmployeeIdAndIsDeletedFalse(employeeId)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff with employee ID " + employeeId + " not found."));
        mirkwoodStaffPersonalDetails.setIsDeleted(true);
        mirkwoodStaffPersonalDetailsRepository.save(mirkwoodStaffPersonalDetails);
    }

}
