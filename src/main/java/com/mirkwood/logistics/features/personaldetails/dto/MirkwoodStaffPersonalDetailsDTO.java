package com.mirkwood.logistics.features.personaldetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mirkwood.logistics.core.enums.GenderType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MirkwoodStaffPersonalDetailsDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty("gender")
    private GenderType gender;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("current_address")
    private String currentAddress;

    @JsonProperty("permanent_address")
    private String permanentAddress;

    @JsonProperty("employee_id")
    private String employeeId;

    @JsonProperty("date_of_joining")
    private LocalDateTime dateOfJoining;

    @JsonProperty("designation")
    private String designation;

    @JsonProperty("department")
    private String department;
}
