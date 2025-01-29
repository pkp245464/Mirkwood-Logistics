package com.mirkwood.logistics.features.personaldetails.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @JsonProperty("gender")
    private GenderType gender;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("email_id")
    private String emailId;

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

    @JsonProperty("is_Deleted")
    private Boolean isDeleted;

    public MirkwoodStaffPersonalDetailsDTO(String firstName, String lastName, GenderType gender, String emailId, String phoneNumber, String employeeId, String designation, String department, String permanentAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.employeeId = employeeId;
        this.designation = designation;
        this.department = department;
        this.permanentAddress = permanentAddress;
    }

    public MirkwoodStaffPersonalDetailsDTO() {

    }
}
