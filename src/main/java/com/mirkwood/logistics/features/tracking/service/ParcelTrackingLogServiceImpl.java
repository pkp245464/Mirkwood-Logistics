package com.mirkwood.logistics.features.tracking.service;

import com.mirkwood.logistics.core.enums.ParcelLifecycleStatus;
import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.core.models.Parcel;
import com.mirkwood.logistics.core.models.ParcelTrackingLog;
import com.mirkwood.logistics.features.parcel.repository.ParcelRepository;
import com.mirkwood.logistics.features.parcel.service.ParcelService;
import com.mirkwood.logistics.features.tracking.dto.ParcelTrackingLogDTO;
import com.mirkwood.logistics.features.tracking.repository.ParcelTrackingLogRepository;
import com.mirkwood.logistics.features.tracking.utility.ParcelTrackingLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParcelTrackingLogServiceImpl implements ParcelTrackingLogService{

    @Autowired
    private ParcelTrackingLogRepository parcelTrackingLogRepository;

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private ParcelService parcelService;

    @Override
    public List<ParcelTrackingLogDTO> getParcelStatusByTrackingNumber(String trackingNumber) {
        Parcel parcel = parcelRepository.findByTrackingNumberAndParcelLifecycleStatus(trackingNumber, ParcelLifecycleStatus.ACTIVE)
                .orElseThrow(() -> new CustomMirkwoodLogisticsExceptions("Active parcel not found with tracking number: " + trackingNumber));

        List<ParcelTrackingLog> trackingLogs = parcelTrackingLogRepository.findByParcelOrderByUpdateTimeAsc(parcel);

        if (trackingLogs.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("No tracking logs found for parcel with tracking number: " + trackingNumber);
        }

        return trackingLogs.stream()
                .map(ParcelTrackingLogMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParcelTrackingLogDTO> updateParcelStatusByTrackingNumber(String trackingNumber, ParcelTrackingLogDTO parcelTrackingLogDTO) {
        Optional<Parcel> parcelOptional = parcelRepository.findByTrackingNumberAndParcelLifecycleStatus(trackingNumber, ParcelLifecycleStatus.ACTIVE);
        if (parcelOptional.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("Active parcel not found with tracking number: " + trackingNumber);
        }
        Parcel parcel = parcelOptional.get();

        ParcelTrackingLog newTrackingLog = ParcelTrackingLogMapper.toEntity(parcelTrackingLogDTO);
        newTrackingLog.setParcel(parcel);
        newTrackingLog.setUpdateTime(LocalDateTime.now());

        parcelTrackingLogRepository.save(newTrackingLog);

        List<ParcelTrackingLog> allTrackingLogs = parcelTrackingLogRepository.findByParcelOrderByUpdateTimeAsc(parcel);

        return allTrackingLogs.stream()
                .map(ParcelTrackingLogMapper::toDTO)
                .collect(Collectors.toList());
    }
}
