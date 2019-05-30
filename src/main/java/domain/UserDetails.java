package domain;

import repository.City;
import repository.TypeOfProfession;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "userDetails")
public class UserDetails {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id")
    @MapsId
    UserLogin userLogin;

    @Column(name = "email")
    String email;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String surname;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeOfProfession profession;

    @Column
    @NotNull
    private long phoneNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private City city;
    //private CityDistrict district;

    @Column
    private double longitude;

    @Column
    private double latitude;

    @Column
    private int numberLikes = 0;

    @Column
    private String comments = "";

    public UserDetails(){
        // Hibernate
    }

    public UserDetails(String email, String name, String surname, TypeOfProfession profession,
                               long phoneNumber, City city,
                               //CityDistrict district,
                               double longitude, double latitude) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.profession = profession;
        this.phoneNumber = phoneNumber;
        this.city = city;
        //this.district = district;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public TypeOfProfession getProfession() {
        return profession;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public City getCity() {
        return city;
    }

/*    @Override
    public CityDistrict getDistrict() {
        return district;
    }*/

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getNumberLikes() {
        return numberLikes;
    }

    public String getComments() {
        return comments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setProfession(TypeOfProfession profession) {
        this.profession = profession;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCity(City city) {
        this.city = city;
    }

/*    public void setDistrict(CityDistrict district) {
        this.district = district;
    }*/

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setNumberLikes(int numberLikes) {
        this.numberLikes = numberLikes;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", profession=" + profession +
                ", phoneNumber=" + phoneNumber +
                ", city=" + city +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", numberLikes=" + numberLikes +
                ", comments='" + comments + '\'' +
                '}';
    }
}


