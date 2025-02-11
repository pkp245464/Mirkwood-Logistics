package com.mirkwood.logistics.core.configurations;


import com.mirkwood.logistics.core.models.MirkwoodStaff;
import com.mirkwood.logistics.core.models.MirkwoodStaffLoginDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MirkwoodStaffUserDetails implements UserDetails {
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    // Constructor to initialize from MirkwoodStaff object
    public MirkwoodStaffUserDetails(MirkwoodStaff mirkwoodStaff, MirkwoodStaffLoginDetails mirkwoodStaffLoginDetails) {
        this.username = mirkwoodStaffLoginDetails.getStaffUsername();
        this.password = mirkwoodStaffLoginDetails.getPassword();
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + mirkwoodStaff.getStaffRole().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Customize based on requirements
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Customize based on requirements
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Customize based on requirements
    }

    @Override
    public boolean isEnabled() {
        return true;  // Customize based on requirements
    }
}
