package com.anup.user.service.external.services;

import com.anup.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rating-service")
public interface RatingService {
    @GetMapping("/ratings/users/{userId}")
    Rating[] getRatingsByUserId(@PathVariable("userId") String userId);
}