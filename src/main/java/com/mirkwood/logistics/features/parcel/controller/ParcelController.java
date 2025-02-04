package com.mirkwood.logistics.features.parcel.controller;

import com.mirkwood.logistics.features.parcel.dto.ParcelDTO;
import com.mirkwood.logistics.features.parcel.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mirkwood-logistics/parcel")
public class ParcelController {

    @Autowired
    private ParcelService parcelService;

    @PostMapping("/register")
    public ResponseEntity<ParcelDTO> registerNewParcel(@RequestBody ParcelDTO parcelDTO) {
        ParcelDTO createdParcel = parcelService.createNewParcel(parcelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdParcel);
    }
}
