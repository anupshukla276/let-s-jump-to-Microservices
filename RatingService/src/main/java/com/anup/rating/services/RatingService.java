package com.anup.rating.services;

import com.anup.rating.entities.Rating;

import java.util.List;

public interface RatingService {
    //create

    Rating create(Rating rating);

    //getAllRating
    List<Rating>getRatings();

    // get all by user id
    List<Rating>getRatingByUserId( String userId);


    //get All by hotel
    List<Rating>getRatingByHotelId(String hotelId);

}
