package domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/*@Builder
@Getter
@Setter*/
public class ContactEvent {

    private Long id;
    private String email;
    private String reason;
    private String message;

    public ContactEvent() {
        //JPA
    }

}
