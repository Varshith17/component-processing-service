package com.digitalhonor.componentprocessing.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "packaging-delivery-service")
public interface PackageDeliveryClient {

	@GetMapping("/packagingAndDelivery/getCharge/{componentType}/{count}")
	double getPackagingAndDeliveryCharge(@PathVariable("componentType") String componentType, @PathVariable("count") int count);
	
}
