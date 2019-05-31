package domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "messages")
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "message_id")
    private Long id;

    @Column
    @NotBlank
    @Length(max = 400)
    private String message;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "sender")
    private UserLogin userLogin1;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "recipient")
    private UserLogin userLogin2;

    @Id
    @Column(name = "sender")
    private Long sender;

    @Id
    @Column(name = "recipient")
    private Long recipient;

    @Column
    private boolean wasRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getRecipient() {
        return recipient;
    }

    public void setRecipient(Long recipient) {
        this.recipient = recipient;
    }

    public boolean isWasRead() {
        return wasRead;
    }

    public UserLogin getUserLogin1() {
        return userLogin1;
    }

    public void setUserLogin1(UserLogin userLogin1) {
        this.userLogin1 = userLogin1;
    }

    public UserLogin getUserLogin2() {
        return userLogin2;
    }

    public void setUserLogin2(UserLogin userLogin2) {
        this.userLogin2 = userLogin2;
    }

    public void setWasRead(boolean wasRead) {
        this.wasRead = wasRead;
    }

    public Messages() {
        //Hibernate
    }
}
