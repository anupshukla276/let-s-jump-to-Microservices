package com.anup.rating.controllers;

import com.anup.rating.entities.Rating;
import com.anup.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    //create
    @PostMapping
    public ResponseEntity<Rating>create(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }
    //get all

    @GetMapping
    public  ResponseEntity<List<Rating>>getRatings(){
        return ResponseEntity.ok(ratingService.getRatings());


    }
    //get rating by user Id
    @GetMapping("/users/{userId}")
    public  ResponseEntity<List<Rating>>getRatingByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));

    }
    //get rating by hotelid
    @GetMapping("/hotels/{hotelId}")
    public  ResponseEntity<List<Rating>>getRatingByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));

    }

}
