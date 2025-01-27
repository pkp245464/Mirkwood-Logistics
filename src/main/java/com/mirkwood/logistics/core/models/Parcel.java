package com.mirkwood.logistics.core.models;

import com.mirkwood.logistics.core.enums.ParcelType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "parcels")
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parcel_id")
    private Long parcelId;

    @Column(name = "tracking_number", unique = true, nullable = false)
    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "parcel_type", nullable = false)
    private ParcelType parcelType;


    // sender details
    @Column(name = "sender_name", nullable = false)
    private String senderName;

    @Column(name = "sender_address", nullable = false)
    private String senderAddress;

    @Column(name = "sender_phone_number", nullable = false)
    private String senderPhoneNumber;

    @Column(name = "sender_email_id")
    private String senderEmailId;

    // receiver details
    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Column(name = "receiver_address", nullable = false)
    private String receiverAddress;

    @Column(name = "receiver_phone_number", nullable = false)
    private String receiverPhoneNumber;

    @Column(name = "receiver_email_id")
    private String receiverEmailId;

    //parcel logs details
    @OneToMany(mappedBy = "parcel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParcelTrackingLog> trackingLogs;
}
