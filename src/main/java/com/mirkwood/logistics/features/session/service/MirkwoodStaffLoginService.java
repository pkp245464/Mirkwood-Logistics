package com.mirkwood.logistics.features.session.service;

import com.mirkwood.logistics.features.session.dto.MirkwoodStaffRegistrationDTO;

public interface MirkwoodStaffLoginService {
    String manualLogin(String username, String password);
    void manualLogout(String username);
    String registerStaff(MirkwoodStaffRegistrationDTO mirkwoodStaffRegistrationDTO);
}
