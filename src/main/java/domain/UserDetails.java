package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import repository.City;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Blob;


@Data
@Builder
@AllArgsConstructor
@Embeddable
public class UserDetails {

    private String name;
    private String surname;

    @Builder.Default
    private Blob profilePicture = null;

    private long phoneNumber;

    @Enumerated(EnumType.STRING)
    private City city;

    private Double longitude;
    private Double latitude;

    @Builder.Default
    private int numberOfLikes = 0;

   public UserDetails(){
        // used by Hibernate
    }



}


