package com.mirkwood.logistics.core.configurations;

import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.core.models.MirkwoodStaff;
import com.mirkwood.logistics.features.staff.repository.MirkwoodStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomStaffDetailsService {

    @Autowired
    private MirkwoodStaffRepository mirkwoodStaffRepository;

    public UserDetails loadStaffByUsername(String username)  {

        MirkwoodStaff staff = mirkwoodStaffRepository.findByStaffUsernameAndIsDeletedFalse(username)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Staff member not found with username: " + username));

        return new User(
                staff.getStaffUsername(),
                staff.getStaffPhoneNumber(), // Assuming staffPhoneNumber is used as a password here. Modify as per real password field.
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + staff.getStaffRole().name()))
        );
    }
}
