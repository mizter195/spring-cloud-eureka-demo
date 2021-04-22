package duong.demo.ratingdataservice.controllers;

import duong.demo.ratingdataservice.models.Rating;
import duong.demo.ratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/ratings")
public class MovieRatingController {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId) {
        return new Rating(movieId, 4);
    }

    @GetMapping("/user/{userId}")
    public UserRating getRatingsOfUser(@PathVariable String userId) {
        return new UserRating(
                userId,
                Arrays.asList(
                        new Rating("123", 4),
                        new Rating("345", 5)
                )
        );
    }
}
