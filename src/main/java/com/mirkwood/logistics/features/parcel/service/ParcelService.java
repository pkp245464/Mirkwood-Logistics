package com.mirkwood.logistics.features.parcel.service;

import com.mirkwood.logistics.features.parcel.dto.ParcelDTO;

import java.util.List;

public interface ParcelService {

    //find (list the parcel which is "ACTIVE")
    long getTotalParcelsCount();
    List<ParcelDTO> getAllParcels();
    ParcelDTO getParcelById(Long parcelId);
    ParcelDTO getParcelByTrackingNumber(String trackingNumber);
    List<ParcelDTO> getParcelsBySender(String senderName);
    List<ParcelDTO> getParcelsByReceiver(String receiverName);
    List<ParcelDTO> getParcelsByDateRange(String startDate, String endDate);


    // create
    ParcelDTO createNewParcel(ParcelDTO parcelDTO);


    // update
    ParcelDTO updateStatusByParcelId(Long parcelId, ParcelDTO parcelDTO);
    ParcelDTO updateStatusByTrackingNumber(String trackingNumber, ParcelDTO parcelDTO);


    // delete
    void deleteParcelById(Long parcelId);
    void deleteParcelByTrackingNumber(String trackingNumber);


    // restore
    void restoreParcelByParcelId(Long parcelId);
    void restoreParcelByTrackingNumber(String trackingNumber);

}
