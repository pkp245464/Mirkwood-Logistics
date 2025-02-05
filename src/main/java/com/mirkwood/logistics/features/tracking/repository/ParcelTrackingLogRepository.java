package com.mirkwood.logistics.features.tracking.repository;

import com.mirkwood.logistics.core.models.ParcelTrackingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelTrackingLogRepository extends JpaRepository<ParcelTrackingLog,Long> {

}
