package com.mirkwood.logistics.core.models;

import com.mirkwood.logistics.core.enums.ParcelStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "parcel_tracking_log")
public class ParcelTrackingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parcel_log_id")
    private Long parcelLogId;

    @ManyToOne
    @JoinColumn(name = "parcel_id", nullable = false)
    private Parcel parcel;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ParcelStatus status;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;
}
