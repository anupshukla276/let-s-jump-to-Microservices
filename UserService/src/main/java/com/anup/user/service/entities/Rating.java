package com.anup.user.service.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
    private  String ratingId;
    private String userid;
    private String hotelId;
    private int rating;
    private String feedback;
    private Hotel hotel;



}

