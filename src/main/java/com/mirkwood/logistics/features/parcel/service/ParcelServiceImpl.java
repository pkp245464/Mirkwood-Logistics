package com.mirkwood.logistics.features.parcel.service;

import com.mirkwood.logistics.core.enums.ParcelLifecycleStatus;
import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.core.models.Parcel;
import com.mirkwood.logistics.features.parcel.dto.ParcelDTO;
import com.mirkwood.logistics.features.parcel.repository.ParcelRepository;
import com.mirkwood.logistics.features.parcel.utility.ParcelMapper;
import com.mirkwood.logistics.features.parcel.utility.ParcelUpdateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParcelServiceImpl implements ParcelService{

    private static final Logger logger = LoggerFactory.getLogger(ParcelServiceImpl.class);


    @Autowired
    private ParcelRepository parcelRepository;

    @Override
    public long getTotalParcelsCount() {
        List<Parcel> activeParcels = parcelRepository.findByParcelLifecycleStatus(ParcelLifecycleStatus.ACTIVE);
        if (activeParcels.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("No active parcels found");
        }
        return activeParcels.size();
    }

    @Override
    public List<ParcelDTO> getAllParcels() {
        List<Parcel> activeParcels = parcelRepository.findByParcelLifecycleStatus(ParcelLifecycleStatus.ACTIVE);
        if (activeParcels.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("No active parcels found");
        }
        return activeParcels.stream()
                .map(ParcelMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ParcelDTO getParcelById(Long parcelId) {
        Parcel parcel = parcelRepository.findByParcelIdAndParcelLifecycleStatus(parcelId, ParcelLifecycleStatus.ACTIVE)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Active parcel not found with ID: " + parcelId));
        return ParcelMapper.mapToDTO(parcel);
    }

    @Override
    public ParcelDTO getParcelByTrackingNumber(String trackingNumber) {
        Parcel parcel = parcelRepository.findByTrackingNumberAndParcelLifecycleStatus(trackingNumber, ParcelLifecycleStatus.ACTIVE)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Active parcel not found with tracking number: " + trackingNumber));
        return ParcelMapper.mapToDTO(parcel);
    }


    @Override
    public List<ParcelDTO> getParcelsBySender(String senderName) {
        List<Parcel> parcels = parcelRepository.findBySenderNameAndParcelLifecycleStatus(senderName, ParcelLifecycleStatus.ACTIVE);
        if (parcels.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("No active parcels found for sender: " + senderName);
        }
        return parcels.stream()
                .map(ParcelMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParcelDTO> getParcelsByReceiver(String receiverName) {
        List<Parcel> parcels = parcelRepository.findByReceiverNameAndParcelLifecycleStatus(receiverName, ParcelLifecycleStatus.ACTIVE);
        if (parcels.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("No active parcels found for receiver: " + receiverName);
        }
        return parcels.stream()
                .map(ParcelMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParcelDTO> getParcelsByDateRange(String startDate, String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        List<Parcel> parcels = parcelRepository.findByDateRangeAndLifecycleStatus(start, end, ParcelLifecycleStatus.ACTIVE);
        if (parcels.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("No active parcels found for the given date range");
        }
        return parcels.stream()
                .map(ParcelMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ParcelDTO createNewParcel(ParcelDTO parcelDTO) {
        Parcel parcel = ParcelMapper.mapToEntity(parcelDTO);
        Parcel savedParcel = parcelRepository.save(parcel);
        return ParcelMapper.mapToDTO(savedParcel);
    }

    @Override
    public ParcelDTO updateStatusByParcelId(Long parcelId, ParcelDTO parcelDTO) {
        Parcel parcel = parcelRepository.findByParcelIdAndParcelLifecycleStatus(parcelId, ParcelLifecycleStatus.ACTIVE)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Active parcel not found with ID: " + parcelId));

        ParcelUpdateMapper.updateSenderReceiverDetails(parcel, parcelDTO);

        Parcel updatedParcel = parcelRepository.save(parcel);
        return ParcelMapper.mapToDTO(updatedParcel);
    }

    @Override
    public ParcelDTO updateStatusByTrackingNumber(String trackingNumber, ParcelDTO parcelDTO) {
        Parcel parcel = parcelRepository.findByTrackingNumberAndParcelLifecycleStatus(trackingNumber, ParcelLifecycleStatus.ACTIVE)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Active parcel not found with tracking number: " + trackingNumber));

        ParcelUpdateMapper.updateSenderReceiverDetails(parcel, parcelDTO);

        Parcel updatedParcel = parcelRepository.save(parcel);
        return ParcelMapper.mapToDTO(updatedParcel);
    }

    //TODO: implement using query
    @Override
    public void deleteParcelById(Long parcelId) {
        Parcel parcel = parcelRepository.findById(parcelId)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Parcel with ID " + parcelId + " not found."));

        if (parcel.getParcelLifecycleStatus() == ParcelLifecycleStatus.INACTIVE) {
            throw new CustomMirkwoodLogisticsExceptions("Parcel with ID " + parcelId + " is already deleted.");
        }
        parcel.setParcelLifecycleStatus(ParcelLifecycleStatus.INACTIVE);
        parcelRepository.save(parcel);
    }


    @Override
    public void deleteParcelByTrackingNumber(String trackingNumber) {
        Parcel parcel = parcelRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Parcel with tracking number " + trackingNumber + " not found."));

        if (parcel.getParcelLifecycleStatus() == ParcelLifecycleStatus.INACTIVE) {
            throw new CustomMirkwoodLogisticsExceptions("Parcel with tracking number " + trackingNumber + " is already deleted.");
        }
        parcel.setParcelLifecycleStatus(ParcelLifecycleStatus.INACTIVE);
        parcelRepository.save(parcel);
    }


    @Override
    public void restoreParcelByParcelId(Long parcelId) {
        Parcel parcel = parcelRepository.findByParcelIdAndParcelLifecycleStatus(parcelId, ParcelLifecycleStatus.INACTIVE)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Parcel with ID " + parcelId + " not found."));

        if (parcel.getParcelLifecycleStatus() == ParcelLifecycleStatus.ACTIVE) {
            throw new CustomMirkwoodLogisticsExceptions("Parcel with ID " + parcelId + " is already active.");
        }

        parcel.setParcelLifecycleStatus(ParcelLifecycleStatus.ACTIVE);
        parcelRepository.save(parcel);

        ParcelMapper.mapToDTO(parcel);
    }

    @Override
    public void restoreParcelByTrackingNumber(String trackingNumber) {
        Parcel parcel = parcelRepository.findByTrackingNumberAndParcelLifecycleStatus(trackingNumber, ParcelLifecycleStatus.INACTIVE)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Parcel with tracking number " + trackingNumber + " not found or already active."));

        if (parcel.getParcelLifecycleStatus() == ParcelLifecycleStatus.ACTIVE) {
            throw new CustomMirkwoodLogisticsExceptions("Parcel with tracking number " + trackingNumber + " is already active.");
        }

        parcel.setParcelLifecycleStatus(ParcelLifecycleStatus.ACTIVE);
        parcelRepository.save(parcel);

        ParcelMapper.mapToDTO(parcel);
    }
}
