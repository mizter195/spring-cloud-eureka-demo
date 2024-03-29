package duong.demo.ratingdataservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserRating {

    private String userId;
    private List<Rating> ratings;
}
