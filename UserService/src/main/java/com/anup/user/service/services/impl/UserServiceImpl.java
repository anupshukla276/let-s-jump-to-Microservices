package com.anup.user.service.services.impl;

import com.anup.user.service.entities.Hotel;
import com.anup.user.service.entities.Rating;
import com.anup.user.service.entities.User;
import com.anup.user.service.exceptions.ResourceNotFoundException;
import com.anup.user.service.external.services.HotelService;
import com.anup.user.service.external.services.RatingService;
import com.anup.user.service.repositories.UserRepository;
import com.anup.user.service.services.UserServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserServices {

    private final UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;
    private RatingService ratingService;

    @Override
    public User saveUser(User user) {
        // Generate Unique Id
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            // Fetch ratings of the user from Rating Service
            String url = "http://RATING-SERVER/ratings/users/" + user.getUserId();
            Rating[] ratingsOfUser = restTemplate.getForObject(url, Rating[].class);
            log.info("Ratings for user {}: {}", user.getUserId(), (Object) ratingsOfUser);

            // Fetch hotel details for each rating and set the userId in each rating
            if (ratingsOfUser != null) {
                for (Rating rating : ratingsOfUser) {
                    rating.setUserid(user.getUserId()); // Set the userId here
                    String hotelUrl = "http://HOTEL-SERVICE/hotels/" + rating.getHotelId();
                    Hotel hotel = restTemplate.getForObject(hotelUrl, Hotel.class);
                    rating.setHotel(hotel);
                }
                user.setRatings(Arrays.asList(ratingsOfUser));
            }
        }
        return ResponseEntity.ok(users);
    }

    //    @Override
//    public User getUser(String userId) {
//        // Get user from database with the help of User Repository
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User with given id not found on the server!! : " + userId));
//
//        // Fetch ratings of the user from Rating Service
//        String url = "http://RATING-SERVER/ratings/users/" + userId;
//        Rating[] ratingsOfUser = restTemplate.getForObject(url, Rating[].class);
//        log.info("Ratings: {}", (Object) ratingsOfUser);
//
//        // Fetch hotel details for each rating and set the userId in each rating
//        if (ratingsOfUser != null) {
//            for (Rating rating : ratingsOfUser) {
//                rating.setUserid(userId); // Set the userId here
//                // String hotelUrl = "http://HOTEL-SERVICE/hotels/" + rating.getHotelId();
//                //  Hotel hotel = restTemplate.getForObject(hotelUrl, Hotel.class);
//                Hotel hotel = hotelService.getHotel(rating.getHotelId());
//                rating.setHotel(hotel);
//            }
//        }
//
//        user.setRatings(Arrays.asList(ratingsOfUser));
//        return ResponseEntity.ok(user).getBody();
//    }
//}
    @Override
    public ResponseEntity<User> getUser(String userId) {
        // Get user from database with the help of User Repository
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with given id not found on the server!! : " + userId));

        // Fetch ratings of the user from Rating Service
        String url = "http://RATING-SERVER/ratings/users/" + userId;
        Rating[] ratingsOfUser = restTemplate.getForObject(url, Rating[].class);
        log.info("Ratings: {}", (Object) ratingsOfUser);

        // Fetch hotel details for each rating and set the userId in each rating
        if (ratingsOfUser != null) {
            List<Rating> ratingList = Arrays.stream(ratingsOfUser)
                    .peek(rating -> rating.setUserid(userId)) // Set the userId here
                    .map(rating -> {
                        Hotel hotel = hotelService.getHotel(rating.getHotelId());
                        rating.setHotel(hotel);
                        return rating;
                    })
                    .collect(Collectors.toList());

            user.setRatings(ratingList);
        }

        return ResponseEntity.ok(user);
    }
}





// Uncomment and implement the methods below if needed
    // @Override
    // public User updateUser(String userId, User updatedUser) {
    //     User existingUser = userRepository.findById(userId).orElse(null);
    //     if (existingUser != null) {
    //         existingUser.setName(updatedUser.getName());
    //         existingUser.setEmail(updatedUser.getEmail());
    //         // Update other fields as needed
    //         return userRepository.save(existingUser);
    //     }
    //     return null; // Handle scenario where user with userId not found
    // }

    // @Override
    // public void deleteUser(String userId) {
    //     userRepository.deleteById(userId);
    // }

