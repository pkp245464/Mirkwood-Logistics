package com.mirkwood.logistics.features.parcel.repository;

import com.mirkwood.logistics.core.enums.ParcelLifecycleStatus;
import com.mirkwood.logistics.core.models.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel,Long> {
    Optional<Parcel> findByTrackingNumber(String trackingNumber);
    Optional<Parcel> findByParcelIdAndParcelLifecycleStatus(Long parcelId, ParcelLifecycleStatus status);
    Optional<Parcel> findByTrackingNumberAndParcelLifecycleStatus(String trackingNumber, ParcelLifecycleStatus status);
    List<Parcel> findByParcelLifecycleStatus(ParcelLifecycleStatus status);
    List<Parcel> findBySenderNameAndParcelLifecycleStatus(String senderName, ParcelLifecycleStatus status);
    List<Parcel> findByReceiverNameAndParcelLifecycleStatus(String receiverName, ParcelLifecycleStatus status);

    @Query("SELECT p FROM Parcel p WHERE p.registeredDate BETWEEN :startDate AND :endDate AND p.parcelLifecycleStatus = :status")
    List<Parcel> findByDateRangeAndLifecycleStatus(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("status") ParcelLifecycleStatus status
    );
}
