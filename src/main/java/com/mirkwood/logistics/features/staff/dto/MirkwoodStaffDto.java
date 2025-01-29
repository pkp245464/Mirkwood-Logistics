package com.mirkwood.logistics.features.staff.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mirkwood.logistics.core.enums.StaffRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MirkwoodStaffDto {

    @JsonProperty("staff_id")
    private Long staffId;

    @JsonProperty("staff_username")
    private String staffUsername;

    @JsonProperty("staff_full_name")
    private String staffFullName;

    @JsonProperty("staff_role")
    private StaffRole staffRole;

    @JsonProperty("staff_office_code")
    private String staffOfficeCode;

    @JsonProperty("staff_office_address")
    private String staffOfficeAddress;

    @JsonProperty("staff_email_id")
    private String staffEmailId;

    @JsonProperty("staff_phone_number")
    private String staffPhoneNumber;

    public MirkwoodStaffDto() {
    }

    public MirkwoodStaffDto(Long staffId, String staffUsername, String staffFullName, StaffRole staffRole,
                            String staffOfficeCode, String staffOfficeAddress, String staffEmailId,
                            String staffPhoneNumber) {
        this.staffId = staffId;
        this.staffUsername = staffUsername;
        this.staffFullName = staffFullName;
        this.staffRole = staffRole;
        this.staffOfficeCode = staffOfficeCode;
        this.staffOfficeAddress = staffOfficeAddress;
        this.staffEmailId = staffEmailId;
        this.staffPhoneNumber = staffPhoneNumber;
    }
}
