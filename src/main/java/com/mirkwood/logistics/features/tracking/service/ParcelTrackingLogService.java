package com.mirkwood.logistics.features.tracking.service;

import com.mirkwood.logistics.features.tracking.dto.ParcelTrackingLogDTO;

import java.util.List;

public interface ParcelTrackingLogService {
    List<ParcelTrackingLogDTO> getParcelStatusByTrackingNumber(String trackingNumber);
    List<ParcelTrackingLogDTO> updateParcelStatusByTrackingNumber(String trackingNumber, ParcelTrackingLogDTO parcelTrackingLogDTO);
}
