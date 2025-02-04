package com.mirkwood.logistics.features.parcel.utility;

import com.mirkwood.logistics.core.enums.ParcelLifecycleStatus;
import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.core.models.Parcel;
import com.mirkwood.logistics.features.parcel.dto.ParcelDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ParcelMapper {

    // Helper method to validate required fields
    private static void validateRequiredField(String field, String fieldName) {
        if (Objects.isNull(field) || field.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions(fieldName + " is required");
        }
    }

    private static void validateParcelDTO(ParcelDTO parcelDTO) {
        // Validate parcel type
        if (Objects.isNull(parcelDTO.getParcelType())) {
            throw new CustomMirkwoodLogisticsExceptions("Parcel type is required");
        }

        // Validate sender details
        validateRequiredField(parcelDTO.getSenderName(), "Sender name");
        validateRequiredField(parcelDTO.getSenderAddress(), "Sender address");
        validateRequiredField(parcelDTO.getSenderPhoneNumber(), "Sender phone number");
        validateRequiredField(parcelDTO.getSenderEmailId(), "Sender email ID");

        // Validate receiver details
        validateRequiredField(parcelDTO.getReceiverName(), "Receiver name");
        validateRequiredField(parcelDTO.getReceiverAddress(), "Receiver address");
        validateRequiredField(parcelDTO.getReceiverPhoneNumber(), "Receiver phone number");
        validateRequiredField(parcelDTO.getReceiverEmailId(), "Receiver email ID");
    }


    public static Parcel mapToEntity(ParcelDTO parcelDTO) {
        // Validate the input parcelDTO
        validateParcelDTO(parcelDTO);

        //TODO:
        String trackingNUmber = "";

        Parcel parcel = new Parcel();
        parcel.setTrackingNumber(parcelDTO.getTrackingNumber());
        parcel.setParcelType(parcelDTO.getParcelType());

        //sender
        parcel.setSenderName(parcelDTO.getSenderName());
        parcel.setSenderAddress(parcelDTO.getSenderAddress());
        parcel.setSenderPhoneNumber(parcelDTO.getSenderPhoneNumber());
        parcel.setSenderEmailId(parcelDTO.getSenderEmailId());

        //receiver
        parcel.setReceiverName(parcelDTO.getReceiverName());
        parcel.setReceiverAddress(parcelDTO.getReceiverAddress());
        parcel.setReceiverPhoneNumber(parcelDTO.getReceiverPhoneNumber());
        parcel.setReceiverEmailId(parcelDTO.getReceiverEmailId());

        parcel.setRegisteredDate(parcelDTO.getRegisteredDate());
        parcel.setExpectedDeliveryDate(parcelDTO.getExpectedDeliveryDate());
        parcel.setParcelLifecycleStatus(ParcelLifecycleStatus.ACTIVE);

        return parcel;
    }

    public static ParcelDTO mapToDTO(Parcel parcel) {
        ParcelDTO parcelDTO = new ParcelDTO();
        parcelDTO.setParcelId(parcel.getParcelId());
        parcelDTO.setTrackingNumber(parcel.getTrackingNumber());
        parcelDTO.setParcelType(parcel.getParcelType());

        parcelDTO.setSenderName(parcel.getSenderName());
        parcelDTO.setSenderAddress(parcel.getSenderAddress());
        parcelDTO.setSenderPhoneNumber(parcel.getSenderPhoneNumber());
        parcelDTO.setSenderEmailId(parcel.getSenderEmailId());

        parcelDTO.setReceiverName(parcel.getReceiverName());
        parcelDTO.setReceiverAddress(parcel.getReceiverAddress());
        parcelDTO.setReceiverPhoneNumber(parcel.getReceiverPhoneNumber());
        parcelDTO.setReceiverEmailId(parcel.getReceiverEmailId());

        parcelDTO.setRegisteredDate(parcel.getRegisteredDate());
        parcelDTO.setExpectedDeliveryDate(parcel.getExpectedDeliveryDate());
        parcelDTO.setParcelLifecycleStatus(parcel.getParcelLifecycleStatus());
        return parcelDTO;
    }
}