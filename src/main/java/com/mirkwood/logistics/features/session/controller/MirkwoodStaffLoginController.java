package com.mirkwood.logistics.features.session.controller;

import com.mirkwood.logistics.features.session.dto.MirkwoodStaffLoginDTO;
import com.mirkwood.logistics.features.session.dto.MirkwoodStaffRegistrationDTO;
import com.mirkwood.logistics.features.session.service.MirkwoodStaffLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mirkwood-logistics/session")
public class MirkwoodStaffLoginController {

    @Autowired
    private MirkwoodStaffLoginService mirkwoodStaffLoginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MirkwoodStaffLoginDTO loginDTO) {
        boolean isLoggedIn = mirkwoodStaffLoginService.manualLogin(loginDTO.getUsername(), loginDTO.getPassword());
        if (isLoggedIn) {
            return ResponseEntity.ok("Login successful");
        }
        else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping("/logout/{username}")
    public ResponseEntity<String> logout(@PathVariable String username) {
        mirkwoodStaffLoginService.manualLogout(username);
        return ResponseEntity.ok("Logout successful");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody MirkwoodStaffRegistrationDTO registrationDTO) {
        String registrationResult = mirkwoodStaffLoginService.registerStaff(registrationDTO);
        if (registrationResult.equals("Registration successful.")) {
            return ResponseEntity.ok(registrationResult);
        }
        else {
            return ResponseEntity.status(400).body(registrationResult);
        }
    }
}
