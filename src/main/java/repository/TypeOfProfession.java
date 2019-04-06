package repository;

import java.util.Arrays;
import java.util.List;

public enum TypeOfProfession {

    PLUMBER("hydraulik"),
    ELECTRICIAN("elektryk"),
    TILER("tiler");


    private final String fullName;

    TypeOfProfession(String fullName) {
        this.fullName = fullName;
    }

/*    @Override
    public String toString() {
        return "Profession{" +
                "fullName='" + fullName + '\'' +
                '}';
    }*/

    public String getFullName() {
        return fullName;
    }


}
