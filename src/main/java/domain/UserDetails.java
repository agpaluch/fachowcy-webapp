package domain;

import repository.City;
import repository.TypeOfProfession;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class UserDetails {

    @Id
    String login = null;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String surname;

    @Column
    private TypeOfProfession profession;

    @Column
    @NotNull
    private long phoneNumber;

    @Column
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

    public UserDetails(String name, String surname, TypeOfProfession profession,
                               long phoneNumber, City city,
                               //CityDistrict district,
                               double longitude, double latitude) {

        this.name = name;
        this.surname = surname;
        this.profession = profession;
        this.phoneNumber = phoneNumber;
        this.city = city;
        //this.district = district;
        this.longitude = longitude;
        this.latitude = latitude;
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


    public String toString(){
        return ("Name: " + getName()  + "\n"+
                "Surname: " + getSurname()  + "\n" + "Profession: " +
                getProfession().toString() + "\n"+
                "Phone number: " + getPhoneNumber() + "\n"+ "City: " + getCity().toString() +"\n"+
                //"City district: " + getDistrict().toString() + "\n"+
                "Longitude: " + getLongitude() + "\n"+
                "Latitude: " + getLatitude() + "\n"+ "Number of likes: "+ getNumberLikes() + "\n"+
                "Comments: " + getComments());
    }


}


