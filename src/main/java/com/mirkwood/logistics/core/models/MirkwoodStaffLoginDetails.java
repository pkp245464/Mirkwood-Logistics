package com.mirkwood.logistics.core.models;

import com.mirkwood.logistics.core.enums.LoginMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "mirkwood_staff_login_details")
public class MirkwoodStaffLoginDetails {

    @Id
    @Column(name = "login_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loginId;

    @Column(name = "staff_username", unique = true, nullable = false)
    private String staffUsername;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "login_method", nullable = false)
    private LoginMethod loginMethod = LoginMethod.MANUAL;

    @Column(name = "is_logged_in", nullable = false)
    private Boolean isLoggedIn = false;

    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    @Column(name = "last_logout_time")
    private LocalDateTime lastLogoutTime;
}
