package com.mirkwood.logistics.features.personaldetails.service;

import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.core.models.MirkwoodStaffPersonalDetails;
import com.mirkwood.logistics.features.personaldetails.dto.MirkwoodStaffPersonalDetailsDTO;
import com.mirkwood.logistics.features.personaldetails.repository.MirkwoodStaffPersonalDetailsRepository;
import com.mirkwood.logistics.features.personaldetails.utility.MirkwoodStaffPersonalEntityToDTOMapper;
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

        if(Objects.isNull(mirkwoodStaffPersonalDetailsDTO)) {
            throw new IllegalStateException("Mapped DTO is null for employeeId: " + employeeId);
        }
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

        if(Objects.isNull(mirkwoodStaffPersonalDetailsDTO)) {
            throw new CustomMirkwoodLogisticsExceptions("Staff details cannot be null.");
        }

        String staffEmployeeId = mirkwoodStaffPersonalDetailsDTO.getEmployeeId();
        Optional<MirkwoodStaffPersonalDetails>optionalMirkwoodStaffPersonalDetails =
                mirkwoodStaffPersonalDetailsRepository.findByEmployeeIdAndIsDeletedFalse(staffEmployeeId);

        if(optionalMirkwoodStaffPersonalDetails.isPresent()) {
            throw new CustomMirkwoodLogisticsExceptions("Staff details already exist for employeeId: " + staffEmployeeId);
        }
        

        String staffEmailId = mirkwoodStaffPersonalDetailsDTO.getEmailId();
        String staffPhoneNumber = mirkwoodStaffPersonalDetailsDTO.getPhoneNumber();

        MirkwoodStaffPersonalDetails mirkwoodStaffPersonalDetails = new MirkwoodStaffPersonalDetails();

        mirkwoodStaffPersonalDetails.setFirstName(mirkwoodStaffPersonalDetailsDTO.getFirstName());
        mirkwoodStaffPersonalDetails.setLastName(mirkwoodStaffPersonalDetailsDTO.getLastName());
        mirkwoodStaffPersonalDetails.setDateOfBirth(mirkwoodStaffPersonalDetailsDTO.getDateOfBirth());
        mirkwoodStaffPersonalDetails.setGender(mirkwoodStaffPersonalDetailsDTO.getGender());
        mirkwoodStaffPersonalDetails.setNationality(mirkwoodStaffPersonalDetailsDTO.getNationality());
        mirkwoodStaffPersonalDetails.setEmail(mirkwoodStaffPersonalDetailsDTO.getEmailId());
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
