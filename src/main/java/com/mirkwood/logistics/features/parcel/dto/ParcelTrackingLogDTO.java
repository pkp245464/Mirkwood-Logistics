package com.mirkwood.logistics.features.parcel.dto;

import com.mirkwood.logistics.core.enums.ParcelStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ParcelTrackingLogDTO {
    private Long parcelLogId;
    private Long parcelId;
    private String city;
    private String state;
    private String country;
    private ParcelStatus status;
    private LocalDateTime updateTime;
}
