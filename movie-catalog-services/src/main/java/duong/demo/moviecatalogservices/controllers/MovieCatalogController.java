package duong.demo.moviecatalogservices.controllers;

import duong.demo.moviecatalogservices.models.CatalogItem;
import duong.demo.moviecatalogservices.models.Movie;
import duong.demo.moviecatalogservices.models.UserRating;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class MovieCatalogController {

    private final WebClient.Builder webClientBuilder;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalogs(@PathVariable String userId) {

        UserRating ratings = webClientBuilder.build()
                .get()
                .uri("http://rating-data-service/ratings/user/" + userId)
                .retrieve()
                .bodyToMono(UserRating.class)
                .block();


        return ratings.getRatings().stream()
                .map(r -> {
                    Movie movie = webClientBuilder.build()
                            .get()
                            .uri("http://movie-info-service/movies/" + r.getMovieId())
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();
                    return new CatalogItem(movie.getName(), "description", r.getRating());
                })
                .collect(Collectors.toList());
    }
}
