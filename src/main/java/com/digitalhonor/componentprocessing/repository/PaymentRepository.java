package com.digitalhonor.componentprocessing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.digitalhonor.componentprocessing.entity.PaymentData;;

public interface PaymentRepository extends JpaRepository<PaymentData, Integer> {

}
