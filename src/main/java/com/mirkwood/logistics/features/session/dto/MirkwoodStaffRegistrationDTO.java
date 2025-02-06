package com.mirkwood.logistics.features.session.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MirkwoodStaffRegistrationDTO {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
