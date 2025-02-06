package com.mirkwood.logistics.features.session.service;

import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.core.models.MirkwoodStaff;
import com.mirkwood.logistics.core.models.MirkwoodStaffLoginDetails;
import com.mirkwood.logistics.features.session.dto.MirkwoodStaffRegistrationDTO;
import com.mirkwood.logistics.features.session.repository.MirkwoodStaffLoginDetailsRepository;
import com.mirkwood.logistics.features.staff.repository.MirkwoodStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MirkwoodStaffLoginServiceImpl implements MirkwoodStaffLoginService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MirkwoodStaffRepository mirkwoodStaffRepository;

    @Autowired
    private MirkwoodStaffLoginDetailsRepository mirkwoodStaffLoginDetailsRepository;

    @Override
    public boolean manualLogin(String username, String password) {
        MirkwoodStaffLoginDetails loginDetails = mirkwoodStaffLoginDetailsRepository.findByStaffUsername(username)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("User not found or username is incorrect"));

        if (!passwordEncoder.matches(password, loginDetails.getPassword())) {
            throw new CustomMirkwoodLogisticsExceptions("Invalid password for the given username");
        }

        loginDetails.setIsLoggedIn(true);
        loginDetails.setLastLoginTime(LocalDateTime.now());

        mirkwoodStaffLoginDetailsRepository.save(loginDetails);
        return true;
    }

    @Override
    public void manualLogout(String username) {
        Optional<MirkwoodStaff> existingStaff = mirkwoodStaffRepository.findByStaffUsernameAndIsDeletedFalse(username);
        if (existingStaff.isPresent()) {
            throw new CustomMirkwoodLogisticsExceptions ("Username already exists or is deleted.");
        }

        MirkwoodStaffLoginDetails loginDetails = mirkwoodStaffLoginDetailsRepository.findByStaffUsername(username)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("User not found or username is incorrect"));

        if (!loginDetails.getIsLoggedIn()) {
            throw new CustomMirkwoodLogisticsExceptions("User is not logged in");
        }

        loginDetails.setIsLoggedIn(false);
        loginDetails.setLastLogoutTime(LocalDateTime.now());

        mirkwoodStaffLoginDetailsRepository.save(loginDetails);
    }

    @Override
    public String registerStaff(MirkwoodStaffRegistrationDTO registrationDTO) {
        Optional<MirkwoodStaff> existingStaff = mirkwoodStaffRepository.findByStaffUsernameAndIsDeletedFalse(registrationDTO.getUsername());
        if (existingStaff.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions ("The username does not exist. Please ensure the staff is created before registering the login with a password.");
        }

        MirkwoodStaffLoginDetails loginDetails = new MirkwoodStaffLoginDetails();
        loginDetails.setStaffUsername(registrationDTO.getUsername());
        loginDetails.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        mirkwoodStaffLoginDetailsRepository.save(loginDetails);
        return "Registration successful.";
    }
}
