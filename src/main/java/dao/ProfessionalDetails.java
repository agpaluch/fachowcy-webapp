package dao;


import repository.City;
import repository.CityDistrict;
import repository.TypeOfProfession;

public class ProfessionalDetails implements ProfessionalProfile {

    //profile information
    private String name;
    private String surname;
    private TypeOfProfession profession;
    private long phoneNumber;
    private City city;
    private CityDistrict district;
    private double longitude;
    private double latitude;
    private int numberLikes=0;
    private String comments;



    public ProfessionalDetails(){
        //Non-parametric constructor used in MenuAdd
    }

    public ProfessionalDetails(String name, String surname, TypeOfProfession profession,
                               long phoneNumber, City city, CityDistrict district,
                               double longitude, double latitude) {

        this.name = name;
        this.surname = surname;
        this.profession = profession;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.district = district;
        this.longitude = longitude;
        this.latitude = latitude;



    }

    @Override
    public TypeOfProfession getProfession() {
        return profession;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public long getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public City getCity() {
        return city;
    }

    @Override
    public CityDistrict getDistrict() {
        return district;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    public int getNumberLikes() {
        return numberLikes;
    }

    @Override
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

    public void setDistrict(CityDistrict district) {
        this.district = district;
    }

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
                "City district: " + getDistrict().toString() + "\n"+ "Longitude: " + getLongitude() + "\n"+
                "Latitude: " + getLatitude() + "\n"+ "Number of likes: "+ getNumberLikes() + "\n"+
                "Comments: " + getComments());
    }


}


