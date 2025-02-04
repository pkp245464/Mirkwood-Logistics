package com.mirkwood.logistics.features.parcel.service;

import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.core.models.Parcel;
import com.mirkwood.logistics.features.parcel.dto.ParcelDTO;
import com.mirkwood.logistics.features.parcel.repository.ParcelRepository;
import com.mirkwood.logistics.features.parcel.tracking.TrackingNumberGenerator;
import com.mirkwood.logistics.features.parcel.utility.ParcelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ParcelServiceImpl implements ParcelService{

    private static final Logger logger = LoggerFactory.getLogger(ParcelServiceImpl.class);


    @Autowired
    private ParcelRepository parcelRepository;

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
        Parcel parcel = ParcelMapper.mapToEntity(parcelDTO);
        Parcel savedParcel = parcelRepository.save(parcel);
        return ParcelMapper.mapToDTO(savedParcel);
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
