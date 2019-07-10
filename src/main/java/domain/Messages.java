package domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "messages")
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private long id;

    @Column
    @NotBlank
    @Length(max = 400)
    private String message;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private UserLogin sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    private UserLogin recipient;

    @Column
    private boolean wasRead;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserLogin getSender() {
        return sender;
    }

    public void setSender(UserLogin sender) {
        this.sender = sender;
    }

    public UserLogin getRecipient() {
        return recipient;
    }

    public void setRecipient(UserLogin recipient) {
        this.recipient = recipient;
    }

    public boolean isWasRead() {
        return wasRead;
    }

    public void setWasRead(boolean wasRead) {
        this.wasRead = wasRead;
    }

    public Messages() {
        //Hibernate
    }
}
