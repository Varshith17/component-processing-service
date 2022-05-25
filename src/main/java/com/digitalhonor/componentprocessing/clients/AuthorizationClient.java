package com.digitalhonor.componentprocessing.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authentication-service")
public interface AuthorizationClient {
	@GetMapping(value = "/auth/authorize")
	ResponseEntity<?> validateAndReturnUser(@RequestHeader("Authorization") String token);
}
