package com.mirkwood.logistics.core.models;

import com.mirkwood.logistics.core.enums.StaffRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mirkwood_staff")
public class MirkwoodStaff {

    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;

    @Column(name = "staff_username",unique = true)
    private String staffUsername;

    @Column(name = "staff_full_name",nullable = false)
    private String staffFullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "staff_role",nullable = false)
    private StaffRole staffRole = StaffRole.STAFF;

    @Column(name = "staff_office_code",nullable = false)
    private String staffOfficeCode;

    @Column(name = "staff_office_address",nullable = false)
    private String staffOfficeAddress;

    @Column(name = "staff_email_id", unique = true)
    private String staffEmailId;

    @Column(name = "staff_phone_number")
    private String staffPhoneNumber;

}
