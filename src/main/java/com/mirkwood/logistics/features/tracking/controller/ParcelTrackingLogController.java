package com.mirkwood.logistics.features.tracking.controller;

import com.mirkwood.logistics.features.tracking.dto.ParcelTrackingLogDTO;
import com.mirkwood.logistics.features.tracking.service.ParcelTrackingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/mirkwood-logistics/parcel-tracking")
public class ParcelTrackingLogController {

    @Autowired
    private ParcelTrackingLogService parcelTrackingLogService;

    @GetMapping("/status/{trackingNumber}")
    ResponseEntity<List<ParcelTrackingLogDTO>> getParcelAllStatusByTrackingNumber(@PathVariable String trackingNumber) {
        List<ParcelTrackingLogDTO> parcelTrackingLogDTOS = parcelTrackingLogService.getParcelStatusByTrackingNumber(trackingNumber);
        return ResponseEntity.ok(parcelTrackingLogDTOS);
    }

    @PostMapping("/update")
    public ResponseEntity<List<ParcelTrackingLogDTO>> updateParcelStatusUsingTrackingNumber(@RequestBody ParcelTrackingLogDTO parcelTrackingLogDTO) {
        String trackingNumber = parcelTrackingLogDTO.getTrackingNumber();
        List<ParcelTrackingLogDTO> updatedParcelTrackingLogDTO = parcelTrackingLogService.updateParcelStatusByTrackingNumber(trackingNumber, parcelTrackingLogDTO);

        if (Objects.nonNull(updatedParcelTrackingLogDTO) && !updatedParcelTrackingLogDTO.isEmpty()) {
            return ResponseEntity.ok(updatedParcelTrackingLogDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
