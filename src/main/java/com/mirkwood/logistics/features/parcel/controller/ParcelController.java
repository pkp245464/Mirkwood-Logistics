package com.mirkwood.logistics.features.parcel.controller;

import com.mirkwood.logistics.core.exceptions.CustomMirkwoodLogisticsExceptions;
import com.mirkwood.logistics.features.parcel.dto.ParcelDTO;
import com.mirkwood.logistics.features.parcel.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/mirkwood-logistics/parcel")
public class ParcelController {

    @Autowired
    private ParcelService parcelService;

    // find
    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping("/fetch/count")
    public ResponseEntity<Long> getTotalParcelsCount() {
        long count = parcelService.getTotalParcelsCount();
        return ResponseEntity.ok(count);
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping("/fetch/all")
    public ResponseEntity<List<ParcelDTO>> getAllParcels() {
        List<ParcelDTO> parcelDTOs = parcelService.getAllParcels();
        return ResponseEntity.ok(parcelDTOs);
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping("/fetch/parcelId/{parcelId}")
    public ResponseEntity<ParcelDTO> getByParcelId(@PathVariable Long parcelId) {
        ParcelDTO parcelDTO = parcelService.getParcelById(parcelId);
        return ResponseEntity.ok(parcelDTO);
    }

    @GetMapping("/fetch/trackingNumber/{trackingNumber}")
    public ResponseEntity<ParcelDTO> getByParcelTrackingNumber(@PathVariable String trackingNumber) {
        ParcelDTO parcelDTO = parcelService.getParcelByTrackingNumber(trackingNumber);
        return ResponseEntity.ok(parcelDTO);
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping("/fetch/sender")
    public ResponseEntity<List<ParcelDTO>> getParcelsBySender(@RequestParam String senderName) {
        List<ParcelDTO> parcels = parcelService.getParcelsBySender(senderName);
        return ResponseEntity.ok(parcels);
    }


    @GetMapping("/fetch/receiver")
    public ResponseEntity<List<ParcelDTO>> getParcelsByReceiver(@RequestParam String receiverName) {
        List<ParcelDTO> parcels = parcelService.getParcelsByReceiver(receiverName);
        return ResponseEntity.ok(parcels);
    }


    @GetMapping("/fetch/date-range")
    public ResponseEntity<List<ParcelDTO>> getParcelsByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        List<ParcelDTO> parcels = parcelService.getParcelsByDateRange(startDate, endDate);
        return ResponseEntity.ok(parcels);
    }

    // create
    @PreAuthorize("hasAuthority('STAFF')")
    @PostMapping("/register")
    public ResponseEntity<ParcelDTO> registerNewParcel(@RequestBody ParcelDTO parcelDTO) {
        ParcelDTO createdParcel = parcelService.createNewParcel(parcelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdParcel);
    }

    // update
    @PreAuthorize("hasAuthority('STAFF')")
    @PutMapping("/update/parcelId")
    public ResponseEntity<ParcelDTO> updateParcelById(@RequestBody ParcelDTO parcelDTO) {
        Long parcelId = parcelDTO.getParcelId();

        if (Objects.isNull(parcelId)) {
            throw new CustomMirkwoodLogisticsExceptions("Parcel ID is required to update details.");
        }

        ParcelDTO updatedParcel = parcelService.updateStatusByParcelId(parcelId, parcelDTO);
        return ResponseEntity.ok(updatedParcel);
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @PutMapping("/update/trackingNumber")
    public ResponseEntity<ParcelDTO> updateParcelByTrackingNumber(@RequestBody ParcelDTO parcelDTO) {
        String trackingNumber = parcelDTO.getTrackingNumber();

        if (Objects.isNull(trackingNumber) || trackingNumber.isEmpty()) {
            throw new CustomMirkwoodLogisticsExceptions("Tracking number is required to update details.");
        }

        ParcelDTO updatedParcel = parcelService.updateStatusByTrackingNumber(trackingNumber, parcelDTO);
        return ResponseEntity.ok(updatedParcel);
    }

    //delete
    @PreAuthorize("hasAuthority('STAFF')")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteParcel(@RequestParam(required = false) Long parcelId,
                                               @RequestParam(required = false) String trackingNumber) {
        if (Objects.nonNull(parcelId)) {
            parcelService.deleteParcelById(parcelId);
            return ResponseEntity.ok("Parcel with ID " + parcelId + " has been discontinued and is no longer in service.");
        }
        else if (Objects.nonNull(trackingNumber)) {
            parcelService.deleteParcelByTrackingNumber(trackingNumber);
            return ResponseEntity.ok("Parcel with tracking number " + trackingNumber + " has been discontinued and is no longer in service.");
        }
        return ResponseEntity.badRequest().body("Either parcelId or trackingNumber must be provided.");
    }

    // restore
    @PreAuthorize("hasAuthority('STAFF')")
    @PutMapping("/restore")
    public ResponseEntity<String> restoreParcel(@RequestParam(required = false) Long parcelId,
                                                @RequestParam(required = false) String trackingNumber) {
        if (Objects.nonNull(parcelId)) {
            parcelService.restoreParcelByParcelId(parcelId);
            return ResponseEntity.ok("Parcel with ID " + parcelId + " has been successfully restored to ACTIVE.");
        }
        else if (Objects.nonNull(trackingNumber)) {
            parcelService.restoreParcelByTrackingNumber(trackingNumber);
            return ResponseEntity.ok("Parcel with tracking number " + trackingNumber + " has been successfully restored to ACTIVE.");
        }
        return ResponseEntity.badRequest().body("Either parcelId or trackingNumber must be provided.");
    }

}
