package com.example.wallet.Service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("User")
public interface UserService {
}
