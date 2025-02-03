package com.mirkwood.logistics.features.parcel.service;

import com.mirkwood.logistics.features.parcel.dto.ParcelDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelServiceImpl implements ParcelService{

    @Override
    public long getTotalParcelsCount() {
        return 0;
    }

    @Override
    public List<ParcelDTO> getAllParcels() {
        return List.of();
    }

    @Override
    public ParcelDTO getParcelById(Long parcelId) {
        return null;
    }

    @Override
    public ParcelDTO getParcelByTrackingNumber(String trackingNumber) {
        return null;
    }

    @Override
    public List<ParcelDTO> getParcelsBySender(String senderName) {
        return List.of();
    }

    @Override
    public List<ParcelDTO> getParcelsByReceiver(String receiverName) {
        return List.of();
    }

    @Override
    public List<ParcelDTO> getParcelsByDateRange(String startDate, String endDate) {
        return List.of();
    }

    @Override
    public ParcelDTO createNewParcel(ParcelDTO parcelDTO) {
        return null;
    }

    @Override
    public ParcelDTO updateStatusByParcelId(Long parcelId, ParcelDTO parcelDTO) {
        return null;
    }

    @Override
    public ParcelDTO updateStatusByTrackingNumber(String trackingNumber, ParcelDTO parcelDTO) {
        return null;
    }

    @Override
    public void deleteParcelById(Long parcelId) {

    }

    @Override
    public void deleteParcelByTrackingNumber(String trackingNumber) {

    }

    @Override
    public ParcelDTO restoreParcelByParcelId(Long parcelId) {
        return null;
    }

    @Override
    public ParcelDTO restoreParcelByTrackingNumber(String trackingNumber) {
        return null;
    }
}
