package com.mirkwood.logistics.features.tracking.repository;

import com.mirkwood.logistics.core.models.Parcel;
import com.mirkwood.logistics.core.models.ParcelTrackingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelTrackingLogRepository extends JpaRepository<ParcelTrackingLog,Long> {
    List<ParcelTrackingLog> findByParcelOrderByUpdateTimeAsc(Parcel parcel);
}
