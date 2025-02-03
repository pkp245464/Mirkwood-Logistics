package com.mirkwood.logistics.features.parcel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mirkwood.logistics.core.enums.ParcelLifecycleStatus;
import com.mirkwood.logistics.core.enums.ParcelType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParcelDTO {

    @JsonProperty("parcel_id")
    private Long parcelId;

    @JsonProperty("tracking_number")
    private String trackingNumber;

    @JsonProperty("parcel_type")
    private ParcelType parcelType;

    // sender details
    @JsonProperty("sender_name")
    private String senderName;

    @JsonProperty("sender_address")
    private String senderAddress;

    @JsonProperty("sender_phone_number")
    private String senderPhoneNumber;

    @JsonProperty("sender_email_id")
    private String senderEmailId;

    // receiver details
    @JsonProperty("receiver_name")
    private String receiverName;

    @JsonProperty("receiver_address")
    private String receiverAddress;

    @JsonProperty("receiver_phone_number")
    private String receiverPhoneNumber;

    @JsonProperty("receiver_email_id")
    private String receiverEmailId;

    @JsonProperty("parcel_life_cycle_status")
    private ParcelLifecycleStatus parcelLifecycleStatus;

    @JsonProperty("registered_date")
    private LocalDateTime registeredDate;

    @JsonProperty("expected_delivery")
    private LocalDateTime expectedDeliveryDate;

    // parcel logs details
    @JsonProperty("tracking_logs")
    private List<ParcelTrackingLogDTO> trackingLogs;
}
