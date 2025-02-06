package com.mirkwood.logistics.features.tracking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mirkwood.logistics.core.enums.ParcelStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParcelTrackingLogDTO {
    @JsonProperty("parcel_log_id")
    private Long parcelLogId;

    @JsonProperty("tracking_number")
    private String trackingNumber;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("country")
    private String country;

    @JsonProperty("status")
    private ParcelStatus status;

    @JsonProperty("update_time")
    private LocalDateTime updateTime;
}
