package com.mirkwood.logistics.core.configurations;

import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.core.models.MirkwoodStaff;
import com.mirkwood.logistics.core.models.MirkwoodStaffLoginDetails;
import com.mirkwood.logistics.features.session.repository.MirkwoodStaffLoginDetailsRepository;
import com.mirkwood.logistics.features.staff.repository.MirkwoodStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomStaffDetailsService implements UserDetailsService {

    @Autowired
    private MirkwoodStaffRepository mirkwoodStaffRepository;

    @Autowired
    private MirkwoodStaffLoginDetailsRepository mirkwoodStaffLoginDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MirkwoodStaffLoginDetails loginDetails = mirkwoodStaffLoginDetailsRepository.findByStaffUsername(username)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Login details not found for username: " + username));

        MirkwoodStaff staff = mirkwoodStaffRepository.findByStaffUsernameAndIsDeletedFalse(username)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff member not found with username: " + username));

        System.out.println("User loaded: " + staff.getStaffUsername() + " with role " + staff.getStaffRole().name());

        return new MirkwoodStaffUserDetails(staff, loginDetails);
    }
}
