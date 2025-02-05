package com.mirkwood.logistics.features.parcel.utility;
import com.mirkwood.logistics.core.models.Parcel;
import com.mirkwood.logistics.features.parcel.dto.ParcelDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ParcelUpdateMapper {
    public static void updateSenderReceiverDetails(Parcel parcel, ParcelDTO parcelDTO) {
        // Update sender details if provided
        if (Objects.nonNull(parcelDTO.getSenderName())) {
            parcel.setSenderName(parcelDTO.getSenderName());
        }
        if (Objects.nonNull(parcelDTO.getSenderAddress())) {
            parcel.setSenderAddress(parcelDTO.getSenderAddress());
        }
        if (Objects.nonNull(parcelDTO.getSenderPhoneNumber())) {
            parcel.setSenderPhoneNumber(parcelDTO.getSenderPhoneNumber());
        }
        if (Objects.nonNull(parcelDTO.getSenderEmailId())) {
            parcel.setSenderEmailId(parcelDTO.getSenderEmailId());
        }

        // Update receiver details if provided
        if (Objects.nonNull(parcelDTO.getReceiverName())) {
            parcel.setReceiverName(parcelDTO.getReceiverName());
        }
        if (Objects.nonNull(parcelDTO.getReceiverAddress())) {
            parcel.setReceiverAddress(parcelDTO.getReceiverAddress());
        }
        if (Objects.nonNull(parcelDTO.getReceiverPhoneNumber())) {
            parcel.setReceiverPhoneNumber(parcelDTO.getReceiverPhoneNumber());
        }
        if (Objects.nonNull(parcelDTO.getReceiverEmailId())) {
            parcel.setReceiverEmailId(parcelDTO.getReceiverEmailId());
        }
    }
}
