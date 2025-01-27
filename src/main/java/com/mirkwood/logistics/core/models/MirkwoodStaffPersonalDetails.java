package com.mirkwood.logistics.core.models;

import com.mirkwood.logistics.core.enums.GenderType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "mirkwood_staff_personal_details")
public class MirkwoodStaffPersonalDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender",nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Column(name = "nationality",nullable = false)
    private String nationality;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "current_address")
    private String currentAddress;

    @Column(name = "permanent_address",nullable = false)
    private String permanentAddress;

    @Column(name = "employee_id", unique = true)
    private String employeeId;

    @Column(name = "date_of_joining")
    private LocalDateTime dateOfJoining;

    @Column(name = "designation")
    private String designation;

    @Column(name = "department")
    private String department;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}