package domain;


import repository.City;
import repository.CityDistrict;

public interface ClientProfile {

    String getName();
    String getSurname();
    long getPhoneNumber();
    City getCity();
    //CityDistrict getDistrict();
    double getLongitude();
    double getLatitude();
    int getNumberLikes();
    String getComments();


    void setName(String name);
    void setSurname(String name);
    void setPhoneNumber(long phoneNumber);
    void setCity(City city);
    //void setDistrict(CityDistrict district);
    void setLongitude(double longitude);
    void setLatitude(double latitude);
    void setNumberLikes(int numerLikes);
    void setComments(String comments);




}
