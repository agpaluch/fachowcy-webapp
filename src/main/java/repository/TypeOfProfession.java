package repository;

public enum TypeOfProfession {

    PLUMBER("hydraulik"),
    ELECTRICIAN("elektryk"),
    TILER("tiler");


    private final String fullName;

    TypeOfProfession(String fullName) {
        this.fullName = fullName;
    }



    public String getFullName() {
        return fullName;
    }


}
