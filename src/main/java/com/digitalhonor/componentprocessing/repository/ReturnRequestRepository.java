package com.digitalhonor.componentprocessing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.digitalhonor.componentprocessing.entity.ProcessData;

public interface ReturnRequestRepository extends JpaRepository<ProcessData, Integer> {
}
