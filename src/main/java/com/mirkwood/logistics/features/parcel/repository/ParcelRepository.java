package com.mirkwood.logistics.features.parcel.repository;

import com.mirkwood.logistics.core.enums.ParcelLifecycleStatus;
import com.mirkwood.logistics.core.models.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel,Long> {

    Optional<Parcel> findByTrackingNumber(String trackingNumber);
    Optional<Parcel> findByParcelIdAndParcelLifecycleStatus(Long parcelId, ParcelLifecycleStatus status);
    Optional<Parcel> findByTrackingNumberAndParcelLifecycleStatus(String trackingNumber, ParcelLifecycleStatus status);

}
