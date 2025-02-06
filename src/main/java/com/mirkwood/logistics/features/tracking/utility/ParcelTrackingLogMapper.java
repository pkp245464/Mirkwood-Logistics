package com.mirkwood.logistics.features.tracking.utility;

import com.mirkwood.logistics.core.models.ParcelTrackingLog;
import com.mirkwood.logistics.features.tracking.dto.ParcelTrackingLogDTO;
import org.springframework.stereotype.Component;

@Component
public class ParcelTrackingLogMapper {
    public static ParcelTrackingLog toEntity(ParcelTrackingLogDTO trackingLogDTO) {
        ParcelTrackingLog trackingLog = new ParcelTrackingLog();
        trackingLog.setParcelLogId(trackingLogDTO.getParcelLogId());
        trackingLog.setTrackingNumber(trackingLogDTO.getTrackingNumber());
        trackingLog.setStatus(trackingLogDTO.getStatus());
        trackingLog.setCity(trackingLogDTO.getCity());
        trackingLog.setState(trackingLogDTO.getState());
        trackingLog.setCountry(trackingLogDTO.getCountry());
        trackingLog.setUpdateTime(trackingLogDTO.getUpdateTime());
        return trackingLog;
    }

    public static ParcelTrackingLogDTO toDTO(ParcelTrackingLog trackingLog) {
        ParcelTrackingLogDTO parcelTrackingLogDTO = new ParcelTrackingLogDTO();
        parcelTrackingLogDTO.setParcelLogId(trackingLog.getParcelLogId());
        parcelTrackingLogDTO.setTrackingNumber(trackingLog.getParcel().getTrackingNumber());
        parcelTrackingLogDTO.setStatus(trackingLog.getStatus());
        parcelTrackingLogDTO.setCity(trackingLog.getCity());
        parcelTrackingLogDTO.setState(trackingLog.getState());
        parcelTrackingLogDTO.setCountry(trackingLog.getCountry());
        parcelTrackingLogDTO.setUpdateTime(trackingLog.getUpdateTime());
        return parcelTrackingLogDTO;
    }
}
