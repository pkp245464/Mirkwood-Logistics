package com.mirkwood.logistics.features.parcel.repository;

import com.mirkwood.logistics.core.models.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel,Long> {
    boolean existsByTrackingNumber(String trackingNumber);
}
