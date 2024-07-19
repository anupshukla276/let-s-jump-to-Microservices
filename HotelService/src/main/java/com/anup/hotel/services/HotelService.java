package com.anup.hotel.services;

import com.anup.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    //Create

    Hotel create(Hotel hotel);

    //getall
    List<Hotel> getAll();


    //get single
    Hotel get(String id);




}
